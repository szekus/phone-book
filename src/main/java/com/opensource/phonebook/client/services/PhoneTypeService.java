package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.opensource.phonebook.shared.dto.PhoneTypeDTO;

@RemoteServiceRelativePath("phoneTypeRpc")
public interface PhoneTypeService extends RemoteService  {

	PhoneTypeDTO add(PhoneTypeDTO record);
	void remove(PhoneTypeDTO record);
	void update(PhoneTypeDTO record);
	List<PhoneTypeDTO> fetch(PhoneTypeDTO record);
	List<PhoneTypeDTO> fetch ();
	PhoneTypeDTO fetch(long id);
	
}

