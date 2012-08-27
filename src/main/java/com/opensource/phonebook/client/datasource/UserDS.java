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
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class UserDS extends GwtRpcDataSource
{

    private static UserDS instance = null;

    private final UserServiceAsync userService = GWT.create(UserService.class);

    public static UserDS getInstance()
    {
        if (instance == null)
        {
            instance = new UserDS();
        }
        return instance;
    }

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

    DataSourceDateField userBirthdateField;

    public UserDS()
    {
        super();
        setID("UsersGwtRpcDataSource");

        userIdField = new DataSourceIntegerField(Constants.USER_ID, Constants.TITLE_USER_ID);
        userIdField.setPrimaryKey(true);
        userIdField.setCanEdit(false);
        userIdField.setHidden(true);

        userActiveField = new DataSourceBooleanField(Constants.USER_ACTIVE, Constants.TITLE_USER_ACTIVE);
        userPositionIdField = new DataSourceIntegerField(Constants.USER_POSITION_ID, Constants.TITLE_USER_POSITION_ID);

        usernameField = new DataSourceTextField(Constants.USER_USERNAME, Constants.TITLE_USER_USERNAME, 200);
        passwordField = new DataSourceTextField(Constants.USER_PASSWORD, Constants.TITLE_USER_PASSWORD, 200);

        userFirstNameField = new DataSourceTextField(Constants.USER_FIRST_NAME, Constants.TITLE_USER_FIRST_NAME, 200);
        userLastNameField = new DataSourceTextField(Constants.USER_LAST_NAME, Constants.TITLE_USER_LAST_NAME, 200);
        userEmailField = new DataSourceTextField(Constants.USER_EMAIL, Constants.TITLE_USER_EMAIL);

        userQuestion1Field =
            new DataSourceTextField(Constants.USER_SECURITY_QUESTION_1, Constants.TITLE_USER_SECURITY_QUESTION_1, 200);
        userAnswer1Field =
            new DataSourceTextField(Constants.USER_SECURITY_ANSWER_1, Constants.TITLE_USER_SECURITY_ANSWER_1, 200);

        userQuestion2Field =
            new DataSourceTextField(Constants.USER_SECURITY_QUESTION_2, Constants.TITLE_USER_SECURITY_QUESTION_2, 200);
        userAnswer2Field =
            new DataSourceTextField(Constants.USER_SECURITY_ANSWER_2, Constants.TITLE_USER_SECURITY_ANSWER_2, 200);

        userBirthdateField = new DataSourceDateField(Constants.USER_BIRTHDATE, Constants.TITLE_USER_BIRTHDATE, 200);

        setFields(userIdField, userActiveField, userPositionIdField, usernameField, passwordField, userFirstNameField,
            userLastNameField, userEmailField, userQuestion1Field, userAnswer1Field, userQuestion2Field,
            userAnswer2Field, userBirthdateField);
    }

    // *************************************************************************************
    // *************************************************************************************

    @Override
    protected void executeAdd(final String requestId, final DSRequest request, final DSResponse response)
    {
        // Retrieve record which should be added.
        JavaScriptObject data = request.getData();
        ListGridRecord rec = new ListGridRecord(data);
        UserDTO testRec = new UserDTO();
        copyValues(rec, testRec);
        // ConsumerInterestServiceAsync service = GWT.create (ConsumerInterestService.class);
        userService.add(testRec, new AsyncCallback<UserDTO>()
        {
            public void onFailure(Throwable caught)
            {
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
            }

            public void onSuccess(UserDTO result)
            {
                ListGridRecord[] list = new ListGridRecord[1];
                ListGridRecord newRec = new ListGridRecord();
                copyValues(result, newRec);
                list[0] = newRec;
                response.setData(list);
                processResponse(requestId, response);
            }
        });
    }

    @Override
    protected void executeRemove(final String requestId, final DSRequest request, final DSResponse response)
    {
        // Retrieve record which should be removed.
        JavaScriptObject data = request.getData();
        final ListGridRecord rec = new ListGridRecord(data);
        UserDTO testRec = new UserDTO();
        copyValues(rec, testRec);
        userService.remove(testRec, new AsyncCallback<Void>()
        {
            public void onFailure(Throwable caught)
            {
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
            }

            public void onSuccess(Void result)
            {
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
    protected void executeFetch(final String requestId, DSRequest request, final DSResponse response)
    {

        // for this example I will do "paging" on client side
        final int startIndex = (request.getStartRow() < 0) ? 0 : request.getStartRow();
        final int endIndex = (request.getEndRow() == null) ? -1 : request.getEndRow();

        userService.fetch(new AsyncCallback<List<UserDTO>>()
        {
            public void onFailure(Throwable caught)
            {
                caught.printStackTrace();
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
            }

            public void onSuccess(List<UserDTO> result)
            {
                // Calculating size of return list
                int size = result.size();
                if (endIndex >= 0)
                {
                    if (endIndex < startIndex)
                    {
                        size = 0;
                    }
                    else
                    {
                        size = endIndex - startIndex + 1;
                    }
                }
                // Create list for return - it is just requested records
                ListGridRecord[] list = new ListGridRecord[size];
                if (size > 0)
                {
                    for (int i = 0; i < result.size(); i++)
                    {
                        if (i >= startIndex && i <= endIndex)
                        {
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
    protected void executeUpdate(final String requestId, DSRequest request, final DSResponse response)
    {
        // Retrieve record which should be updated
        JavaScriptObject data = request.getData();
        final ListGridRecord rec = new ListGridRecord(data);
        UserDTO testRec = new UserDTO();
        copyValues(rec, testRec);
        userService.update(testRec, new AsyncCallback<Void>()
        {
            @Override
            public void onFailure(Throwable caught)
            {
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
                SC.say("Profile Edit Save", "User edits have NOT been saved!");
            }

            @Override
            public void onSuccess(Void result)
            {
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData(list);
                processResponse(requestId, response);
                SC.say("Profile Edit Save", "User edits have been saved!");
            }
        });
    }

    // *************************************************************************************
    // *************************************************************************************

    private void copyValues(ListGridRecord from, UserDTO to)
    {
        to.setId(from.getAttributeAsInt(userIdField.getName()));
        to.setActive(from.getAttributeAsBoolean(userActiveField.getName()));
        // ================================================================================
        to.setUsername(from.getAttributeAsString(usernameField.getName()));
        to.setPassword(from.getAttributeAsString(passwordField.getName()));
        // ================================================================================
        PositionDTO position = new PositionDTO();
        int positionId = from.getAttributeAsInt(userPositionIdField.getName());
        position.setId(positionId);
        position.setActive(true);
        to.setPosition(position);
        // ================================================================================
        to.setFirstname(from.getAttributeAsString(userFirstNameField.getName()));
        to.setLastname(from.getAttributeAsString(userLastNameField.getName()));
        to.setEmail(from.getAttributeAsString(userEmailField.getName()));
        // ================================================================================
        to.setSecurityQuestion1(from.getAttributeAsString(userQuestion1Field.getName()));
        to.setSecurityAnswer1(from.getAttributeAsString(userAnswer1Field.getName()));
        to.setSecurityQuestion2(from.getAttributeAsString(userQuestion2Field.getName()));
        to.setSecurityAnswer2(from.getAttributeAsString(userAnswer2Field.getName()));
        // ================================================================================
        to.setBirthdate(from.getAttributeAsDate(userBirthdateField.getName()));
    }

    private static void copyValues(UserDTO from, ListGridRecord to)
    {
        to.setAttribute("userId", from.getId());
        to.setAttribute("userActive", from.isActive());
        // ================================================================================
        to.setAttribute("userFirstName", from.getFirstname());
        to.setAttribute("userLastName", from.getLastname());
        // ================================================================================
        to.setAttribute("userHomeEmail", from.getEmail());
        // ================================================================================
    }

    private ListGridRecord getEditedRecord(DSRequest request)
    {
        // Retrieving values before edit
        JavaScriptObject oldValues = request.getAttributeAsJavaScriptObject("oldValues");
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
