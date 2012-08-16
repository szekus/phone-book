package com.opensource.phonebook.server.rpc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.client.services.UserService;
import com.opensource.phonebook.domain.ContactEmailEntity;
import com.opensource.phonebook.domain.ContactEntity;
import com.opensource.phonebook.domain.ContactLinkEntity;
import com.opensource.phonebook.domain.ContactPhoneEntity;
import com.opensource.phonebook.domain.EmailTypeEntity;
import com.opensource.phonebook.domain.LinkTypeEntity;
import com.opensource.phonebook.domain.PhoneTypeEntity;
import com.opensource.phonebook.domain.PositionEntity;
import com.opensource.phonebook.domain.UserEntity;
import com.opensource.phonebook.server.dao.UserDao;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactEmailDTO;
import com.opensource.phonebook.shared.dto.ContactLinkDTO;
import com.opensource.phonebook.shared.dto.ContactPhoneDTO;
import com.opensource.phonebook.shared.dto.EmailTypeDTO;
import com.opensource.phonebook.shared.dto.LinkTypeDTO;
import com.opensource.phonebook.shared.dto.PhoneTypeDTO;
import com.opensource.phonebook.shared.dto.PositionDTO;
import com.opensource.phonebook.shared.dto.UserDTO;

@SuppressWarnings("serial")
@Transactional
@Service("userService")
public class UserServiceImpl extends BaseRPC implements UserService {

	private UserDao userDao;
	
