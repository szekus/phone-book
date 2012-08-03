package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.opensource.phonebook.shared.dto.LinkTypeDTO;

@RemoteServiceRelativePath("linkTypeRpc")
public interface LinkTypeService extends RemoteService  {

	LinkTypeDTO add(LinkTypeDTO record);
	void remove(LinkTypeDTO record);
	void update(LinkTypeDTO record);
	List<LinkTypeDTO> fetch(LinkTypeDTO record);
	List<LinkTypeDTO> fetch ();
	LinkTypeDTO fetch(long id);
	
}

