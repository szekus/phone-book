package com.opensource.phonebook.server.dao;

import java.util.List;

import com.opensource.phonebook.domain.ContactEntity;
import com.opensource.phonebook.domain.UserEntity;

public interface ContactDao
{

    public ContactEntity saveContactEntity(ContactEntity contact);

    public ContactEntity createContactEntity(ContactEntity contact);

    public ContactEntity updateContactEntity(ContactEntity contact);

    public void deleteContactEntity(long contactId);

    public void deleteContactEntity(ContactEntity contact);

    public List<ContactEntity> getAllContactEntitys();

    // Retrieve
    public ContactEntity getContactEntity(long id);

    public List<ContactEntity> getContactEntity(ContactEntity exampleEntity);

    List<ContactEntity> getContactEntityByUser(UserEntity exampleEntity);

}
