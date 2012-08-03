package com.opensource.phonebook.server.dao;

import java.util.List;

import com.opensource.phonebook.shared.dto.ContactEmailDTO;

public interface ContactEmailDao {
	
	public ContactEmailDTO saveContactEmailDTO(ContactEmailDTO contactEmail);
	
	public ContactEmailDTO createContactEmailDTO(ContactEmailDTO contactEmail);
	
	public ContactEmailDTO updateContactEmailDTO(ContactEmailDTO contactEmail);
	
	public void deleteContactEmailDTO(Long contactEmailId);
	public void deleteContactEmailDTO(ContactEmailDTO contactEmail);
	
	public List<ContactEmailDTO> getAllContactEmailDTOs();
	
	// Retrieve
	public ContactEmailDTO getContactEmailDTO(long id);
	public List<ContactEmailDTO> getContactEmailDTO(ContactEmailDTO exampleEntity);
	
}
