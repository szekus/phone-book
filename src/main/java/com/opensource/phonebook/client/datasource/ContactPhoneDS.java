package com.opensource.phonebook.client.datasource;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.client.services.ContactPhoneService;
import com.opensource.phonebook.client.services.ContactPhoneServiceAsync;
import com.opensource.phonebook.client.utils.datasource.GwtRpcDataSource;
import com.opensource.phonebook.shared.Constants;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactPhoneDTO;
import com.opensource.phonebook.shared.dto.PhoneTypeDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceDateTimeField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ContactPhoneDS extends GwtRpcDataSource
{
    private static ContactPhoneDS instance = null;

    private final ContactPhoneServiceAsync contactPhoneService = GWT.create(ContactPhoneService.class);

    public static ContactPhoneDS getInstance()
    {
        if (instance == null)
        {
            instance = new ContactPhoneDS();
        }
        return instance;
    }

    DataSourceIntegerField contactIdField;
    DataSourceIntegerField contactPhoneIdField;
    DataSourceTextField phoneTypeIdField;
    DataSourceTextField phoneField;
    DataSourceDateTimeField enteredDateField;

    public ContactPhoneDS()
    {
        super();
        setID("ContactPhonesGwtRpcDataSource");

        contactIdField = new DataSourceIntegerField(Constants.C_PHONE_CONTACT_ID, null);
        contactIdField.setCanEdit(false);
        contactIdField.setHidden(true);

        contactPhoneIdField = new DataSourceIntegerField(Constants.C_PHONE_ID, null);
        contactPhoneIdField.setPrimaryKey(true);
        contactPhoneIdField.setCanEdit(false);
        contactPhoneIdField.setHidden(true);

        phoneField = new DataSourceTextField(Constants.C_PHONE_NUMBER, Constants.TITLE_C_PHONE_NUMBER);

        phoneTypeIdField = new DataSourceTextField(Constants.PHONE_TYPE_ID, Constants.TITLE_PHONE_TYPE_ID);

        enteredDateField =
            new DataSourceDateTimeField(Constants.C_PHONE_ENTERED_DATE, Constants.TITLE_C_PHONE_ENTERED_DATE);

        setFields(contactIdField, contactPhoneIdField, phoneField, phoneTypeIdField, enteredDateField);
    }

    // *************************************************************************************
    // *************************************************************************************

    @Override
    protected void executeAdd(final String requestId, final DSRequest request, final DSResponse response)
    {
        // Retrieve record which should be added.
        JavaScriptObject data = request.getData();
        ListGridRecord rec = new ListGridRecord(data);
        ContactPhoneDTO testRec = new ContactPhoneDTO();
        copyValues(rec, testRec);
        contactPhoneService.add(testRec, new AsyncCallback<ContactPhoneDTO>()
        {
            public void onFailure(Throwable caught)
            {
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
            }

            public void onSuccess(ContactPhoneDTO result)
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
        ContactPhoneDTO testRec = new ContactPhoneDTO();
        copyValues(rec, testRec);
        contactPhoneService.remove(testRec, new AsyncCallback<Void>()
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

        String contactId = request.getCriteria().getAttribute(Constants.C_PHONE_CONTACT_ID);
        ContactDTO contactDto = new ContactDTO();
        contactDto.setId(Long.parseLong(contactId));

        contactPhoneService.fetch(contactDto, new AsyncCallback<List<ContactPhoneDTO>>()
        {
            public void onFailure(Throwable caught)
            {
                caught.printStackTrace();
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
            }

            public void onSuccess(List<ContactPhoneDTO> result)
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

    private void copyValues(ListGridRecord from, ContactPhoneDTO to)
    {
        to.setContactId(from.getAttributeAsInt(contactIdField.getName()));
        to.setPhoneId(from.getAttributeAsInt(contactPhoneIdField.getName()));
        to.setPhone(from.getAttributeAsString(phoneField.getName()));
        to.setEnteredDate(from.getAttributeAsDate(enteredDateField.getName()));
        // ================================================================================
        PhoneTypeDTO phoneType = new PhoneTypeDTO();
        phoneType.setId(from.getAttributeAsInt(phoneTypeIdField.getName()));
        to.setPhoneType(phoneType);
    }

    private static void copyValues(ContactPhoneDTO from, ListGridRecord to)
    {
        to.setAttribute(Constants.C_PHONE_CONTACT_ID, from.getContactId());
        to.setAttribute(Constants.C_PHONE_ID, from.getPhoneId());
        to.setAttribute(Constants.C_PHONE_NUMBER, from.getPhone());
        to.setAttribute(Constants.C_PHONE_ENTERED_DATE, from.getEnteredDate());
        to.setAttribute(Constants.C_PHONE_TYPE_ID, from.getPhoneType().getId());
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
