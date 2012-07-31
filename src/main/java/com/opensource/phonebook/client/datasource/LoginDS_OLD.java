package com.opensource.phonebook.client.datasource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.opensource.phonebook.client.ClientResources;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.XMLTools;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;

public class LoginDS_OLD extends BaseDS {
	
	static private LoginDS_OLD instance = null;
	
	static public LoginDS_OLD getInstance() {
		if (instance==null) {
			instance = new LoginDS_OLD("loginDataSource");
		}
		return instance;
	}
	
	protected LoginDS_OLD(String DSID) {
		super(DSID);
		ClientResources resources = (ClientResources)GWT.create(ClientResources.class);
		DataSourceTextField user = new DataSourceTextField("userID", resources.login_userLabel());  
		DataSourceTextField password = new DataSourceTextField("password", resources.login_passwordLabel());  
		DataSourceTextField passwordHash = new DataSourceTextField("passwordHash");  
		setFields(user, password, passwordHash);  
		user.setPrimaryKey(true); 
		setDataURL(GWT.getModuleBaseURL()+"/loginEP");  		
	}
	
	protected void transformResponse(DSResponse response, DSRequest request, Object jsonData) {
		// this is absurd, there must be a better way
		int status = (int)(((JSONNumber)(XMLTools.selectObjects(jsonData, "/response/status").get(0))).doubleValue());
		if(status!=RPCResponse.STATUS_SUCCESS) {  
			response.setStatus(RPCResponse.STATUS_VALIDATION_ERROR);  
			JSONArray errors = XMLTools.selectObjects(jsonData, "/response/errors");  
			response.setErrors(errors.getJavaScriptObject());  
		}  else {
			response.setStatus(RPCResponse.STATUS_SUCCESS);
			response.setAttribute("userID", ((JSONString)(((JSONObject)(XMLTools.selectObjects(jsonData, "/response/data").get(0))).get("userID"))).stringValue());
			//response.setAttribute("offerSafeID", ((JSONString)(((JSONObject)(XMLTools.selectObjects(jsonData, "/response/data").get(0))).get("offerSafeID"))).stringValue());
			response.setAttribute("userType", ((JSONString)(((JSONObject)(XMLTools.selectObjects(jsonData, "/response/data").get(0))).get("userType"))).stringValue());
			JSONValue offerSafeIDValue = (((JSONObject)(XMLTools.selectObjects(jsonData, "/response/data").get(0))).get("offerSafeID"));
			if (offerSafeIDValue != null)
			{
				response.setAttribute("offerSafeID", ((JSONString)offerSafeIDValue).stringValue());
			}
			JSONValue validOfferSafeIDValue = (((JSONObject)(XMLTools.selectObjects(jsonData, "/response/data").get(0))).get("validOfferSafeID"));
			if (validOfferSafeIDValue != null)
			{
				response.setAttribute("validOfferSafeID", ((JSONString)validOfferSafeIDValue).stringValue());
			}
		}
	}  
}
