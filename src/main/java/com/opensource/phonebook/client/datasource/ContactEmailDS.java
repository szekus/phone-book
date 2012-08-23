package com.opensource.phonebook.client.datasource;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.client.services.ContactEmailService;
import com.opensource.phonebook.client.services.ContactEmailServiceAsync;
import com.opensource.phonebook.client.utils.datasource.GwtRpcDataSource;
import com.opensource.phonebook.shared.Constants;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactEmailDTO;
import com.opensource.phonebook.shared.dto.EmailTypeDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceDateTimeField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ContactEmailDS extends GwtRpcDataSource
{
    private static ContactEmailDS instance = null;

    private final ContactEmailServiceAsync contactEmailService = GWT.create(ContactEmailService.class);

    public static ContactEmailDS getInstance()
    {
        if (instance == null)
        {
            instance = new ContactEmailDS();
        }
        return instance;
    }

    DataSourceIntegerField contactIdField;
    DataSourceIntegerField contactEmailIdField;
    DataSourceTextField emailTypeIdField;
    DataSourceTextField emailField;
    DataSourceDateTimeField enteredDateField;

    public ContactEmailDS()
    {
        super();
        setID("ContactEmailsGwtRpcDataSource");

        contactIdField = new DataSourceIntegerField(Constants.EMAIL_CONTACT_ID, null);
        contactIdField.setCanEdit(false);
        contactIdField.setHidden(true);

        contactEmailIdField = new DataSourceIntegerField(Constants.EMAIL_ID, null);
        contactEmailIdField.setPrimaryKey(true);
        contactEmailIdField.setCanEdit(false);
        contactEmailIdField.setHidden(true);

        emailField = new DataSourceTextField(Constants.EMAIL_ADDRESS, Constants.TITLE_EMAIL_ADDRESS);

        emailTypeIdField = new DataSourceTextField(Constants.EMAIL_TYPE_ID, Constants.TITLE_EMAIL_TYPE_ID);

        enteredDateField =
            new DataSourceDateTimeField(Constants.EMAIL_ENTERED_DATE, Constants.TITLE_EMAIL_ENTERED_DATE);

        setFields(contactIdField, contactEmailIdField, emailField, emailTypeIdField, enteredDateField);
    }

    // *************************************************************************************
    // *************************************************************************************

    @Override
    protected void executeAdd(final String requestId, final DSRequest request, final DSResponse response)
    {
        // Retrieve record which should be added.
        JavaScriptObject data = request.getData();
        ListGridRecord rec = new ListGridRecord(data);
        ContactEmailDTO testRec = new ContactEmailDTO();
        copyValues(rec, testRec);
        contactEmailService.add(testRec, new AsyncCallback<ContactEmailDTO>()
        {
            public void onFailure(Throwable caught)
            {
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
            }

            public void onSuccess(ContactEmailDTO result)
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
        ContactEmailDTO testRec = new ContactEmailDTO();
        copyValues(rec, testRec);
        contactEmailService.remove(testRec, new AsyncCallback<Void>()
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

        String contactId = request.getCriteria().getAttribute("contactId");
        ContactDTO contactDto = new ContactDTO();
        contactDto.setId(Long.parseLong(contactId));

        contactEmailService.fetch(contactDto, new AsyncCallback<List<ContactEmailDTO>>()
        {
            public void onFailure(Throwable caught)
            {
                caught.printStackTrace();
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
            }

            public void onSuccess(List<ContactEmailDTO> result)
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
    protected void executeUpdate(String requestId, DSRequest request, DSResponse response)
    {
        // TODO Auto-generated method stub
    }

    // *************************************************************************************
    // *************************************************************************************

    private void copyValues(ListGridRecord from, ContactEmailDTO to)
    {
        to.setContactId(from.getAttributeAsInt(contactIdField.getName()));
        to.setEmailId(from.getAttributeAsInt(contactEmailIdField.getName()));
        to.setEmail(from.getAttributeAsString(emailField.getName()));
        to.setEnteredDate(from.getAttributeAsDate(enteredDateField.getName()));
        // ================================================================================
        EmailTypeDTO emailType = new EmailTypeDTO();
        emailType.setId(from.getAttributeAsInt(emailTypeIdField.getName()));
        to.setEmailType(emailType);
    }

    private static void copyValues(ContactEmailDTO from, ListGridRecord to)
    {
        to.setAttribute(Constants.EMAIL_CONTACT_ID, from.getContactId());
        to.setAttribute(Constants.EMAIL_ID, from.getEmailId());
        to.setAttribute(Constants.EMAIL_ADDRESS, from.getEmail());
        to.setAttribute(Constants.EMAIL_ENTERED_DATE, from.getEnteredDate());
        to.setAttribute(Constants.EMAIL_TYPE_ID, from.getEmailType().getId());
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
