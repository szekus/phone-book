package com.opensource.phonebook.server.rpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.client.services.ContactLinkService;
import com.opensource.phonebook.domain.ContactEntity;
import com.opensource.phonebook.domain.ContactLinkEntity;
import com.opensource.phonebook.domain.LinkTypeEntity;
import com.opensource.phonebook.server.dao.ContactLinkDao;
import com.opensource.phonebook.shared.Mapping;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactLinkDTO;

@SuppressWarnings("serial")
@Transactional
@Service("contactLinkService")
public class ContactLinkServiceImpl extends BaseRPC implements ContactLinkService
{

    @Autowired
    private ContactLinkDao contactLinkDao;

    public ContactLinkDao getContactLinkDao()
    {
        return contactLinkDao;
    }

    public void setContactLinkDao(ContactLinkDao contactLinkDao)
    {
        this.contactLinkDao = contactLinkDao;
    }

    @Transactional
    @Override
    public ContactLinkDTO add(ContactLinkDTO contactLinkDto)
    {
        ContactEntity contact = new ContactEntity();
        contact.setId(contactLinkDto.getContactId());

        ContactLinkEntity newContactLink = new ContactLinkEntity();
        newContactLink.setContact(contact);

        newContactLink.setLinkId(contactLinkDto.getLinkId());
        newContactLink.setLink(contactLinkDto.getLink());
        newContactLink.setEnteredDate(contactLinkDto.getEnteredDate());

        LinkTypeEntity linkTypeEntity = new LinkTypeEntity();
        linkTypeEntity.setId(contactLinkDto.getLinkType().getId());
        newContactLink.setLinkType(linkTypeEntity);

        ContactLinkEntity contactLinkEntity = contactLinkDao.createContactLinkEntity(newContactLink);
        ContactLinkDTO newContactLinkDto = Mapping.createContactLink(contactLinkEntity);

        return newContactLinkDto;
    }

    @Transactional
    @Override
    public void remove(ContactLinkDTO record)
    {
        // TODO Auto-generated method stub
    }

    @Transactional
    @Override
    public void update(ContactLinkDTO record)
    {
        // TODO Auto-generated method stub
    }

    @Transactional
    @Override
    public List<ContactLinkDTO> fetch(ContactLinkDTO exampleEntity)
    {
        List<ContactLinkDTO> contactLinkList = null;
        if (exampleEntity != null)
        {
            ContactLinkEntity exampleContactLinkEntity = new ContactLinkEntity();

            exampleContactLinkEntity.setLinkId(exampleEntity.getLinkId());
            exampleContactLinkEntity.setEnteredDate(exampleEntity.getEnteredDate());
            exampleContactLinkEntity.setLink(exampleEntity.getLink());

            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setId(exampleEntity.getContactId());
            exampleContactLinkEntity.setContact(contactEntity);

            if (exampleEntity.getLinkType() != null)
            {
                LinkTypeEntity linkType = new LinkTypeEntity();
                linkType.setId(exampleEntity.getLinkType().getId());
                exampleContactLinkEntity.setLinkType(linkType);
            }

            List<ContactLinkEntity> contactLinkEntityList =
                contactLinkDao.getContactLinkEntity(exampleContactLinkEntity);
            if (contactLinkEntityList != null)
            {
                contactLinkList = new ArrayList<ContactLinkDTO>();
                for (ContactLinkEntity contactLinkEntity : contactLinkEntityList)
                {
                    ContactLinkDTO contactLinkDTO = Mapping.createContactLink(contactLinkEntity);
                    if (contactLinkDTO != null)
                    {
                        contactLinkList.add(contactLinkDTO);
                    }
                }
            }
        }
        return contactLinkList;
    }

    @Transactional
    public List<ContactLinkDTO> fetch_old(ContactDTO exampleEntity)
    {
        List<ContactLinkDTO> contactLinkList = null;
        if (exampleEntity != null)
        {
            ContactEntity exampleContactEntity = new ContactEntity();
            exampleContactEntity.setId(exampleEntity.getId());

            List<ContactLinkEntity> contactLinkEntityList =
                contactLinkDao.getContactLinkEntityByContact(exampleContactEntity);
            if (contactLinkEntityList != null)
            {
                contactLinkList = new ArrayList<ContactLinkDTO>();
                for (ContactLinkEntity contactLinkEntity : contactLinkEntityList)
                {
                    ContactLinkDTO contactLinkDTO = Mapping.createContactLink(contactLinkEntity);
                    if (contactLinkDTO != null)
                    {
                        contactLinkList.add(contactLinkDTO);
                    }
                }
            }
        }
        return contactLinkList;
    }

    @Transactional
    @Override
    public List<ContactLinkDTO> fetch(ContactDTO exampleEntity)
    {
        List<ContactLinkDTO> contactLinkList = new ArrayList<ContactLinkDTO>();
        if (exampleEntity != null)
        {
            long contactId = exampleEntity.getId();
            List<ContactLinkEntity> contactLinkEntityList = contactLinkDao.getContactLinkEntityByContactId(contactId);
            if (contactLinkEntityList != null)
            {
                contactLinkList = new ArrayList<ContactLinkDTO>();
                for (ContactLinkEntity contactLinkEntity : contactLinkEntityList)
                {
                    ContactLinkDTO contactLinkDTO = Mapping.createContactLink(contactLinkEntity);
                    if (contactLinkDTO != null)
                    {
                        contactLinkList.add(contactLinkDTO);
                    }
                }
            }
        }
        return contactLinkList;
    }

    @Transactional
    @Override
    public List<ContactLinkDTO> fetch()
    {
        List<ContactLinkDTO> contactLinkList = null;
        List<ContactLinkEntity> contactLinkEntityList = contactLinkDao.getAllContactLinkEntitys();
        if (contactLinkEntityList != null)
        {
            contactLinkList = new ArrayList<ContactLinkDTO>();
            for (ContactLinkEntity contactLinkEntity : contactLinkEntityList)
            {
                ContactLinkDTO contactLinkDTO = Mapping.createContactLink(contactLinkEntity);
                if (contactLinkDTO != null)
                {
                    contactLinkList.add(contactLinkDTO);
                }
            }
        }
        return contactLinkList;
    }

    @Transactional
    @Override
    public ContactLinkDTO fetch(long id)
    {
        ContactLinkEntity contactLinkEntity = contactLinkDao.getContactLinkEntity(id);
        return Mapping.createContactLink(contactLinkEntity);
    }

}
