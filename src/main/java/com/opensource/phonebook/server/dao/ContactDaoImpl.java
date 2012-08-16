package com.opensource.phonebook.server.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.domain.ContactEntity;

@Transactional
@Repository("contactDao")
public class ContactDaoImpl implements ContactDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Log logger = LogFactory.getLog(ContactDaoImpl.class);
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public ContactEntity createContactEntity(ContactEntity contact) {
		//this.getHibernateTemplate().saveOrUpdate(contact);
		this.sessionFactory.getCurrentSession().persist(contact);
		return contact;
	}
	
	public ContactEntity saveContactEntity(ContactEntity contact) {
		//this.getHibernateTemplate().saveOrUpdate(contact);
		this.sessionFactory.getCurrentSession().saveOrUpdate(contact);
		return contact;
	}
	
	public ContactEntity updateContactEntity(ContactEntity contact) {
		//this.getHibernateTemplate().saveOrUpdate(contact);
		this.sessionFactory.getCurrentSession().merge(contact);
		return contact;
	}

	public void deleteContactEntity(Long contactId) {
		//this.getgetHibernateTemplate()().delete(interest);
	}

	public void deleteContactEntity(ContactEntity contact) {
		//this.getHibernateTemplate().delete(contact);
		this.sessionFactory.getCurrentSession().delete(contact);
	}

	public List<ContactEntity> getAllContactEntitys() {
		String queryString = "from ContactEntity";
		//List<ContactEntity> users = this.getHibernateTemplate().find(queryString);
		List<ContactEntity> users = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
		return users;
	}

	public ContactEntity getContactEntity(long id) {
		//return (ContactEntity)this.getHibernateTemplate().get(ContactEntity.class, id);
		return (ContactEntity)this.sessionFactory.getCurrentSession().get(ContactEntity.class, id);
	}

	public List<ContactEntity> getContactEntity(ContactEntity exampleEntity) {
		//List<ContactEntity> users = this.getHibernateTemplate().findByExample(exampleEntity);
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ContactEntity.class);
		List<ContactEntity> users = criteria.list();
		return users;
	}

	
}

