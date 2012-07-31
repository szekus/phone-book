package com.opensource.phonebook.client.datasource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.opensource.phonebook.client.ClientResources;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.XMLTools;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;

public class EnrolmentDS extends BaseDS {
	
	static private EnrolmentDS instance = null;
	
	static public EnrolmentDS getInstance() {
		if (instance==null) {
			instance = new EnrolmentDS("enrolmentDataSource");
		}
		return instance;
	}
	
	protected EnrolmentDS(String DSID) {
		super(DSID);
		ClientResources resources = (ClientResources)GWT.create(ClientResources.class);
		//DataSourceTextField enrolmentType = new DataSourceTextField("enrolmentType", resources.enroll_typePrompt());  
		//DataSourceBooleanField anonymous = new DataSourceBooleanField("anonymous", resources.enroll_anonymous());  
		DataSourceTextField userID = new DataSourceTextField("userID", resources.enroll_userName());  
		DataSourceTextField password = new DataSourceTextField("password", resources.enroll_password());  
		DataSourceTextField passwordAgain = new DataSourceTextField("passwordAgain", resources.enroll_passwordAgain());  
		/*DataSourceTextField email = new DataSourceTextField("email", resources.enroll_email());  
		DataSourceTextField lastName = new DataSourceTextField("lastName", resources.enroll_lastName());  
		DataSourceTextField firstName = new DataSourceTextField("firstName", resources.enroll_firstName());  
		DataSourceDateField dob = new DataSourceDateField("dob", resources.enroll_dob());  
		DataSourceTextField question = new DataSourceTextField("question", resources.enroll_question());  
		DataSourceTextField answer = new DataSourceTextField("answer", resources.enroll_answer());  */
		//setFields(enrolmentType, anonymous, userID, password, passwordAgain, email, lastName, firstName, dob, question, answer);  
		//setFields(userID, password, passwordAgain, email, lastName, firstName, dob, question, answer);
		setFields(userID, password, passwordAgain);
		userID.setPrimaryKey(true); 
		setDataURL(GWT.getModuleBaseURL()+"/enrolmentEP");  		
	}
	protected void transformResponse(DSResponse response, DSRequest request, Object jsonData) {
		int status = (int)(((JSONNumber)(XMLTools.selectObjects(jsonData, "/response/status").get(0))).doubleValue());
		if(status!=RPCResponse.STATUS_SUCCESS) {  
			response.setStatus(RPCResponse.STATUS_VALIDATION_ERROR);  
			JSONArray errors = XMLTools.selectObjects(jsonData, "/response/errors");  
			response.setErrors(errors.getJavaScriptObject());  
		}  
		else {
			response.setStatus(RPCResponse.STATUS_SUCCESS);
			response.setAttribute("userID", ((JSONString)(((JSONObject)(XMLTools.selectObjects(jsonData, "/response/data").get(0))).get("userID"))).stringValue());
			response.setAttribute("offerSafeID", ((JSONString)(((JSONObject)(XMLTools.selectObjects(jsonData, "/response/data").get(0))).get("offerSafeID"))).stringValue());
			response.setAttribute("validOfferSafeID", ((JSONString)(((JSONObject)(XMLTools.selectObjects(jsonData, "/response/data").get(0))).get("validOfferSafeID"))).stringValue());
		}
	}  

}
