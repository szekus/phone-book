package com.opensource.phonebook.server.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.shared.dto.LinkTypeDTO;

@Transactional
@Repository("linkTypeDao")
public class LinkTypeDaoImpl implements LinkTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Log logger = LogFactory.getLog(LinkTypeDaoImpl.class);
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public LinkTypeDTO saveLinkTypeDTO(LinkTypeDTO linkType) {
		//this.getHibernateTemplate().saveOrUpdate(linkType);
		return linkType;
	}

	public void deleteLinkTypeDTO(Long linkTypeId) {
		//this.getgetHibernateTemplate()().delete(interest);
	}

	public void deleteLinkTypeDTO(LinkTypeDTO linkType) {
		//this.getHibernateTemplate().delete(linkType);
	}

	public List<LinkTypeDTO> getAllLinkTypeDTOs() {
		String queryString = "from LinkTypeDTO";
		//List<LinkTypeDTO> users = this.getHibernateTemplate().find(queryString);
		List<LinkTypeDTO> users = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
		return users;
	}

	public LinkTypeDTO getLinkTypeDTO(long id) {
		//return (LinkTypeDTO)this.getHibernateTemplate().get(LinkTypeDTO.class, id);
		return (LinkTypeDTO)this.sessionFactory.getCurrentSession().get(LinkTypeDTO.class, id);
	}

	public List<LinkTypeDTO> getLinkTypeDTO(LinkTypeDTO exampleEntity) {
		//List<LinkTypeDTO> users = this.getHibernateTemplate().findByExample(exampleEntity);
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(LinkTypeDTO.class);
		List<LinkTypeDTO> users = criteria.list();
		return users;
	}

}


