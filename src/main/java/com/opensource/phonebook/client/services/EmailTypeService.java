package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.opensource.phonebook.shared.dto.EmailTypeDTO;

@RemoteServiceRelativePath("emailTypeRpc")
public interface EmailTypeService extends RemoteService  {

	EmailTypeDTO add(EmailTypeDTO record);
	void remove(EmailTypeDTO record);
	void update(EmailTypeDTO record);
	List<EmailTypeDTO> fetch(EmailTypeDTO record);
	List<EmailTypeDTO> fetch ();
	EmailTypeDTO fetch(long id);
	
}
