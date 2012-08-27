package com.opensource.phonebook.client.datasource;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.client.services.ContactService;
import com.opensource.phonebook.client.services.ContactServiceAsync;
import com.opensource.phonebook.client.utils.datasource.GwtRpcDataSource;
import com.opensource.phonebook.shared.Constants;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.UserDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceDateTimeField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ContactDS extends GwtRpcDataSource
{
    private static ContactDS instance = null;

    private final ContactServiceAsync service = GWT.create(ContactService.class);

    public static ContactDS getInstance()
    {
        if (instance == null)
        {
            instance = new ContactDS();
        }
        return instance;
    }

    DataSourceIntegerField userIdField;

    DataSourceIntegerField contactIdField;
    DataSourceTextField prefixField;
    DataSourceTextField firstNameField;
    DataSourceTextField middleNameField;
    DataSourceTextField lastNameField;
    DataSourceTextField suffixField;

    DataSourceTextField address1Field;
    DataSourceTextField address2Field;
    DataSourceTextField cityField;
    DataSourceTextField stateField;
    DataSourceTextField zipField;

    DataSourceIntegerField companyIdField;

    DataSourceIntegerField enteredByField;
    DataSourceDateTimeField enteredDateField;
    DataSourceIntegerField editedByField;
    DataSourceDateTimeField editedDateField;

    DataSourceDateTimeField birthDateField;

    public ContactDS()
    {
        super();
        setID("ContactsGwtRpcDataSource");

        userIdField = new DataSourceIntegerField(Constants.CONTACT_USER_ID, null);
        userIdField.setCanEdit(false);
        userIdField.setHidden(true);

        contactIdField = new DataSourceIntegerField(Constants.CONTACT_ID, null);
        contactIdField.setPrimaryKey(true);
        contactIdField.setCanEdit(false);
        contactIdField.setHidden(true);

        prefixField = new DataSourceTextField(Constants.CONTACT_PREFIX, Constants.TITLE_CONTACT_PREFIX);
        firstNameField = new DataSourceTextField(Constants.CONTACT_FIRST_NAME, Constants.TITLE_CONTACT_FIRST_NAME, 200);
        middleNameField =
            new DataSourceTextField(Constants.CONTACT_MIDDLE_NAME, Constants.TITLE_CONTACT_MIDDLE_NAME, 200);
        lastNameField = new DataSourceTextField(Constants.CONTACT_LAST_NAME, Constants.TITLE_CONTACT_LAST_NAME, 200);
        suffixField = new DataSourceTextField(Constants.CONTACT_SUFFIX, Constants.TITLE_CONTACT_SUFFIX);

        address1Field = new DataSourceTextField(Constants.CONTACT_ADDRESS1, Constants.TITLE_CONTACT_ADDRESS1, 200);
        address2Field = new DataSourceTextField(Constants.CONTACT_ADDRESS2, Constants.TITLE_CONTACT_ADDRESS2, 200);
        cityField = new DataSourceTextField(Constants.CONTACT_CITY, Constants.TITLE_CONTACT_CITY, 200);
        stateField = new DataSourceTextField(Constants.CONTACT_STATE, Constants.TITLE_CONTACT_STATE, 200);
        zipField = new DataSourceTextField(Constants.CONTACT_ZIP, Constants.TITLE_CONTACT_ZIP, 200);

        companyIdField = new DataSourceIntegerField(Constants.CONTACT_COMPANY_ID, Constants.TITLE_CONTACT_COMPANY_ID);

        enteredByField = new DataSourceIntegerField(Constants.CONTACT_ENTERED_BY, Constants.TITLE_CONTACT_ENTERED_BY);
        enteredDateField =
            new DataSourceDateTimeField(Constants.CONTACT_ENTERED_DATE, Constants.TITLE_CONTACT_ENTERED_DATE);
        editedByField = new DataSourceIntegerField(Constants.CONTACT_EDITED_BY, Constants.TITLE_CONTACT_EDITED_BY);
        editedDateField =
            new DataSourceDateTimeField(Constants.CONTACT_EDITED_DATE, Constants.TITLE_CONTACT_EDITED_DATE);

        birthDateField = new DataSourceDateTimeField(Constants.CONTACT_BIRTHDATE, Constants.TITLE_CONTACT_BIRTHDATE);

        setFields(userIdField, contactIdField, prefixField, firstNameField, middleNameField, lastNameField,
            suffixField, address1Field, address2Field, cityField, stateField, zipField, companyIdField, enteredByField,
            enteredDateField, editedByField, editedDateField, birthDateField);
    }

    // *************************************************************************************
    // *************************************************************************************

    @Override
    protected void executeAdd(final String requestId, final DSRequest request, final DSResponse response)
    {
        // Retrieve record which should be added.
        JavaScriptObject data = request.getData();
        ListGridRecord rec = new ListGridRecord(data);
        ContactDTO testRec = new ContactDTO();
        copyValues(rec, testRec);
        service.add(testRec, new AsyncCallback<ContactDTO>()
        {
            public void onFailure(Throwable caught)
            {
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
            }

            public void onSuccess(ContactDTO result)
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
    protected void executeRemove(final String requestId, DSRequest request, final DSResponse response)
    {
        // Retrieve record which should be updated
        JavaScriptObject data = request.getData();
        final ListGridRecord rec = new ListGridRecord(data);
        ContactDTO testRec = new ContactDTO();
        // copyValues(rec, testRec);
        testRec.setId(rec.getAttributeAsInt(contactIdField.getName()));
        service.remove(testRec, new AsyncCallback<Void>()
        {
            @Override
            public void onFailure(Throwable caught)
            {
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
                SC.say("Contact Delete", "Contact has not been deleted!");
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
                SC.say("Contact Delete", "Contact has been deleted!");
            }
        });
    }

    @Override
    protected void executeFetch(final String requestId, DSRequest request, final DSResponse response)
    {

        // for this example I will do "paging" on client side
        final int startIndex = (request.getStartRow() < 0) ? 0 : request.getStartRow();
        final int endIndex = (request.getEndRow() == null) ? -1 : request.getEndRow();

        String userId = request.getCriteria().getAttribute("userId");
        UserDTO userDto = new UserDTO();
        userDto.setId(Long.parseLong(userId));

        service.fetch(userDto, new AsyncCallback<List<ContactDTO>>()
        {
            public void onFailure(Throwable caught)
            {
                caught.printStackTrace();
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
            }

            public void onSuccess(List<ContactDTO> result)
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
        ContactDTO testRec = new ContactDTO();
        copyValues(rec, testRec);
        service.update(testRec, new AsyncCallback<Void>()
        {
            @Override
            public void onFailure(Throwable caught)
            {
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
                SC.say("Contact Edit Save", "Contact edits have NOT been saved!");
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
                SC.say("Contact Edit Save", "Contact edits have been saved!");
            }
        });
    }

    // *************************************************************************************
    // *************************************************************************************

    private void copyValues(ListGridRecord from, ContactDTO to)
    {
        to.setUserId(from.getAttributeAsInt(userIdField.getName()));
        to.setId(from.getAttributeAsInt(contactIdField.getName()));
        // ================================================================================
        to.setPrefix(from.getAttributeAsString(prefixField.getName()));
        to.setFirstName(from.getAttributeAsString(firstNameField.getName()));
        to.setMiddleName(from.getAttributeAsString(middleNameField.getName()));
        to.setLastName(from.getAttributeAsString(lastNameField.getName()));
        to.setSuffix(from.getAttributeAsString(suffixField.getName()));
        // ================================================================================
        to.setAddress1(from.getAttributeAsString(address1Field.getName()));
        to.setAddress2(from.getAttributeAsString(address2Field.getName()));
        to.setCity(from.getAttributeAsString(cityField.getName()));
        to.setState(from.getAttributeAsString(stateField.getName()));
        to.setZip(from.getAttributeAsString(zipField.getName()));
        // ================================================================================
        to.setCompanyId(from.getAttributeAsInt(companyIdField.getName()));
        // ================================================================================
        to.setEnteredBy(from.getAttributeAsInt(enteredByField.getName()));
        to.setEditedBy(from.getAttributeAsInt(editedByField.getName()));
        to.setEnteredDate(from.getAttributeAsDate(enteredDateField.getName()));
        to.setEditedDate(from.getAttributeAsDate(editedDateField.getName()));
        // ================================================================================
        to.setBirthDate(from.getAttributeAsDate(birthDateField.getName()));
    }

    private static void copyValues(ContactDTO from, ListGridRecord to)
    {
        to.setAttribute(Constants.CONTACT_USER_ID, from.getUserId());
        to.setAttribute(Constants.CONTACT_ID, from.getId());
        // ================================================================================
        to.setAttribute(Constants.CONTACT_PREFIX, from.getPrefix());
        to.setAttribute(Constants.CONTACT_FIRST_NAME, from.getFirstName());
        to.setAttribute(Constants.CONTACT_MIDDLE_NAME, from.getMiddleName());
        to.setAttribute(Constants.CONTACT_LAST_NAME, from.getLastName());
        to.setAttribute(Constants.CONTACT_SUFFIX, from.getSuffix());
        // ================================================================================
        to.setAttribute(Constants.CONTACT_ADDRESS1, from.getAddress1());
        to.setAttribute(Constants.CONTACT_ADDRESS2, from.getAddress2());
        to.setAttribute(Constants.CONTACT_CITY, from.getCity());
        to.setAttribute(Constants.CONTACT_STATE, from.getState());
        to.setAttribute(Constants.CONTACT_ZIP, from.getZip());
        // ================================================================================
        to.setAttribute(Constants.CONTACT_ENTERED_BY, from.getEnteredBy());
        to.setAttribute(Constants.CONTACT_ENTERED_DATE, from.getEnteredDate());
        to.setAttribute(Constants.CONTACT_EDITED_BY, from.getEditedBy());
        to.setAttribute(Constants.CONTACT_EDITED_DATE, from.getEditedDate());
        // ================================================================================
        to.setAttribute(Constants.CONTACT_BIRTHDATE, from.getBirthDate());
        to.setAttribute(Constants.CONTACT_COMPANY_ID, from.getCompanyId());
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
