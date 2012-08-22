package com.opensource.phonebook.client.datasource;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.client.services.UserService;
import com.opensource.phonebook.client.services.UserServiceAsync;
import com.opensource.phonebook.client.utils.datasource.GwtRpcDataSource;
import com.opensource.phonebook.shared.Constants;
import com.opensource.phonebook.shared.dto.PositionDTO;
import com.opensource.phonebook.shared.dto.UserDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class UserDS extends GwtRpcDataSource
{
    
 private static UserDS instance = null;
    
   
    private final UserServiceAsync userService = GWT.create(UserService.class);
    
    public static UserDS getInstance() {
        if (instance == null) {
            instance = new UserDS();
        }
        return instance;
    }
	
	public final static String USER_ID = "id";
	public final static String USER_ACTIVE = "active";
	public final static String USER_POSITION = "position";
	public final static String USER_USERNAME = "username";
	public final static String USER_PASSWORD = "password";
	public final static String USER_FIRSTNAME = "firstName";
	public final static String USER_LASTNAME = "lastName";
	public final static String USER_EMAIL = "email";
	public final static String USER_QUESTION_1 = "securityQuestion1";
	public final static String USER_ANSWER_1 = "securityAnswer1";
	public final static String USER_QUESTION_2 = "securityQuestion2";
    public final static String USER_ANSWER_2 = "securityAnswer2";

	DataSourceIntegerField userIdField;
	DataSourceBooleanField userActiveField;
	DataSourceIntegerField userPositionIdField;
	
	DataSourceTextField usernameField;
	DataSourceTextField passwordField;
	
	DataSourceTextField userFirstNameField;
	DataSourceTextField userLastNameField;
	DataSourceTextField userEmailField;
	
	DataSourceTextField userQuestion1Field;
	DataSourceTextField userAnswer1Field;
	DataSourceTextField userQuestion2Field;
    DataSourceTextField userAnswer2Field;
	
	public UserDS() {
		super();
		setID("UsersGwtRpcDataSource");

		userIdField = new DataSourceIntegerField(Constants.USER_ID, null);
		userIdField.setPrimaryKey(true);
		userIdField.setCanEdit(false);
		userIdField.setHidden(true);
		
		userActiveField = new DataSourceBooleanField(Constants.USER_ACTIVE,"Active");
		userPositionIdField = new DataSourceIntegerField(Constants.USER_POSITION_ID, "Position");
		
		usernameField = new DataSourceTextField(Constants.USER_USERNAME, "Username",200);
		passwordField = new DataSourceTextField(Constants.USER_PASSWORD, "Password",200);     
		
		userFirstNameField = new DataSourceTextField(Constants.USER_FIRST_NAME, "First Name",200);
		userLastNameField = new DataSourceTextField(Constants.USER_LAST_NAME, "Last Name",200);		
		userEmailField = new DataSourceTextField(Constants.USER_EMAIL, null);
		
		userQuestion1Field = new DataSourceTextField(Constants.USER_SECURITY_QUESTION_1, "Security Question 1",200);
		userAnswer1Field = new DataSourceTextField(Constants.USER_SECURITY_ANSWER_1, "Security Answer 1",200);
		
		userQuestion2Field = new DataSourceTextField(Constants.USER_SECURITY_QUESTION_2, "Security Question 2",200);
        userAnswer2Field = new DataSourceTextField(Constants.USER_SECURITY_ANSWER_2, "Security Answer 2",200);   
        
		setFields(userIdField, userActiveField,  userPositionIdField,
		    usernameField, passwordField,
		    userFirstNameField,userLastNameField,userEmailField, 
		    userQuestion1Field, userAnswer1Field, userQuestion2Field, userAnswer2Field);
	}

	// *************************************************************************************
	// *************************************************************************************

	 @Override
     protected void executeAdd (final String requestId, final DSRequest request, final DSResponse response) {
         // Retrieve record which should be added.
         JavaScriptObject data = request.getData ();
         ListGridRecord rec = new ListGridRecord (data);
         UserDTO testRec = new UserDTO ();
         copyValues (rec, testRec);
         //ConsumerInterestServiceAsync service = GWT.create (ConsumerInterestService.class);
         userService.add (testRec, new AsyncCallback<UserDTO> () {
             public void onFailure (Throwable caught) {
                 response.setStatus (RPCResponse.STATUS_FAILURE);
                 processResponse (requestId, response);
             }
             public void onSuccess (UserDTO result) {
                 ListGridRecord[] list = new ListGridRecord[1];
                 ListGridRecord newRec = new ListGridRecord ();
                 copyValues (result, newRec);
                 list[0] = newRec;
                 response.setData (list);
                 processResponse (requestId, response);
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
		userService.remove(testRec, new AsyncCallback<Void>() {
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

        userService.fetch(new AsyncCallback<List<UserDTO>>() {
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
		to.setId(from.getAttributeAsInt(userIdField.getName()));
		to.setActive(from.getAttributeAsBoolean(userActiveField.getName()));
		//================================================================================
		to.setUsername(from.getAttributeAsString(usernameField.getName()));
        to.setPassword(from.getAttributeAsString(passwordField.getName()));
		//================================================================================
		PositionDTO position = new PositionDTO();
		int positionId = from.getAttributeAsInt(userPositionIdField.getName());
		position.setId(positionId);
		position.setActive(true);
		to.setPosition(position);
		//================================================================================
		to.setFirstname(from.getAttributeAsString(userFirstNameField.getName()));
		to.setLastname(from.getAttributeAsString(userLastNameField.getName()));
		to.setEmail(from.getAttributeAsString(userEmailField.getName()));
		//================================================================================
		to.setSecurityQuestion1(from.getAttributeAsString(userQuestion1Field.getName()));
        to.setSecurityAnswer1(from.getAttributeAsString(userAnswer1Field.getName()));
        to.setSecurityQuestion2(from.getAttributeAsString(userQuestion2Field.getName()));
        to.setSecurityAnswer2(from.getAttributeAsString(userAnswer2Field.getName()));
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
