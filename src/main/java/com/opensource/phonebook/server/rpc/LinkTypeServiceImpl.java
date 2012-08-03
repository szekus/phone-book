package com.opensource.phonebook.server.rpc;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.client.services.LinkTypeService;
import com.opensource.phonebook.server.dao.LinkTypeDao;
import com.opensource.phonebook.shared.dto.LinkTypeDTO;

@Transactional
@Service("linkTypeService")
public class LinkTypeServiceImpl extends BaseRPC implements LinkTypeService {

	private static final long serialVersionUID = 1L;
	
	private LinkTypeDao linkTypeDao;
	
	public LinkTypeServiceImpl () {
		if(ctx == null) {
			System.out.println("ctx is NULL");
		} else {
			System.out.println("ctx is NOT null");
			
			linkTypeDao = (LinkTypeDao) ctx.getBean("linkTypeDao");
			if(linkTypeDao == null) {
				System.out.println("linkTypeDao is NULL");
			} else {
				System.out.println("linkTypeDao is NOT null");
			}
		}
	}
	
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
	public List<LinkTypeDTO> fetch(LinkTypeDTO exampleEntity) {
		return linkTypeDao.getLinkTypeDTO(exampleEntity);
	}

	@Transactional
	public List<LinkTypeDTO> fetch() {
		return linkTypeDao.getAllLinkTypeDTOs();
	}

	@Transactional
	public LinkTypeDTO fetch(long id) {
		return linkTypeDao.getLinkTypeDTO(id);
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

