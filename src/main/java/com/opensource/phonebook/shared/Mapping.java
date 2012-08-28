package com.opensource.phonebook.shared;

import java.util.HashSet;
import java.util.Set;

import com.opensource.phonebook.domain.ContactEmailEntity;
import com.opensource.phonebook.domain.ContactEntity;
import com.opensource.phonebook.domain.ContactLinkEntity;
import com.opensource.phonebook.domain.ContactPhoneEntity;
import com.opensource.phonebook.domain.EmailTypeEntity;
import com.opensource.phonebook.domain.LinkTypeEntity;
import com.opensource.phonebook.domain.PhoneTypeEntity;
import com.opensource.phonebook.domain.PositionEntity;
import com.opensource.phonebook.domain.UserEntity;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactEmailDTO;
import com.opensource.phonebook.shared.dto.ContactLinkDTO;
import com.opensource.phonebook.shared.dto.ContactPhoneDTO;
import com.opensource.phonebook.shared.dto.EmailTypeDTO;
import com.opensource.phonebook.shared.dto.LinkTypeDTO;
import com.opensource.phonebook.shared.dto.PhoneTypeDTO;
import com.opensource.phonebook.shared.dto.PositionDTO;
import com.opensource.phonebook.shared.dto.UserDTO;

public class Mapping
{

    final static public UserDTO createUser(UserEntity userEntity)
    {
        UserDTO userDTO = null;
        if (userEntity != null)
        {
            userDTO = new UserDTO();
            userDTO.setActive(userEntity.isActive());
            // =========================================================
            Set<ContactDTO> contactDTOSet = null;
            if (userEntity.getContacts() != null)
            {
                contactDTOSet = new HashSet<ContactDTO>();
                for (ContactEntity contactEntity : userEntity.getContacts())
                {
                    ContactDTO contactDTO = createContact(contactEntity);
                    if (contactDTO != null)
                    {
                        contactDTOSet.add(contactDTO);
                    }
                }
            }
            userDTO.setContacts((HashSet<ContactDTO>) contactDTOSet);
            // =========================================================
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setFirstname(userEntity.getFirstname());
            userDTO.setId(userEntity.getId());
            userDTO.setLastname(userEntity.getLastname());
            userDTO.setPassword(userEntity.getPassword());
            userDTO.setPosition(createPosition(userEntity.getPosition()));
            userDTO.setSecurityAnswer1(userEntity.getSecurityAnswer1());
            userDTO.setSecurityAnswer2(userEntity.getSecurityAnswer2());
            userDTO.setSecurityQuestion1(userEntity.getSecurityQuestion1());
            userDTO.setSecurityQuestion2(userEntity.getSecurityQuestion2());
            userDTO.setUsername(userEntity.getUsername());
            userDTO.setBirthdate(userEntity.getBirthdate());
        }
        ;
        return userDTO;
    }

    final static public ContactDTO createContact(ContactEntity contactEntity)
    {
        ContactDTO contactDTO = null;
        if (contactEntity != null)
        {
            contactDTO = new ContactDTO();
            contactDTO.setUserId(contactEntity.getUser().getId());
            contactDTO.setId(contactEntity.getId());

            contactDTO.setPrefix(contactEntity.getPrefix());
            contactDTO.setFirstName(contactEntity.getFirstName());
            contactDTO.setMiddleName(contactEntity.getMiddleName());
            contactDTO.setLastName(contactEntity.getLastName());
            contactDTO.setSuffix(contactEntity.getSuffix());

            contactDTO.setAddress1(contactEntity.getAddress1());
            contactDTO.setAddress2(contactEntity.getAddress2());
            contactDTO.setCity(contactEntity.getCity());
            contactDTO.setState(contactEntity.getState());
            contactDTO.setZip(contactEntity.getZip());

            contactDTO.setEnteredBy(contactEntity.getEnteredBy());
            contactDTO.setEnteredDate(contactEntity.getEnteredDate());
            contactDTO.setEditedBy(contactEntity.getEditedBy());
            contactDTO.setEditedDate(contactEntity.getEditedDate());

            contactDTO.setBirthDate(contactEntity.getBirthDate());
            contactDTO.setCompanyId(contactEntity.getCompanyId());
            // ==============================================================
            Set<ContactEmailDTO> contactEmailDTOSet = null;
            if (contactEntity.getEmails() != null)
            {
                contactEmailDTOSet = new HashSet<ContactEmailDTO>();
                for (ContactEmailEntity contactEmailEntity : contactEntity.getEmails())
                {
                    ContactEmailDTO contactEmailDTO = createContactEmail(contactEmailEntity);
                    if (contactEmailDTO != null)
                    {
                        contactEmailDTOSet.add(contactEmailDTO);
                    }
                }
            }
            contactDTO.setEmails((HashSet<ContactEmailDTO>) contactEmailDTOSet);
            // ==============================================================
            Set<ContactLinkDTO> contactLinkDTOSet = null;
            if (contactEntity.getLinks() != null)
            {
                contactLinkDTOSet = new HashSet<ContactLinkDTO>();
                for (ContactLinkEntity contactLinkEntity : contactEntity.getLinks())
                {
                    ContactLinkDTO contactLinkDTO = createContactLink(contactLinkEntity);
                    if (contactLinkDTO != null)
                    {
                        contactLinkDTOSet.add(contactLinkDTO);
                    }
                }
            }
            contactDTO.setLinks((HashSet<ContactLinkDTO>) contactLinkDTOSet);
            // ==============================================================
            Set<ContactPhoneDTO> contactPhoneDTOSet = null;
            if (contactEntity.getPhones() != null)
            {
                contactPhoneDTOSet = new HashSet<ContactPhoneDTO>();
                for (ContactPhoneEntity contactPhoneEntity : contactEntity.getPhones())
                {
                    ContactPhoneDTO contactPhoneDTO = createContactPhone(contactPhoneEntity);
                    if (contactPhoneDTO != null)
                    {
                        contactPhoneDTOSet.add(contactPhoneDTO);
                    }
                }
            }
            contactDTO.setPhones((HashSet<ContactPhoneDTO>) contactPhoneDTOSet);
            // ==============================================================
            contactDTO.setEnteredBy(contactEntity.getEnteredBy());
            contactDTO.setEnteredDate(contactEntity.getEnteredDate());
            contactDTO.setFirstName(contactEntity.getFirstName());
            contactDTO.setId(contactEntity.getId());
            contactDTO.setLastName(contactEntity.getLastName());
            contactDTO.setMiddleName(contactEntity.getMiddleName());
            contactDTO.setPrefix(contactEntity.getPrefix());
            contactDTO.setState(contactEntity.getState());
            contactDTO.setSuffix(contactEntity.getSuffix());
            // contactDTO.setUser(contactEntity.getUser());
            contactDTO.setZip(contactEntity.getZip());
        }
        return contactDTO;
    }

