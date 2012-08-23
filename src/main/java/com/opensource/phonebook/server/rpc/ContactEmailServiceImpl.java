package com.opensource.phonebook.server.rpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.client.services.ContactEmailService;
import com.opensource.phonebook.domain.ContactEmailEntity;
import com.opensource.phonebook.domain.ContactEntity;
import com.opensource.phonebook.domain.EmailTypeEntity;
import com.opensource.phonebook.server.dao.ContactEmailDao;
import com.opensource.phonebook.shared.Mapping;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactEmailDTO;

@SuppressWarnings("serial")
@Transactional
@Service("contactEmailService")
public class ContactEmailServiceImpl extends BaseRPC implements ContactEmailService
{

    @Autowired
    private ContactEmailDao contactEmailDao;

    public ContactEmailDao getContactEmailDao()
    {
        return contactEmailDao;
    }

    public void setContactEmailDao(ContactEmailDao contactEmailDao)
    {
        this.contactEmailDao = contactEmailDao;
    }

    @Transactional
    @Override
    public ContactEmailDTO add(ContactEmailDTO contactEmailDto)
    {
        ContactEntity contact = new ContactEntity();
        contact.setId(contactEmailDto.getContactId());

        ContactEmailEntity newContactEmail = new ContactEmailEntity();
        newContactEmail.setContact(contact);

        newContactEmail.setEmailId(contactEmailDto.getEmailId());
        newContactEmail.setEmail(contactEmailDto.getEmail());
        newContactEmail.setEnteredDate(contactEmailDto.getEnteredDate());

        EmailTypeEntity emailTypeEntity = new EmailTypeEntity();
        emailTypeEntity.setId(contactEmailDto.getEmailType().getId());
        newContactEmail.setEmailType(emailTypeEntity);

        ContactEmailEntity contactEmailEntity = contactEmailDao.createContactEmailEntity(newContactEmail);
        ContactEmailDTO newContactEmailDto = Mapping.createContactEmail(contactEmailEntity);

        return newContactEmailDto;
    }

    @Transactional
    @Override
    public void remove(ContactEmailDTO record)
    {
        // TODO Auto-generated method stub
    }

    @Transactional
    @Override
    public void update(ContactEmailDTO record)
    {
        // TODO Auto-generated method stub
    }

    @Transactional
    @Override
    public List<ContactEmailDTO> fetch(ContactEmailDTO exampleEntity)
    {
        List<ContactEmailDTO> contactEmailList = null;
        if (exampleEntity != null)
        {
            ContactEmailEntity exampleContactEmailEntity = new ContactEmailEntity();

            exampleContactEmailEntity.setEmailId(exampleEntity.getEmailId());
            exampleContactEmailEntity.setEnteredDate(exampleEntity.getEnteredDate());
            exampleContactEmailEntity.setEmail(exampleEntity.getEmail());

            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setId(exampleEntity.getContactId());
            exampleContactEmailEntity.setContact(contactEntity);

            if (exampleEntity.getEmailType() != null)
            {
                EmailTypeEntity emailType = new EmailTypeEntity();
                emailType.setId(exampleEntity.getEmailType().getId());
                exampleContactEmailEntity.setEmailType(emailType);
            }

            List<ContactEmailEntity> contactEmailEntityList =
                contactEmailDao.getContactEmailEntity(exampleContactEmailEntity);
            if (contactEmailEntityList != null)
            {
                contactEmailList = new ArrayList<ContactEmailDTO>();
                for (ContactEmailEntity contactEmailEntity : contactEmailEntityList)
                {
                    ContactEmailDTO contactEmailDTO = Mapping.createContactEmail(contactEmailEntity);
                    if (contactEmailDTO != null)
                    {
                        contactEmailList.add(contactEmailDTO);
                    }
                }
            }
        }
        return contactEmailList;
    }

    @Transactional
    public List<ContactEmailDTO> fetch_old(ContactDTO exampleEntity)
    {
        List<ContactEmailDTO> contactEmailList = null;
        if (exampleEntity != null)
        {
            ContactEntity exampleContactEntity = new ContactEntity();
            exampleContactEntity.setId(exampleEntity.getId());

            List<ContactEmailEntity> contactEmailEntityList =
                contactEmailDao.getContactEmailEntityByContact(exampleContactEntity);
            if (contactEmailEntityList != null)
            {
                contactEmailList = new ArrayList<ContactEmailDTO>();
                for (ContactEmailEntity contactEmailEntity : contactEmailEntityList)
                {
                    ContactEmailDTO contactEmailDTO = Mapping.createContactEmail(contactEmailEntity);
                    if (contactEmailDTO != null)
                    {
                        contactEmailList.add(contactEmailDTO);
                    }
                }
            }
        }
        return contactEmailList;
    }

    @Transactional
    @Override
    public List<ContactEmailDTO> fetch(ContactDTO exampleEntity)
    {
        List<ContactEmailDTO> contactEmailList = new ArrayList<ContactEmailDTO>();
        if (exampleEntity != null)
        {
            long contactId = exampleEntity.getId();
            List<ContactEmailEntity> contactEmailEntityList =
                contactEmailDao.getContactEmailEntityByContactId(contactId);
            if (contactEmailEntityList != null)
            {
                contactEmailList = new ArrayList<ContactEmailDTO>();
                for (ContactEmailEntity contactEmailEntity : contactEmailEntityList)
                {
                    ContactEmailDTO contactEmailDTO = Mapping.createContactEmail(contactEmailEntity);
                    if (contactEmailDTO != null)
                    {
                        contactEmailList.add(contactEmailDTO);
                    }
                }
            }
        }
        return contactEmailList;
    }

    @Transactional
    @Override
    public List<ContactEmailDTO> fetch()
    {
        List<ContactEmailDTO> contactEmailList = null;
        List<ContactEmailEntity> contactEmailEntityList = contactEmailDao.getAllContactEmailEntitys();
        if (contactEmailEntityList != null)
        {
            contactEmailList = new ArrayList<ContactEmailDTO>();
            for (ContactEmailEntity contactEmailEntity : contactEmailEntityList)
            {
                ContactEmailDTO contactEmailDTO = Mapping.createContactEmail(contactEmailEntity);
                if (contactEmailDTO != null)
                {
                    contactEmailList.add(contactEmailDTO);
                }
            }
        }
        return contactEmailList;
    }

    @Transactional
    @Override
    public ContactEmailDTO fetch(long id)
    {
        ContactEmailEntity contactEmailEntity = contactEmailDao.getContactEmailEntity(id);
        return Mapping.createContactEmail(contactEmailEntity);
    }

}
