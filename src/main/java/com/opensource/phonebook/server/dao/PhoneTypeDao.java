package com.opensource.phonebook.server.dao;

import java.util.List;

import com.opensource.phonebook.shared.dto.PhoneTypeDTO;

public interface PhoneTypeDao {
	
	public PhoneTypeDTO savePhoneTypeDTO(PhoneTypeDTO phoneType);
	
	public void deletePhoneTypeDTO(Long phoneTypeId);
	public void deletePhoneTypeDTO(PhoneTypeDTO phoneType);
	
	public List<PhoneTypeDTO> getAllPhoneTypeDTOs();
	
	// Retrieve
	public PhoneTypeDTO getPhoneTypeDTO(long id);
	public List<PhoneTypeDTO> getPhoneTypeDTO(PhoneTypeDTO exampleEntity);
	
}
