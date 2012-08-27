package com.opensource.phonebook.client.datasource;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.client.services.LinkTypeService;
import com.opensource.phonebook.client.services.LinkTypeServiceAsync;
import com.opensource.phonebook.client.utils.datasource.GwtRpcDataSource;
import com.opensource.phonebook.shared.dto.LinkTypeDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class LinkTypesDS extends GwtRpcDataSource
{
    private static LinkTypesDS instance = null;

    private final LinkTypeServiceAsync service = GWT.create(LinkTypeService.class);

    public static LinkTypesDS getInstance()
    {
        if (instance == null)
        {
            instance = new LinkTypesDS();
        }
        return instance;
    }

    DataSourceIntegerField linkTypeIdField;
    DataSourceBooleanField linkTypeActiveField;
    DataSourceTextField linkTypeDescriptionField;

    public LinkTypesDS()
    {
        super();
        setID("LinkTypesGWTRPCDataSource");

        linkTypeIdField = new DataSourceIntegerField("linkTypeId", null);
        linkTypeIdField.setPrimaryKey(true);

        linkTypeActiveField = new DataSourceBooleanField("linkTypeActive", null);
        linkTypeDescriptionField = new DataSourceTextField("linkTypeDescription", null);

        setFields(linkTypeIdField, linkTypeDescriptionField, linkTypeActiveField);

    }

    // *************************************************************************************
    // *************************************************************************************

    @Override
    protected void executeAdd(final String requestId, final DSRequest request, final DSResponse response)
    {
        // Retrieve record which should be added.
        JavaScriptObject data = request.getData();
        final ListGridRecord rec = new ListGridRecord(data);
        LinkTypeDTO testRec = new LinkTypeDTO();
        copyValues(rec, testRec);
        service.add(testRec, new AsyncCallback<LinkTypeDTO>()
        {
            public void onFailure(Throwable caught)
            {
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
            }

            public void onSuccess(LinkTypeDTO arg0)
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
        LinkTypeDTO testRec = new LinkTypeDTO();
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
        // final int startIndex = (request.getStartRow() < 0) ? 0 : request.getStartRow();
        // final int endIndex = (request.getEndRow() == null) ? -1 : request.getEndRow();

        final int startIndex = 0;
        final int endIndex = 10;

        service.fetch(new AsyncCallback<List<LinkTypeDTO>>()
        {
            public void onFailure(Throwable caught)
            {
                caught.printStackTrace();
                response.setStatus(RPCResponse.STATUS_FAILURE);
                processResponse(requestId, response);
            }

            public void onSuccess(List<LinkTypeDTO> result)
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

    private void copyValues(ListGridRecord from, LinkTypeDTO to)
    {
        to.setId(from.getAttributeAsInt(linkTypeIdField.getAttribute("linkTypeId")));
        to.setDescription(from.getAttributeAsString(linkTypeDescriptionField.getName()));
        to.setActive(from.getAttributeAsBoolean(linkTypeActiveField.getName()));
    }

    private static void copyValues(LinkTypeDTO from, ListGridRecord to)
    {
        to.setAttribute("linkTypeId", from.getId());
        to.setAttribute("linkTypeActive", from.isActive());
        to.setAttribute("linkTypeDescription", from.getDescription());
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
