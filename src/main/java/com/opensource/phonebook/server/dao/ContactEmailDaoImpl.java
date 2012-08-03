package com.opensource.phonebook.server.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.shared.dto.ContactEmailDTO;

@Transactional
@Repository("contactEmailEmailDao")
public class ContactEmailDaoImpl implements ContactEmailDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Log logger = LogFactory.getLog(ContactDaoImpl.class);
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public ContactEmailDTO createContactEmailDTO(ContactEmailDTO contactEmail) {
		//this.getHibernateTemplate().saveOrUpdate(contactEmail);
		this.sessionFactory.getCurrentSession().persist(contactEmail);
		return contactEmail;
	}
	
	public ContactEmailDTO saveContactEmailDTO(ContactEmailDTO contactEmail) {
		//this.getHibernateTemplate().saveOrUpdate(contactEmail);
		this.sessionFactory.getCurrentSession().saveOrUpdate(contactEmail);
		return contactEmail;
	}
	
	public ContactEmailDTO updateContactEmailDTO(ContactEmailDTO contactEmail) {
		//this.getHibernateTemplate().saveOrUpdate(contactEmail);
		this.sessionFactory.getCurrentSession().merge(contactEmail);
		return contactEmail;
	}

	public void deleteContactEmailDTO(Long contactEmailId) {
		//this.getgetHibernateTemplate()().delete(interest);
	}

	public void deleteContactEmailDTO(ContactEmailDTO contactEmail) {
		//this.getHibernateTemplate().delete(contactEmail);
		this.sessionFactory.getCurrentSession().delete(contactEmail);
	}

	public List<ContactEmailDTO> getAllContactEmailDTOs() {
		String queryString = "from ContactEmailDTO";
		//List<ContactEmailDTO> users = this.getHibernateTemplate().find(queryString);
		List<ContactEmailDTO> users = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
		return users;
	}

	public ContactEmailDTO getContactEmailDTO(long id) {
		//return (ContactEmailDTO)this.getHibernateTemplate().get(ContactEmailDTO.class, id);
		return (ContactEmailDTO)this.sessionFactory.getCurrentSession().get(ContactEmailDTO.class, id);
	}

	public List<ContactEmailDTO> getContactEmailDTO(ContactEmailDTO exampleEntity) {
		//List<ContactEmailDTO> users = this.getHibernateTemplate().findByExample(exampleEntity);
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ContactEmailDTO.class);
		List<ContactEmailDTO> users = criteria.list();
		return users;
	}

	
}

