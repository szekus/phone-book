package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.shared.dto.UserDTO;

public interface UserServiceAsync {

	void add(UserDTO record, AsyncCallback<UserDTO> callback);
	void remove(UserDTO record, AsyncCallback<Void> callback);
	void update(UserDTO record, AsyncCallback<Void> callback);
	void fetch (UserDTO record, AsyncCallback<List<UserDTO>> callback);
	void fetch(AsyncCallback<List<UserDTO>> callback);
	void fetch(long id, AsyncCallback<UserDTO> callback);

}