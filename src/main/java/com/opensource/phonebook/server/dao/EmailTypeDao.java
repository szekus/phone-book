package com.opensource.phonebook.server.dao;

import java.util.List;

import com.opensource.phonebook.shared.dto.EmailTypeDTO;

public interface EmailTypeDao {
	
	public EmailTypeDTO saveEmailTypeDTO(EmailTypeDTO emailType);
	
	public void deleteEmailTypeDTO(Long emailTypeId);
	public void deleteEmailTypeDTO(EmailTypeDTO emailType);
	
	public List<EmailTypeDTO> getAllEmailTypeDTOs();
	
	// Retrieve
	public EmailTypeDTO getEmailTypeDTO(long id);
	public List<EmailTypeDTO> getEmailTypeDTO(EmailTypeDTO exampleEntity);
	
}

