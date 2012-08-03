package com.opensource.phonebook.server.dao;

import java.util.List;

import com.opensource.phonebook.shared.dto.LinkTypeDTO;

public interface LinkTypeDao {
	
	public LinkTypeDTO saveLinkTypeDTO(LinkTypeDTO linkType);
	
	public void deleteLinkTypeDTO(Long linkTypeId);
	public void deleteLinkTypeDTO(LinkTypeDTO linkType);
	
	public List<LinkTypeDTO> getAllLinkTypeDTOs();
	
	// Retrieve
	public LinkTypeDTO getLinkTypeDTO(long id);
	public List<LinkTypeDTO> getLinkTypeDTO(LinkTypeDTO exampleEntity);
	
}