package com.opensource.phonebook.client.datasource;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.client.services.UserService;
import com.opensource.phonebook.client.services.UserServiceAsync;
import com.opensource.phonebook.client.utils.datasource.GwtRpcDataSource;
import com.opensource.phonebook.shared.Constants;
import com.opensource.phonebook.shared.dto.UserDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class UserDS extends GwtRpcDataSource {
	
	public final static String USER_ID = "userId";
	public final static String USER_ACTIVE = "active";
	public final static String USER_USERNAME = "username";
	public final static String USER_PASSWORD = "password";
	
	public final static String USER_FIRSTNAME = "firstName";
	public final static String USER_LASTNAME = "lastName";

	DataSourceIntegerField userIdField;
	DataSourceBooleanField userActiveField;
	DataSourceTextField userFirstNameField;
	DataSourceTextField userMiddleNameField;
	DataSourceTextField userLastNameField;
	DataSourceTextField userEmailField;
	
	public UserDS() {
		super();
		setID("UsersGWTRPCDataSource");

		userIdField = new DataSourceIntegerField(Constants.USER_ID, "Id");
		userIdField.setPrimaryKey(true);
		userIdField.setCanEdit(false);
		userIdField.setHidden(true);
		
		userActiveField = new DataSourceBooleanField(Constants.USER_ACTIVE,"Active");

		userFirstNameField = new DataSourceTextField(Constants.USER_FIRST_NAME, "First Name",200);
		userLastNameField = new DataSourceTextField(Constants.USER_LAST_NAME, "Last Name",200);		
		userEmailField = new DataSourceTextField(Constants.USER_EMAIL, null);

		setFields(userIdField, userActiveField, userFirstNameField, userMiddleNameField, userLastNameField);
	}

	// *************************************************************************************
	// *************************************************************************************

	@Override
	protected void executeAdd(final String requestId, final DSRequest request, final DSResponse response) {
		// Retrieve record which should be added.
		JavaScriptObject data = request.getData();
		final ListGridRecord rec = new ListGridRecord(data);
		UserDTO testRec = new UserDTO();
		copyValues(rec, testRec);
		UserServiceAsync service = GWT.create(UserService.class);
		service.add(testRec, new AsyncCallback<UserDTO>() {
			public void onFailure(Throwable caught) {
				response.setStatus(RPCResponse.STATUS_FAILURE);
				processResponse(requestId, response);
			}

			public void onSuccess(UserDTO arg0) {
				ListGridRecord[] list = new ListGridRecord[1];
				list[0] = rec;
				response.setData(list);
				processResponse(requestId, response);
			}
		});
	}

	@Override
	protected void executeRemove(final String requestId, final DSRequest request, final DSResponse response) {
		// Retrieve record which should be removed.
		JavaScriptObject data = request.getData();
		final ListGridRecord rec = new ListGridRecord(data);
		UserDTO testRec = new UserDTO();
		copyValues(rec, testRec);
		UserServiceAsync service = GWT.create(UserService.class);
		service.remove(testRec, new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				response.setStatus(RPCResponse.STATUS_FAILURE);
				processResponse(requestId, response);
			}

			public void onSuccess(Void result) {
				ListGridRecord[] list = new ListGridRecord[1];
				// We do not receive removed record from server.
				// Return record from request.
				list[0] = rec;
				response.setData(list);
				processResponse(requestId, response);
			}
		});
	}

	// *************************************************************************************
	// *************************************************************************************

	@Override
	protected void executeFetch(final String requestId, DSRequest request, final DSResponse response) {
		
	     // for this example I will do "paging" on client side
        final int startIndex = (request.getStartRow () < 0)?0:request.getStartRow ();
        final int endIndex = (request.getEndRow () == null)?-1:request.getEndRow ();

		UserServiceAsync service = GWT.create(UserService.class);

		service.fetch(new AsyncCallback<List<UserDTO>>() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				response.setStatus(RPCResponse.STATUS_FAILURE);
				processResponse(requestId, response);
			}

			public void onSuccess(List<UserDTO> result) {
				// Calculating size of return list
				int size = result.size();
				if (endIndex >= 0) {
					if (endIndex < startIndex) {
						size = 0;
					} else {
						size = endIndex - startIndex + 1;
					}
				}
				// Create list for return - it is just requested records
				ListGridRecord[] list = new ListGridRecord[size];
				if (size > 0) {
					for (int i = 0; i < result.size(); i++) {
						if (i >= startIndex && i <= endIndex) {
							ListGridRecord record = new ListGridRecord();
							copyValues(result.get(i), record);
							list[i - startIndex] = record;
						}
					}
				}
				response.setStatus(RPCResponse.STATUS_SUCCESS);
				response.setData(list);
				// IMPORTANT: for paging to work we have to specify size of full
				// result set
				response.setTotalRows(result.size());
				processResponse(requestId, response);
			}

		});

	}

	@Override
	protected void executeUpdate(String requestId, DSRequest request, DSResponse response) {
		// TODO Auto-generated method stub
	}

	// *************************************************************************************
	// *************************************************************************************

	private void copyValues(ListGridRecord from, UserDTO to) {
		to.setId(from.getAttributeAsInt(userIdField.getAttribute("userId")));
		to.setActive(from.getAttributeAsBoolean(userActiveField.getAttribute("userActive")));
		//================================================================================
		to.setFirstname(from.getAttributeAsString(userFirstNameField.getName()));
		to.setLastname(from.getAttributeAsString(userLastNameField.getName()));
		//================================================================================
		to.setEmail(from.getAttributeAsString(userEmailField.getName()));
		//================================================================================
	}

	private static void copyValues(UserDTO from, ListGridRecord to) {
		to.setAttribute("userId", from.getId());
		to.setAttribute("userActive",from.isActive());
		//================================================================================
		to.setAttribute("userFirstName", from.getFirstname());
		to.setAttribute("userLastName", from.getLastname());
		//================================================================================
		to.setAttribute("userHomeEmail", from.getEmail());
		//================================================================================
	}

	private ListGridRecord getEditedRecord(DSRequest request) {
		// Retrieving values before edit
		JavaScriptObject oldValues = request
				.getAttributeAsJavaScriptObject("oldValues");
		// Creating new record for combining old values with changes
		ListGridRecord newRecord = new ListGridRecord();
		// Copying properties from old record
		JSOHelper.apply(oldValues, newRecord.getJsObj());
		// Retrieving changed values
		JavaScriptObject data = request.getData();
		// Apply changes
		JSOHelper.apply(data, newRecord.getJsObj());
		return newRecord;
	}

}
