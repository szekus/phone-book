package com.opensource.phonebook.client.datasource;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.client.services.PhoneTypeService;
import com.opensource.phonebook.client.services.PhoneTypeServiceAsync;
import com.opensource.phonebook.client.utils.datasource.GwtRpcDataSource;
import com.opensource.phonebook.shared.dto.PhoneTypeDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class PhoneTypesDS extends GwtRpcDataSource
{

    private static PhoneTypesDS instance = null;

    private final PhoneTypeServiceAsync service = GWT.create(PhoneTypeService.class);

    public static PhoneTypesDS getInstance()
    {
        if (instance == null)
        {
            instance = new PhoneTypesDS();
        }
        return instance;
    }

    DataSourceIntegerField phoneTypeIdField;
    DataSourceBooleanField phoneTypeActiveField;
    DataSourceTextField phoneTypeDescriptionField;

    public PhoneTypesDS()
    {
        super();
        setID("PhoneTypesGWTRPCDataSource");

        phoneTypeIdField = new DataSourceIntegerField("phoneTypeId", null);
        phoneTypeIdField.setPrimaryKey(true);

        phoneTypeActiveField = new DataSourceBooleanField("phoneTypeActive", null);
        phoneTypeDescriptionField = new DataSourceTextField("phoneTypeDescription", null);

        setFields(phoneTypeIdField, phoneTypeDescriptionField, phoneTypeActiveField);

    }

    // *************************************************************************************
    // *************************************************************************************

    @Override
    protected void executeAdd(final String requestId, final DSRequest request, final DSResponse response)
    {
        // Retrieve record which should be added.
        JavaScriptObject data = request.getData();
        final ListGridRecord rec = new ListGridRecord(data);
        PhoneTypeDTO testRec = new PhoneTypeDTO();
        copyValues(rec, testRec);
        service.add(testRec, new AsyncCallback<PhoneTypeDTO>()
        {
            public void onFailure(Throwable caught)
            {
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
            }

            public void onSuccess(PhoneTypeDTO arg0)
            {
                ListGridRecord[] list = new ListGridRecord[1];
                list[0] = rec;
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
        PhoneTypeDTO testRec = new PhoneTypeDTO();
        copyValues(rec, testRec);
        service.remove(testRec, new AsyncCallback<Void>()
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

        service.fetch(new AsyncCallback<List<PhoneTypeDTO>>()
        {
            public void onFailure(Throwable caught)
            {
                caught.printStackTrace();
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
            }

            public void onSuccess(List<PhoneTypeDTO> result)
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

    // DataSourceTextField communityIdField; //private String communityId;
    // DataSourceEnumField communityInterestIdsField; //private String
    // communityInterestIds;
    // DataSourceEnumField consumerInterestIdsField; //private String
    // consumerInterestIds;

    private void copyValues(ListGridRecord from, PhoneTypeDTO to)
    {
        to.setId(from.getAttributeAsInt(phoneTypeIdField.getAttribute("phoneTypeId")));
        to.setDescription(from.getAttributeAsString(phoneTypeDescriptionField.getName()));
        to.setActive(from.getAttributeAsBoolean(phoneTypeActiveField.getName()));
    }

    private static void copyValues(PhoneTypeDTO from, ListGridRecord to)
    {
        to.setAttribute("phoneTypeId", from.getId());
        to.setAttribute("phoneTypeActive", from.isActive());
        to.setAttribute("phoneTypeDescription", from.getDescription());
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
