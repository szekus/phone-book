package com.opensource.phonebook.server.dao;

import java.util.List;

import com.opensource.phonebook.shared.dto.ContactDTO;

public interface ContactDao {
	
	public ContactDTO saveContactDTO(ContactDTO contact);
	
	public ContactDTO createContactDTO(ContactDTO contact);
	
	public ContactDTO updateContactDTO(ContactDTO contact);
	
	public void deleteContactDTO(Long contactId);
	public void deleteContactDTO(ContactDTO contact);
	
	public List<ContactDTO> getAllContactDTOs();
	
	// Retrieve
	public ContactDTO getContactDTO(long id);
	public List<ContactDTO> getContactDTO(ContactDTO exampleEntity);
	
}