    final static public EmailTypeDTO createEmailType(EmailTypeEntity emailTypeEntity)
    {
        EmailTypeDTO emailTypeDTO = null;
        if (emailTypeEntity != null)
        {
            emailTypeDTO = new EmailTypeDTO();
            emailTypeDTO.setActive(emailTypeEntity.isActive());
            emailTypeDTO.setDescription(emailTypeEntity.getDescription());
            emailTypeDTO.setId(emailTypeEntity.getId());
        }
        return emailTypeDTO;
    }

    final static public ContactEmailDTO createContactEmail(ContactEmailEntity contactEmailEntity)
    {
        ContactEmailDTO contactEmailDTO = null;
        if (contactEmailEntity != null)
        {
            contactEmailDTO = new ContactEmailDTO();
            contactEmailDTO.setContactId(contactEmailEntity.getContact().getId());
            contactEmailDTO.setEmail(contactEmailEntity.getEmail());
            contactEmailDTO.setEmailId(contactEmailEntity.getEmailId());
            contactEmailDTO.setEmailType(createEmailType(contactEmailEntity.getEmailType()));
            contactEmailDTO.setEnteredDate(contactEmailEntity.getEnteredDate());
        }
        return contactEmailDTO;
    }

    final static public LinkTypeDTO createLinkType(LinkTypeEntity linkTypeEntity)
    {
        LinkTypeDTO linkTypeDTO = null;
        if (linkTypeEntity != null)
        {
            linkTypeDTO = new LinkTypeDTO();
            linkTypeDTO.setActive(linkTypeEntity.isActive());
            linkTypeDTO.setDescription(linkTypeEntity.getDescription());
            linkTypeDTO.setId(linkTypeEntity.getId());
        }
        return linkTypeDTO;
    }

    final static public ContactLinkDTO createContactLink(ContactLinkEntity contactLinkEntity)
    {
        ContactLinkDTO contactLinkDTO = null;
        if (contactLinkEntity != null)
        {
            contactLinkDTO = new ContactLinkDTO();
            contactLinkDTO.setContactId(contactLinkEntity.getContact().getId());
            contactLinkDTO.setLink(contactLinkEntity.getLink());
            contactLinkDTO.setLinkId(contactLinkEntity.getLinkId());
            contactLinkDTO.setLinkType(createLinkType(contactLinkEntity.getLinkType()));
            contactLinkDTO.setEnteredDate(contactLinkEntity.getEnteredDate());
        }
        return contactLinkDTO;
    }

    final static public PhoneTypeDTO createPhoneType(PhoneTypeEntity phoneTypeEntity)
    {
        PhoneTypeDTO phoneTypeDTO = null;
        if (phoneTypeEntity != null)
        {
            phoneTypeDTO = new PhoneTypeDTO();
            phoneTypeDTO.setActive(phoneTypeEntity.isActive());
            phoneTypeDTO.setDescription(phoneTypeEntity.getDescription());
            phoneTypeDTO.setId(phoneTypeEntity.getId());
        }
        return phoneTypeDTO;
    }

    final static public ContactPhoneDTO createContactPhone(ContactPhoneEntity contactPhoneEntity)
    {
        ContactPhoneDTO contactPhoneDTO = null;
        if (contactPhoneEntity != null)
        {
            contactPhoneDTO = new ContactPhoneDTO();
            contactPhoneDTO.setContactId(contactPhoneEntity.getContact().getId());
            contactPhoneDTO.setPhone(contactPhoneEntity.getPhone());
            contactPhoneDTO.setPhoneId(contactPhoneEntity.getPhoneId());
            contactPhoneDTO.setPhoneType(createPhoneType(contactPhoneEntity.getPhoneType()));
            contactPhoneDTO.setEnteredDate(contactPhoneEntity.getEnteredDate());
        }
        return contactPhoneDTO;
    }

    final static public PositionDTO createPosition(PositionEntity positionEntity)
    {
        PositionDTO positionDTO = null;
        if (positionEntity != null)
        {
            positionDTO = new PositionDTO();
            positionDTO.setActive(positionEntity.isActive());
            positionDTO.setCode(positionEntity.getCode());
            positionDTO.setDescription(positionEntity.getDescription());
            positionDTO.setId(positionEntity.getId());
        }
        return positionDTO;
    }
}
