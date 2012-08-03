package com.opensource.phonebook.server.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.shared.dto.ContactLinkDTO;

@Transactional
@Repository("contactLinkLinkDao")
public class ContactLinkDaoImpl implements ContactLinkDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Log logger = LogFactory.getLog(ContactDaoImpl.class);
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public ContactLinkDTO createContactLinkDTO(ContactLinkDTO contactLink) {
		//this.getHibernateTemplate().saveOrUpdate(contactLink);
		this.sessionFactory.getCurrentSession().persist(contactLink);
		return contactLink;
	}
	
	public ContactLinkDTO saveContactLinkDTO(ContactLinkDTO contactLink) {
		//this.getHibernateTemplate().saveOrUpdate(contactLink);
		this.sessionFactory.getCurrentSession().saveOrUpdate(contactLink);
		return contactLink;
	}
	
	public ContactLinkDTO updateContactLinkDTO(ContactLinkDTO contactLink) {
		//this.getHibernateTemplate().saveOrUpdate(contactLink);
		this.sessionFactory.getCurrentSession().merge(contactLink);
		return contactLink;
	}

	public void deleteContactLinkDTO(Long contactLinkId) {
		//this.getgetHibernateTemplate()().delete(interest);
	}

	public void deleteContactLinkDTO(ContactLinkDTO contactLink) {
		//this.getHibernateTemplate().delete(contactLink);
		this.sessionFactory.getCurrentSession().delete(contactLink);
	}

	public List<ContactLinkDTO> getAllContactLinkDTOs() {
		String queryString = "from ContactLinkDTO";
		//List<ContactLinkDTO> users = this.getHibernateTemplate().find(queryString);
		List<ContactLinkDTO> users = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
		return users;
	}

	public ContactLinkDTO getContactLinkDTO(long id) {
		//return (ContactLinkDTO)this.getHibernateTemplate().get(ContactLinkDTO.class, id);
		return (ContactLinkDTO)this.sessionFactory.getCurrentSession().get(ContactLinkDTO.class, id);
	}

	public List<ContactLinkDTO> getContactLinkDTO(ContactLinkDTO exampleEntity) {
		//List<ContactLinkDTO> users = this.getHibernateTemplate().findByExample(exampleEntity);
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ContactLinkDTO.class);
		List<ContactLinkDTO> users = criteria.list();
		return users;
	}

	
}


