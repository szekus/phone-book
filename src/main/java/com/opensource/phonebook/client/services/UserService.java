package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.opensource.phonebook.shared.dto.UserDTO;

@RemoteServiceRelativePath("userRpc")
public interface UserService extends RemoteService  {

	UserDTO add(UserDTO record);
	void remove(UserDTO record);
	void update(UserDTO record);
	List<UserDTO> fetch(UserDTO record);
	List<UserDTO> fetch ();
	UserDTO fetch(long id);
	
}
