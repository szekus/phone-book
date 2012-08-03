package com.opensource.phonebook.server.rpc;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.client.services.PhoneTypeService;
import com.opensource.phonebook.server.dao.PhoneTypeDao;
import com.opensource.phonebook.shared.dto.PhoneTypeDTO;

@Transactional
@Service("phoneTypeService")
public class PhoneTypeServiceImpl extends BaseRPC implements PhoneTypeService {

	private static final long serialVersionUID = 1L;
	
	private PhoneTypeDao phoneTypeDao;
	
	public PhoneTypeServiceImpl () {
		if(ctx == null) {
			System.out.println("ctx is NULL");
		} else {
			System.out.println("ctx is NOT null");
			
			phoneTypeDao = (PhoneTypeDao) ctx.getBean("phoneTypeDao");
			if(phoneTypeDao == null) {
				System.out.println("phoneTypeDao is NULL");
			} else {
				System.out.println("phoneTypeDao is NOT null");
			}
		}
	}
	
	public PhoneTypeDao getPhoneTypeDao() {
		return phoneTypeDao;
	}

	public void setPhoneTypeDao(PhoneTypeDao phoneTypeDao) {
		this.phoneTypeDao = phoneTypeDao;
	}
	
	@Transactional
	public PhoneTypeDTO add(PhoneTypeDTO record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void remove(PhoneTypeDTO record) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void update(PhoneTypeDTO record) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public List<PhoneTypeDTO> fetch(PhoneTypeDTO exampleEntity) {
		return phoneTypeDao.getPhoneTypeDTO(exampleEntity);
	}

	@Transactional
	public List<PhoneTypeDTO> fetch() {
		return phoneTypeDao.getAllPhoneTypeDTOs();
	}

	@Transactional
	public PhoneTypeDTO fetch(long id) {
		return phoneTypeDao.getPhoneTypeDTO(id);
	}
	
	/*
	public PhoneTypeDTO add(PhoneTypeDTO phoneType) {
		return phoneTypeDao.savePhoneTypeDTO(phoneType);
	}

	public void remove(PhoneTypeDTO phoneType) {
		phoneTypeDao.deletePhoneTypeDTO(phoneType);
	}

	public void update(PhoneTypeDTO phoneType) {
		phoneTypeDao.savePhoneTypeDTO(phoneType);
	}
	*/
	
	

}
