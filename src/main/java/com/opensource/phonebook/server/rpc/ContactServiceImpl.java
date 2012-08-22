package com.opensource.phonebook.server.rpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.client.services.ContactService;
import com.opensource.phonebook.domain.ContactEntity;
import com.opensource.phonebook.domain.UserEntity;
import com.opensource.phonebook.server.dao.ContactDao;
import com.opensource.phonebook.shared.Mapping;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.UserDTO;

@SuppressWarnings("serial")
@Transactional
@Service("contactService")
public class ContactServiceImpl extends BaseRPC implements ContactService
{

    @Autowired
    private ContactDao contactDao;

    public ContactDao getContactDao()
    {
        return contactDao;
    }

    public void setContactDao(ContactDao contactDao)
    {
        this.contactDao = contactDao;
    }

    @Transactional
    public ContactDTO add(ContactDTO contactDto)
    {
        UserEntity user = new UserEntity();
        user.setId(contactDto.getUserId());

        ContactEntity newContact = new ContactEntity();
        newContact.setUser(user);

        newContact.setId(contactDto.getId());
        newContact.setPrefix(contactDto.getPrefix());
        newContact.setFirstName(contactDto.getFirstName());
        newContact.setMiddleName(contactDto.getMiddleName());
        newContact.setLastName(contactDto.getLastName());
        newContact.setSuffix(contactDto.getSuffix());
        // ***************************************************
        newContact.setAddress1(contactDto.getAddress1());
        newContact.setAddress2(contactDto.getAddress2());
        newContact.setCity(contactDto.getCity());
        newContact.setState(contactDto.getState());
        newContact.setZip(contactDto.getZip());
        // ***************************************************
        newContact.setEnteredBy(contactDto.getEditedBy());
        newContact.setEnteredDate(contactDto.getEnteredDate());
        newContact.setEditedBy(contactDto.getEditedBy());
        newContact.setEditedDate(contactDto.getEditedDate());
        // ***************************************************
        newContact.setCompanyId(contactDto.getCompanyId());
        newContact.setAdmin(contactDto.isAdmin());
        newContact.setBirthDate(contactDto.getBirthDate());
        // ***************************************************
        ContactEntity contactEntity = contactDao.createContactEntity(newContact);
        ContactDTO newContactDto = Mapping.createContact(contactEntity);
        return newContactDto;
    }

    @Transactional
    public void remove(ContactDTO record)
    {
        // TODO Auto-generated method stub
    }

    @Transactional
    public void update(ContactDTO record)
    {
        // TODO Auto-generated method stub
    }

    @Transactional
    public List<ContactDTO> fetch(ContactDTO exampleEntity)
    {
        List<ContactDTO> contactList = null;
        if (exampleEntity != null)
        {
            ContactEntity exampleContactEntity = new ContactEntity();
            exampleContactEntity.setId(exampleEntity.getId());
            exampleContactEntity.setFirstName(exampleEntity.getFirstName());
            exampleContactEntity.setLastName(exampleEntity.getLastName());

            List<ContactEntity> contactEntityList = contactDao.getContactEntity(exampleContactEntity);
            if (contactEntityList != null)
            {
                contactList = new ArrayList<ContactDTO>();
                for (ContactEntity contactEntity : contactEntityList)
                {
                    ContactDTO contactDTO = Mapping.createContact(contactEntity);
                    if (contactDTO != null)
                    {
                        contactList.add(contactDTO);
                    }
                }
            }
        }
        return contactList;
    }

    @Transactional
    public List<ContactDTO> fetch(UserDTO exampleEntity)
    {
        List<ContactDTO> contactList = null;
        if (exampleEntity != null)
        {
            UserEntity exampleUserEntity = new UserEntity();
            exampleUserEntity.setId(exampleEntity.getId());

            List<ContactEntity> contactEntityList = contactDao.getContactEntityByUser(exampleUserEntity);
            if (contactEntityList != null)
            {
                contactList = new ArrayList<ContactDTO>();
                for (ContactEntity contactEntity : contactEntityList)
                {
                    ContactDTO contactDTO = Mapping.createContact(contactEntity);
                    if (contactDTO != null)
                    {
                        contactList.add(contactDTO);
                    }
                }
            }
        }
        return contactList;
    }

    @Transactional
    public List<ContactDTO> fetch()
    {
        List<ContactDTO> contactList = null;
        List<ContactEntity> contactEntityList = contactDao.getAllContactEntitys();
        if (contactEntityList != null)
        {
            contactList = new ArrayList<ContactDTO>();
            for (ContactEntity contactEntity : contactEntityList)
            {
                ContactDTO contactDTO = Mapping.createContact(contactEntity);
                if (contactDTO != null)
                {
                    contactList.add(contactDTO);
                }
            }
        }
        return contactList;
    }

    @Transactional
    public ContactDTO fetch(long id)
    {
        ContactEntity contactEntity = contactDao.getContactEntity(id);
        return Mapping.createContact(contactEntity);
    }

}
