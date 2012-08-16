package com.opensource.phonebook.server.rpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.client.services.PhoneTypeService;
import com.opensource.phonebook.domain.PhoneTypeEntity;
import com.opensource.phonebook.server.dao.PhoneTypeDao;
import com.opensource.phonebook.shared.dto.PhoneTypeDTO;

@SuppressWarnings("serial")
@Transactional
@Service("phoneTypeService")
public class PhoneTypeServiceImpl extends BaseRPC implements PhoneTypeService
{
	@Autowired
	private PhoneTypeDao phoneTypeDao;
	
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
	public List<PhoneTypeDTO> fetch(PhoneTypeDTO exampleDTO)
	{
		List<PhoneTypeDTO> phoneTypeDTOList = new ArrayList<PhoneTypeDTO>();
		if(exampleDTO != null)
		{
			
			PhoneTypeEntity exampleEntity = new PhoneTypeEntity();
			exampleEntity.setActive(exampleDTO.isActive());
			exampleEntity.setDescription(exampleDTO.getDescription());
			exampleEntity.setId(exampleDTO.getId());
			
			List<PhoneTypeEntity> phoneTypeEntityList = phoneTypeDao.getPhoneTypeEntity(exampleEntity);
			
			for(PhoneTypeEntity phoneTypeEntity : phoneTypeEntityList )
			{
				phoneTypeDTOList.add(createPhoneTypeDTO(phoneTypeEntity));
			}
		}
		return phoneTypeDTOList;
	}

	@Transactional
	public List<PhoneTypeDTO> fetch() {
		List<PhoneTypeDTO> phoneTypeDTOList = new ArrayList<PhoneTypeDTO>();
		List<PhoneTypeEntity> phoneTypeEntityList = phoneTypeDao.getAllPhoneTypeEntitys();
		for(PhoneTypeEntity phoneTypeEntity : phoneTypeEntityList )
		{
			phoneTypeDTOList.add(createPhoneTypeDTO(phoneTypeEntity));
		}
		return phoneTypeDTOList;
	}

	@Transactional
	public PhoneTypeDTO fetch(long id) {
		PhoneTypeEntity phoneTypeEntity = phoneTypeDao.getPhoneTypeEntity(id);
		return createPhoneTypeDTO(phoneTypeEntity);
	}
	
	private PhoneTypeDTO createPhoneTypeDTO(PhoneTypeEntity phoneTypeEntity)
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
