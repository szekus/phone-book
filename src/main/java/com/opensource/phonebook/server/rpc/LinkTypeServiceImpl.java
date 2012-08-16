package com.opensource.phonebook.server.rpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.client.services.LinkTypeService;
import com.opensource.phonebook.domain.LinkTypeEntity;
import com.opensource.phonebook.server.dao.LinkTypeDao;
import com.opensource.phonebook.shared.dto.LinkTypeDTO;

@SuppressWarnings("serial")
@Transactional
@Service("linkTypeService")
public class LinkTypeServiceImpl extends BaseRPC implements LinkTypeService
{
	@Autowired
	private LinkTypeDao linkTypeDao;
	
	public LinkTypeDao getLinkTypeDao() {
		return linkTypeDao;
	}
	public void setLinkTypeDao(LinkTypeDao linkTypeDao) {
		this.linkTypeDao = linkTypeDao;
	}
	
	@Transactional
	public LinkTypeDTO add(LinkTypeDTO record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void remove(LinkTypeDTO record) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public void update(LinkTypeDTO record) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public List<LinkTypeDTO> fetch(LinkTypeDTO exampleDTO)
	{
		List<LinkTypeDTO> linkTypeDTOList = new ArrayList<LinkTypeDTO>();
		if(exampleDTO != null)
		{
			
			LinkTypeEntity exampleEntity = new LinkTypeEntity();
			exampleEntity.setActive(exampleDTO.isActive());
			exampleEntity.setDescription(exampleDTO.getDescription());
			exampleEntity.setId(exampleDTO.getId());
			
			List<LinkTypeEntity> linkTypeEntityList = linkTypeDao.getLinkTypeEntity(exampleEntity);
			
			for(LinkTypeEntity linkTypeEntity : linkTypeEntityList )
			{
				linkTypeDTOList.add(createLinkTypeDTO(linkTypeEntity));
			}
		}
		return linkTypeDTOList;
	}

	@Transactional
	public List<LinkTypeDTO> fetch() {
		List<LinkTypeDTO> linkTypeDTOList = new ArrayList<LinkTypeDTO>();
		List<LinkTypeEntity> linkTypeEntityList = linkTypeDao.getAllLinkTypeEntitys();
		for(LinkTypeEntity linkTypeEntity : linkTypeEntityList )
		{
			linkTypeDTOList.add(createLinkTypeDTO(linkTypeEntity));
		}
		return linkTypeDTOList;
	}

	@Transactional
	public LinkTypeDTO fetch(long id) {
		LinkTypeEntity linkTypeEntity = linkTypeDao.getLinkTypeEntity(id);
		return createLinkTypeDTO(linkTypeEntity);
	}
	
	private LinkTypeDTO createLinkTypeDTO(LinkTypeEntity linkTypeEntity)
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
	
	/*
	public LinkTypeDTO add(LinkTypeDTO linkType) {
		return linkTypeDao.saveLinkTypeDTO(linkType);
	}

	public void remove(LinkTypeDTO linkType) {
		linkTypeDao.deleteLinkTypeDTO(linkType);
	}

	public void update(LinkTypeDTO linkType) {
		linkTypeDao.saveLinkTypeDTO(linkType);
	}
	*/
	
	

}
