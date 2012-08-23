package com.opensource.phonebook.server.rpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.client.services.ContactPhoneService;
import com.opensource.phonebook.domain.ContactEntity;
import com.opensource.phonebook.domain.ContactPhoneEntity;
import com.opensource.phonebook.domain.PhoneTypeEntity;
import com.opensource.phonebook.server.dao.ContactPhoneDao;
import com.opensource.phonebook.shared.Mapping;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactPhoneDTO;

@SuppressWarnings("serial")
@Transactional
@Service("contactPhoneService")
public class ContactPhoneServiceImpl extends BaseRPC implements ContactPhoneService
{

    @Autowired
    private ContactPhoneDao contactPhoneDao;

    public ContactPhoneDao getContactPhoneDao()
    {
        return contactPhoneDao;
    }

    public void setContactPhoneDao(ContactPhoneDao contactPhoneDao)
    {
        this.contactPhoneDao = contactPhoneDao;
    }

    @Transactional
    @Override
    public ContactPhoneDTO add(ContactPhoneDTO contactPhoneDto)
    {
        ContactEntity contact = new ContactEntity();
        contact.setId(contactPhoneDto.getContactId());

        ContactPhoneEntity newContactPhone = new ContactPhoneEntity();
        newContactPhone.setContact(contact);

        newContactPhone.setPhoneId(contactPhoneDto.getPhoneId());
        newContactPhone.setPhone(contactPhoneDto.getPhone());
        newContactPhone.setEnteredDate(contactPhoneDto.getEnteredDate());

        PhoneTypeEntity phoneTypeEntity = new PhoneTypeEntity();
        phoneTypeEntity.setId(contactPhoneDto.getPhoneType().getId());
        newContactPhone.setPhoneType(phoneTypeEntity);

        ContactPhoneEntity contactPhoneEntity = contactPhoneDao.createContactPhoneEntity(newContactPhone);
        ContactPhoneDTO newContactPhoneDto = Mapping.createContactPhone(contactPhoneEntity);

        return newContactPhoneDto;
    }

    @Transactional
    @Override
    public void remove(ContactPhoneDTO record)
    {
        // TODO Auto-generated method stub
    }

    @Transactional
    @Override
    public void update(ContactPhoneDTO record)
    {
        // TODO Auto-generated method stub
    }

    @Transactional
    @Override
    public List<ContactPhoneDTO> fetch(ContactPhoneDTO exampleEntity)
    {
        List<ContactPhoneDTO> contactPhoneList = null;
        if (exampleEntity != null)
        {
            ContactPhoneEntity exampleContactPhoneEntity = new ContactPhoneEntity();

            exampleContactPhoneEntity.setPhoneId(exampleEntity.getPhoneId());
            exampleContactPhoneEntity.setEnteredDate(exampleEntity.getEnteredDate());
            exampleContactPhoneEntity.setPhone(exampleEntity.getPhone());

            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setId(exampleEntity.getContactId());
            exampleContactPhoneEntity.setContact(contactEntity);

            if (exampleEntity.getPhoneType() != null)
            {
                PhoneTypeEntity phoneType = new PhoneTypeEntity();
                phoneType.setId(exampleEntity.getPhoneType().getId());
                exampleContactPhoneEntity.setPhoneType(phoneType);
            }

            List<ContactPhoneEntity> contactPhoneEntityList =
                contactPhoneDao.getContactPhoneEntity(exampleContactPhoneEntity);
            if (contactPhoneEntityList != null)
            {
                contactPhoneList = new ArrayList<ContactPhoneDTO>();
                for (ContactPhoneEntity contactPhoneEntity : contactPhoneEntityList)
                {
                    ContactPhoneDTO contactPhoneDTO = Mapping.createContactPhone(contactPhoneEntity);
                    if (contactPhoneDTO != null)
                    {
                        contactPhoneList.add(contactPhoneDTO);
                    }
                }
            }
        }
        return contactPhoneList;
    }

    @Transactional
    public List<ContactPhoneDTO> fetch_old(ContactDTO exampleEntity)
    {
        List<ContactPhoneDTO> contactPhoneList = null;
        if (exampleEntity != null)
        {
            ContactEntity exampleContactEntity = new ContactEntity();
            exampleContactEntity.setId(exampleEntity.getId());

            List<ContactPhoneEntity> contactPhoneEntityList =
                contactPhoneDao.getContactPhoneEntityByContact(exampleContactEntity);
            if (contactPhoneEntityList != null)
            {
                contactPhoneList = new ArrayList<ContactPhoneDTO>();
                for (ContactPhoneEntity contactPhoneEntity : contactPhoneEntityList)
                {
                    ContactPhoneDTO contactPhoneDTO = Mapping.createContactPhone(contactPhoneEntity);
                    if (contactPhoneDTO != null)
                    {
                        contactPhoneList.add(contactPhoneDTO);
                    }
                }
            }
        }
        return contactPhoneList;
    }

    @Transactional
    @Override
    public List<ContactPhoneDTO> fetch(ContactDTO exampleEntity)
    {
        List<ContactPhoneDTO> contactPhoneList = new ArrayList<ContactPhoneDTO>();
        if (exampleEntity != null)
        {
            long contactId = exampleEntity.getId();
            List<ContactPhoneEntity> contactPhoneEntityList =
                contactPhoneDao.getContactPhoneEntityByContactId(contactId);
            if (contactPhoneEntityList != null)
            {
                contactPhoneList = new ArrayList<ContactPhoneDTO>();
                for (ContactPhoneEntity contactPhoneEntity : contactPhoneEntityList)
                {
                    ContactPhoneDTO contactPhoneDTO = Mapping.createContactPhone(contactPhoneEntity);
                    if (contactPhoneDTO != null)
                    {
                        contactPhoneList.add(contactPhoneDTO);
                    }
                }
            }
        }
        return contactPhoneList;
    }

    @Transactional
    @Override
    public List<ContactPhoneDTO> fetch()
    {
        List<ContactPhoneDTO> contactPhoneList = null;
        List<ContactPhoneEntity> contactPhoneEntityList = contactPhoneDao.getAllContactPhoneEntitys();
        if (contactPhoneEntityList != null)
        {
            contactPhoneList = new ArrayList<ContactPhoneDTO>();
            for (ContactPhoneEntity contactPhoneEntity : contactPhoneEntityList)
            {
                ContactPhoneDTO contactPhoneDTO = Mapping.createContactPhone(contactPhoneEntity);
                if (contactPhoneDTO != null)
                {
                    contactPhoneList.add(contactPhoneDTO);
                }
            }
        }
        return contactPhoneList;
    }

    @Transactional
    @Override
    public ContactPhoneDTO fetch(long id)
    {
        ContactPhoneEntity contactPhoneEntity = contactPhoneDao.getContactPhoneEntity(id);
        return Mapping.createContactPhone(contactPhoneEntity);
    }

}
