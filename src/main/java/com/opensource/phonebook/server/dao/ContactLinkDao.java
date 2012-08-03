package com.opensource.phonebook.server.dao;

import java.util.List;

import com.opensource.phonebook.shared.dto.ContactLinkDTO;

public interface ContactLinkDao {
	
	public ContactLinkDTO saveContactLinkDTO(ContactLinkDTO contactLink);
	
	public ContactLinkDTO createContactLinkDTO(ContactLinkDTO contactLink);
	
	public ContactLinkDTO updateContactLinkDTO(ContactLinkDTO contactLink);
	
	public void deleteContactLinkDTO(Long contactLinkId);
	public void deleteContactLinkDTO(ContactLinkDTO contactLink);
	
	public List<ContactLinkDTO> getAllContactLinkDTOs();
	
	// Retrieve
	public ContactLinkDTO getContactLinkDTO(long id);
	public List<ContactLinkDTO> getContactLinkDTO(ContactLinkDTO exampleEntity);
	
}
