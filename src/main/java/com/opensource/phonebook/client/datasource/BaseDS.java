package com.opensource.phonebook.client.datasource;

import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSProtocol;

public class BaseDS extends RestDataSource {
	protected BaseDS(String DSID) {
		setID(DSID);
		setDataFormat(DSDataFormat.JSON);
		setDataProtocol(DSProtocol.POSTPARAMS);
		setRecordXPath("/response/data");
	}
}
