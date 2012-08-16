package com.opensource.phonebook.server.rpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.client.services.EmailTypeService;
import com.opensource.phonebook.domain.EmailTypeEntity;
import com.opensource.phonebook.server.dao.EmailTypeDao;
import com.opensource.phonebook.shared.dto.EmailTypeDTO;

@SuppressWarnings("serial")
@Transactional
@Service("emailTypeService")
public class EmailTypeServiceImpl extends BaseRPC implements EmailTypeService
{
	@Autowired
	private EmailTypeDao emailTypeDao;
	
	public EmailTypeDao getEmailTypeDao() {
		return emailTypeDao;
	}
	public void setEmailTypeDao(EmailTypeDao emailTypeDao) {
		this.emailTypeDao = emailTypeDao;
	}
	
	@Transactional
	public EmailTypeDTO add(EmailTypeDTO record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void remove(EmailTypeDTO record) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void update(EmailTypeDTO record) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public List<EmailTypeDTO> fetch(EmailTypeDTO exampleDTO)
	{
		List<EmailTypeDTO> emailTypeDTOList = new ArrayList<EmailTypeDTO>();
		if(exampleDTO != null)
		{
			
			EmailTypeEntity exampleEntity = new EmailTypeEntity();
			exampleEntity.setActive(exampleDTO.isActive());
			exampleEntity.setDescription(exampleDTO.getDescription());
			exampleEntity.setId(exampleDTO.getId());
			
			List<EmailTypeEntity> emailTypeEntityList = emailTypeDao.getEmailTypeEntity(exampleEntity);
			
			for(EmailTypeEntity emailTypeEntity : emailTypeEntityList )
			{
				emailTypeDTOList.add(createEmailTypeDTO(emailTypeEntity));
			}
		}
		return emailTypeDTOList;
	}

	@Transactional
	public List<EmailTypeDTO> fetch() {
		List<EmailTypeDTO> emailTypeDTOList = new ArrayList<EmailTypeDTO>();
		List<EmailTypeEntity> emailTypeEntityList = emailTypeDao.getAllEmailTypeEntitys();
		for(EmailTypeEntity emailTypeEntity : emailTypeEntityList )
		{
			emailTypeDTOList.add(createEmailTypeDTO(emailTypeEntity));
		}
		return emailTypeDTOList;
	}

	@Transactional
	public EmailTypeDTO fetch(long id) {
		EmailTypeEntity emailTypeEntity = emailTypeDao.getEmailTypeEntity(id);
		return createEmailTypeDTO(emailTypeEntity);
	}
	
	private EmailTypeDTO createEmailTypeDTO(EmailTypeEntity emailTypeEntity)
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
	
	/*
	public EmailTypeDTO add(EmailTypeDTO emailType) {
		return emailTypeDao.saveEmailTypeDTO(emailType);
	}

	public void remove(EmailTypeDTO emailType) {
		emailTypeDao.deleteEmailTypeDTO(emailType);
	}

	public void update(EmailTypeDTO emailType) {
		emailTypeDao.saveEmailTypeDTO(emailType);
	}
	*/
	
	

}
