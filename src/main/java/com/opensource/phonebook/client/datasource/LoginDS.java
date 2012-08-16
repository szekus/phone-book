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

public class LoginDS extends BaseDS
{
	static private LoginDS instance = null;
	
	static public LoginDS getInstance()
	{
		if (instance==null)
		{
			instance = new LoginDS("loginDataSource");
		}
		return instance;
	}
	
	protected LoginDS(String DSID)
	{
		super(DSID);
		ClientResources resources = (ClientResources)GWT.create(ClientResources.class);
		DataSourceTextField user = new DataSourceTextField("username", resources.login_userLabel());  
		DataSourceTextField password = new DataSourceTextField("password", resources.login_passwordLabel());  
		DataSourceTextField passwordHash = new DataSourceTextField("passwordHash");  
		setFields(user, password, passwordHash);  
		user.setPrimaryKey(true); 
		setClientOnly(true);
	}
	
	protected void transformResponse(DSResponse response, DSRequest request, Object jsonData)
	{
		// this is absurd, there must be a better way
		int status = (int)(((JSONNumber)(XMLTools.selectObjects(jsonData, "/response/status").get(0))).doubleValue());
		
		if(status!=RPCResponse.STATUS_SUCCESS)
		{  
			response.setStatus(RPCResponse.STATUS_VALIDATION_ERROR);  
			JSONArray errors = XMLTools.selectObjects(jsonData, "/response/errors");  
			response.setErrors(errors.getJavaScriptObject());  
		}  
		else
		{
			response.setStatus(RPCResponse.STATUS_SUCCESS);
			response.setAttribute("username", ((JSONString)(((JSONObject)(XMLTools.selectObjects(jsonData, "/response/data").get(0))).get("userID"))).stringValue());
			//response.setAttribute("phoneBookID", ((JSONString)(((JSONObject)(XMLTools.selectObjects(jsonData, "/response/data").get(0))).get("phoneBookID"))).stringValue());
			response.setAttribute("userType", ((JSONString)(((JSONObject)(XMLTools.selectObjects(jsonData, "/response/data").get(0))).get("userType"))).stringValue());
			JSONValue phoneBookIDValue = (((JSONObject)(XMLTools.selectObjects(jsonData, "/response/data").get(0))).get("phoneBookID"));
			if (phoneBookIDValue != null)
			{
				response.setAttribute("phoneBookID", ((JSONString)phoneBookIDValue).stringValue());
			}
			JSONValue validPhoneBookIDValue = (((JSONObject)(XMLTools.selectObjects(jsonData, "/response/data").get(0))).get("validPhoneBookID"));
			if (validPhoneBookIDValue != null)
			{
				response.setAttribute("validPhoneBookID", ((JSONString)validPhoneBookIDValue).stringValue());
			}
		}
	}  
}
