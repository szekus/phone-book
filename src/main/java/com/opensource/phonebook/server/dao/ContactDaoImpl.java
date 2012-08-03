package com.opensource.phonebook.server.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.shared.dto.ContactDTO;

@Transactional
@Repository("contactDao")
public class ContactDaoImpl implements ContactDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Log logger = LogFactory.getLog(ContactDaoImpl.class);
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public ContactDTO createContactDTO(ContactDTO contact) {
		//this.getHibernateTemplate().saveOrUpdate(contact);
		this.sessionFactory.getCurrentSession().persist(contact);
		return contact;
	}
	
	public ContactDTO saveContactDTO(ContactDTO contact) {
		//this.getHibernateTemplate().saveOrUpdate(contact);
		this.sessionFactory.getCurrentSession().saveOrUpdate(contact);
		return contact;
	}
	
	public ContactDTO updateContactDTO(ContactDTO contact) {
		//this.getHibernateTemplate().saveOrUpdate(contact);
		this.sessionFactory.getCurrentSession().merge(contact);
		return contact;
	}

	public void deleteContactDTO(Long contactId) {
		//this.getgetHibernateTemplate()().delete(interest);
	}

	public void deleteContactDTO(ContactDTO contact) {
		//this.getHibernateTemplate().delete(contact);
		this.sessionFactory.getCurrentSession().delete(contact);
	}

	public List<ContactDTO> getAllContactDTOs() {
		String queryString = "from ContactDTO";
		//List<ContactDTO> users = this.getHibernateTemplate().find(queryString);
		List<ContactDTO> users = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
		return users;
	}

	public ContactDTO getContactDTO(long id) {
		//return (ContactDTO)this.getHibernateTemplate().get(ContactDTO.class, id);
		return (ContactDTO)this.sessionFactory.getCurrentSession().get(ContactDTO.class, id);
	}

	public List<ContactDTO> getContactDTO(ContactDTO exampleEntity) {
		//List<ContactDTO> users = this.getHibernateTemplate().findByExample(exampleEntity);
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ContactDTO.class);
		List<ContactDTO> users = criteria.list();
		return users;
	}

	
}

