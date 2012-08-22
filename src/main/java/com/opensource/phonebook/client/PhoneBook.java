package com.opensource.phonebook.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PhoneBook implements EntryPoint {
		
	public void onModuleLoad() {
		LoginDialog.getInstance().displayIfRequired(null);
	}  
}
