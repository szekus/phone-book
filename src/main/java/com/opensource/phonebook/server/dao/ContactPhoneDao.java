package com.opensource.phonebook.server.dao;

import java.util.List;

import com.opensource.phonebook.shared.dto.ContactPhoneDTO;

public interface ContactPhoneDao {
	
	public ContactPhoneDTO saveContactPhoneDTO(ContactPhoneDTO contactPhone);
	
	public ContactPhoneDTO createContactPhoneDTO(ContactPhoneDTO contactPhone);
	
	public ContactPhoneDTO updateContactPhoneDTO(ContactPhoneDTO contactPhone);
	
	public void deleteContactPhoneDTO(Long contactPhoneId);
	public void deleteContactPhoneDTO(ContactPhoneDTO contactPhone);
	
	public List<ContactPhoneDTO> getAllContactPhoneDTOs();
	
	// Retrieve
	public ContactPhoneDTO getContactPhoneDTO(long id);
	public List<ContactPhoneDTO> getContactPhoneDTO(ContactPhoneDTO exampleEntity);
	
}
