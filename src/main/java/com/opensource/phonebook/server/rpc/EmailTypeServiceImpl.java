package com.opensource.phonebook.server.rpc;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.client.services.EmailTypeService;
import com.opensource.phonebook.server.dao.EmailTypeDao;
import com.opensource.phonebook.shared.dto.EmailTypeDTO;

@Transactional
@Service("emailTypeService")
public class EmailTypeServiceImpl extends BaseRPC implements EmailTypeService {

	private static final long serialVersionUID = 1L;
	
	private EmailTypeDao emailTypeDao;
	
	public EmailTypeServiceImpl () {
		if(ctx == null) {
			System.out.println("ctx is NULL");
		} else {
			System.out.println("ctx is NOT null");
			
			emailTypeDao = (EmailTypeDao) ctx.getBean("emailTypeDao");
			if(emailTypeDao == null) {
				System.out.println("emailTypeDao is NULL");
			} else {
				System.out.println("emailTypeDao is NOT null");
			}
		}
	}
	
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
	public List<EmailTypeDTO> fetch(EmailTypeDTO exampleEntity) {
		return emailTypeDao.getEmailTypeDTO(exampleEntity);
	}

	@Transactional
	public List<EmailTypeDTO> fetch() {
		return emailTypeDao.getAllEmailTypeDTOs();
	}

	@Transactional
	public EmailTypeDTO fetch(long id) {
		return emailTypeDao.getEmailTypeDTO(id);
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
