package com.opensource.phonebook.server.dao;

import java.util.List;

import com.opensource.phonebook.domain.ContactEmailEntity;

public interface ContactEmailDao {
	
	public ContactEmailEntity saveContactEmailEntity(ContactEmailEntity contactEmail);
	
	public ContactEmailEntity createContactEmailEntity(ContactEmailEntity contactEmail);
	
	public ContactEmailEntity updateContactEmailEntity(ContactEmailEntity contactEmail);
	
	public void deleteContactEmailEntity(Long contactEmailId);
	public void deleteContactEmailEntity(ContactEmailEntity contactEmail);
	
	public List<ContactEmailEntity> getAllContactEmailEntitys();
	
	// Retrieve
	public ContactEmailEntity getContactEmailEntity(long id);
	public List<ContactEmailEntity> getContactEmailEntity(ContactEmailEntity exampleEntity);
	
}
