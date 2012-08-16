package com.opensource.phonebook.server.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.domain.ContactEmailEntity;

@Transactional
@Repository("contactEmailEmailDao")
public class ContactEmailDaoImpl implements ContactEmailDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Log logger = LogFactory.getLog(ContactDaoImpl.class);
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public ContactEmailEntity createContactEmailEntity(ContactEmailEntity contactEmail) {
		//this.getHibernateTemplate().saveOrUpdate(contactEmail);
		this.sessionFactory.getCurrentSession().persist(contactEmail);
		return contactEmail;
	}
	
	public ContactEmailEntity saveContactEmailEntity(ContactEmailEntity contactEmail) {
		//this.getHibernateTemplate().saveOrUpdate(contactEmail);
		this.sessionFactory.getCurrentSession().saveOrUpdate(contactEmail);
		return contactEmail;
	}
	
	public ContactEmailEntity updateContactEmailEntity(ContactEmailEntity contactEmail) {
		//this.getHibernateTemplate().saveOrUpdate(contactEmail);
		this.sessionFactory.getCurrentSession().merge(contactEmail);
		return contactEmail;
	}

	public void deleteContactEmailEntity(Long contactEmailId) {
		//this.getgetHibernateTemplate()().delete(interest);
	}

	public void deleteContactEmailEntity(ContactEmailEntity contactEmail) {
		//this.getHibernateTemplate().delete(contactEmail);
		this.sessionFactory.getCurrentSession().delete(contactEmail);
	}

	public List<ContactEmailEntity> getAllContactEmailEntitys() {
		String queryString = "from ContactEmailEntity";
		//List<ContactEmailEntity> users = this.getHibernateTemplate().find(queryString);
		List<ContactEmailEntity> users = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
		return users;
	}

	public ContactEmailEntity getContactEmailEntity(long id) {
		//return (ContactEmailEntity)this.getHibernateTemplate().get(ContactEmailEntity.class, id);
		return (ContactEmailEntity)this.sessionFactory.getCurrentSession().get(ContactEmailEntity.class, id);
	}

	public List<ContactEmailEntity> getContactEmailEntity(ContactEmailEntity exampleEntity) {
		//List<ContactEmailEntity> users = this.getHibernateTemplate().findByExample(exampleEntity);
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ContactEmailEntity.class);
		List<ContactEmailEntity> users = criteria.list();
		return users;
	}

	
}