	public UserServiceImpl () {
//		if(ctx == null) {
//			System.out.println("ctx is NULL");
//		} else {
//			System.out.println("ctx is NOT null");
//			
//			userDao = (UserDao) ctx.getBean("userDao");
//			if(userDao == null) {
//				System.out.println("userDao is NULL");
//			} else {
//				System.out.println("userDao is NOT null");
//			}
//		}
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Transactional
	public UserDTO add(UserDTO record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void remove(UserDTO record) {
		// TODO Auto-generated method stub
	}

	@Transactional
	public void update(UserDTO record) {
		// TODO Auto-generated method stub
	}

	@Transactional
	public List<UserDTO> fetch(UserDTO exampleEntity) {
		List<UserDTO> userList = null;
		if(exampleEntity != null)
		{
			UserEntity exampleUserEntity = new UserEntity();
			exampleUserEntity.setActive(exampleEntity.isActive());
			exampleUserEntity.setEmail(exampleEntity.getEmail());
			exampleUserEntity.setId(exampleEntity.getId());
			exampleUserEntity.setFirstname(exampleEntity.getFirstname());
			exampleUserEntity.setLastname(exampleEntity.getLastname());
			exampleUserEntity.setPassword(exampleEntity.getPassword());
			exampleUserEntity.setSecurityAnswer1(exampleEntity.getSecurityAnswer1());
			exampleUserEntity.setSecurityAnswer2(exampleEntity.getSecurityAnswer2());
			exampleUserEntity.setSecurityQuestion1(exampleEntity.getSecurityQuestion1());
			exampleUserEntity.setSecurityQuestion2(exampleEntity.getSecurityQuestion2());
			
			List<UserEntity> userEntityList = userDao.getUsersEntity(exampleUserEntity);
			if(userEntityList != null)
			{
				userList = new ArrayList<UserDTO>();
				for(UserEntity userEntity : userEntityList)
				{
					UserDTO userDTO = createUser(userEntity);
					if(userDTO != null)
					{
						userList.add(userDTO);
					}
				}
			}
		}
		return userList;
	}

	@Transactional
	public List<UserDTO> fetch() {
		List<UserDTO> userList = null;
		List<UserEntity> userEntityList = userDao.getAllUserEntitys();
		if(userEntityList != null)
		{
			userList = new ArrayList<UserDTO>();
			for(UserEntity userEntity : userEntityList)
			{
				UserDTO userDTO = createUser(userEntity);
				if(userDTO != null)
				{
					userList.add(userDTO);
				}
			}
		}
		return userList;
	}

	@Transactional
	public UserDTO fetch(long id) {
		UserEntity userEntity = userDao.getUserEntity(id);
		return createUser(userEntity);
	}
	
	private UserDTO createUser(UserEntity userEntity)
	{
		UserDTO userDTO = null;
		if(userEntity != null)
		{
			userDTO = new UserDTO();
			userDTO.setActive(userEntity.isActive());
			//=========================================================
			Set<ContactDTO> contactDTOSet = null;
			if(userEntity.getContacts() != null)
			{
				for(ContactEntity contactEntity : userEntity.getContacts())
				{
					ContactDTO contactDTO = createContact(contactEntity);
					if(contactDTO != null)
					{
						contactDTOSet.add(contactDTO);
					}
				}
			}
			userDTO.setContacts((HashSet<ContactDTO>) contactDTOSet);
			//=========================================================
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
		}
		return userDTO;
	}
	
	private ContactDTO createContact(ContactEntity contactEntity)
	{
		ContactDTO contactDTO = null;
		if(contactEntity != null)
		{
			contactDTO = new ContactDTO();
			contactDTO.setAddress1(contactEntity.getAddress1());
			contactDTO.setAddress2(contactEntity.getAddress2());
			contactDTO.setBirthDate(contactEntity.getBirthDate());
			contactDTO.setCity(contactEntity.getCity());
			contactDTO.setCompanyId(contactEntity.getCompanyId());
			contactDTO.setEditedBy(contactEntity.getEditedBy());
			contactDTO.setEditedDate(contactEntity.getEditedDate());
			//==============================================================
			Set<ContactEmailDTO> contactEmailDTOSet = null;
			if(contactEntity.getEmails() != null)
			{
				for(ContactEmailEntity contactEmailEntity : contactEntity.getEmails())
				{
					ContactEmailDTO contactEmailDTO = createContactEmail(contactEmailEntity);
					if(contactEmailDTO != null)
					{
						contactEmailDTOSet.add(contactEmailDTO);
					}
				}
			}
			contactDTO.setEmails((HashSet<ContactEmailDTO>) contactEmailDTOSet);
			//==============================================================
			Set<ContactLinkDTO> contactLinkDTOSet = null;
			if(contactEntity.getLinks() != null)
			{
				for(ContactLinkEntity contactLinkEntity : contactEntity.getLinks())
				{
					ContactLinkDTO contactLinkDTO = createContactLink(contactLinkEntity);
					if(contactLinkDTO != null)
					{
						contactLinkDTOSet.add(contactLinkDTO);
					}
				}
			}
			contactDTO.setLinks((HashSet<ContactLinkDTO>) contactLinkDTOSet);
			//==============================================================
			Set<ContactPhoneDTO> contactPhoneDTOSet = null;
			if(contactEntity.getPhones() != null)
			{
				for(ContactPhoneEntity contactPhoneEntity : contactEntity.getPhones())
				{
					ContactPhoneDTO contactPhoneDTO = createContactPhone(contactPhoneEntity);
					if(contactPhoneDTO != null)
					{
						contactPhoneDTOSet.add(contactPhoneDTO);
					}
				}
			}
			contactDTO.setPhones((HashSet<ContactPhoneDTO>) contactPhoneDTOSet);
			//==============================================================
			contactDTO.setEnteredBy(contactEntity.getEnteredBy());
			contactDTO.setEnteredDate(contactEntity.getEnteredDate());
			contactDTO.setFirstName(contactEntity.getFirstName());
			contactDTO.setId(contactEntity.getId());
			contactDTO.setLastName(contactEntity.getLastName());
			contactDTO.setMiddleName(contactEntity.getMiddleName());
			contactDTO.setPrefix(contactEntity.getPrefix());
			contactDTO.setState(contactEntity.getState());
			contactDTO.setSuffix(contactEntity.getSuffix());
			//contactDTO.setUser(contactEntity.getUser());
			contactDTO.setZip(contactEntity.getZip());
		}
		return contactDTO;
	}
	
	private EmailTypeDTO createEmailType(EmailTypeEntity emailTypeEntity)
	{
		EmailTypeDTO emailTypeDTO = null;
		if(emailTypeEntity != null)
		{
			emailTypeDTO = new EmailTypeDTO();
			emailTypeDTO.setActive(emailTypeEntity.isActive());
			emailTypeDTO.setDescription(emailTypeEntity.getDescription());
			emailTypeDTO.setId(emailTypeEntity.getId());
		}
		return emailTypeDTO;
	}
	
	private ContactEmailDTO createContactEmail(ContactEmailEntity contactEmailEntity)
	{
		ContactEmailDTO contactEmailDTO = null;
		if(contactEmailEntity != null)
		{
			contactEmailDTO = new ContactEmailDTO();
			contactEmailDTO.setEmail(contactEmailEntity.getEmail());
			contactEmailDTO.setEmailId(contactEmailEntity.getEmailId());
			contactEmailDTO.setEmailType(createEmailType(contactEmailEntity.getEmailType()));
			contactEmailDTO.setEnteredDate(contactEmailEntity.getEnteredDate());
		}
		return contactEmailDTO;
	}
	
	private LinkTypeDTO createLinkType(LinkTypeEntity linkTypeEntity)
	{
		LinkTypeDTO linkTypeDTO = null;
		if(linkTypeEntity != null)
		{
			linkTypeDTO = new LinkTypeDTO();
			linkTypeDTO.setActive(linkTypeEntity.isActive());
			linkTypeDTO.setDescription(linkTypeEntity.getDescription());
			linkTypeDTO.setId(linkTypeEntity.getId());
		}
		return linkTypeDTO;
	}
	
	private ContactLinkDTO createContactLink(ContactLinkEntity contactLinkEntity)
	{
		ContactLinkDTO contactLinkDTO = null;
		if(contactLinkEntity != null)
		{
			contactLinkDTO = new ContactLinkDTO();
			contactLinkDTO.setLink(contactLinkEntity.getLink());
			contactLinkDTO.setLinkId(contactLinkEntity.getLinkId());
			contactLinkDTO.setLinkType(createLinkType(contactLinkEntity.getLinkType()));
			contactLinkDTO.setEnteredDate(contactLinkEntity.getEnteredDate());
		}
		return contactLinkDTO;
	}
	
	private PhoneTypeDTO createPhoneType(PhoneTypeEntity phoneTypeEntity)
	{
		PhoneTypeDTO phoneTypeDTO = null;
		if(phoneTypeEntity != null)
		{
			phoneTypeDTO = new PhoneTypeDTO();
			phoneTypeDTO.setActive(phoneTypeEntity.isActive());
			phoneTypeDTO.setDescription(phoneTypeEntity.getDescription());
			phoneTypeDTO.setId(phoneTypeEntity.getId());
		}
		return phoneTypeDTO;
	}
	
	private ContactPhoneDTO createContactPhone(ContactPhoneEntity contactPhoneEntity)
	{
		ContactPhoneDTO contactPhoneDTO = null;
		if(contactPhoneEntity != null)
		{
			contactPhoneDTO = new ContactPhoneDTO();
			contactPhoneDTO.setPhone(contactPhoneEntity.getPhone());
			contactPhoneDTO.setPhoneId(contactPhoneEntity.getPhoneId());
			contactPhoneDTO.setPhoneType(createPhoneType(contactPhoneEntity.getPhoneType()));
			contactPhoneDTO.setEnteredDate(contactPhoneEntity.getEnteredDate());
		}
		return contactPhoneDTO;
	}
	
	private PositionDTO createPosition(PositionEntity positionEntity)
	{
		PositionDTO positionDTO = null;
		if(positionEntity != null)
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

