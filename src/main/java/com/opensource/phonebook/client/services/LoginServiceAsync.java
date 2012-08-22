package com.opensource.phonebook.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.shared.dto.UserDTO;

/**
 * The async counterpart of <code>LoginService</code>.
 */
public interface LoginServiceAsync
{
	void login(String username, String password, AsyncCallback<UserDTO> callback);
	
	void login(String id, AsyncCallback<UserDTO> callback);
}
