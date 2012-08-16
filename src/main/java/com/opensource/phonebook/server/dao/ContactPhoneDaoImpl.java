package com.opensource.phonebook.server.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.domain.ContactPhoneEntity;

@Transactional
@Repository("contactPhonePhoneDao")
public class ContactPhoneDaoImpl implements ContactPhoneDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Log logger = LogFactory.getLog(ContactDaoImpl.class);
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public ContactPhoneEntity createContactPhoneEntity(ContactPhoneEntity contactPhone) {
		//this.getHibernateTemplate().saveOrUpdate(contactPhone);
		this.sessionFactory.getCurrentSession().persist(contactPhone);
		return contactPhone;
	}
	
	public ContactPhoneEntity saveContactPhoneEntity(ContactPhoneEntity contactPhone) {
		//this.getHibernateTemplate().saveOrUpdate(contactPhone);
		this.sessionFactory.getCurrentSession().saveOrUpdate(contactPhone);
		return contactPhone;
	}
	
	public ContactPhoneEntity updateContactPhoneEntity(ContactPhoneEntity contactPhone) {
		//this.getHibernateTemplate().saveOrUpdate(contactPhone);
		this.sessionFactory.getCurrentSession().merge(contactPhone);
		return contactPhone;
	}

	public void deleteContactPhoneEntity(Long contactPhoneId) {
		//this.getgetHibernateTemplate()().delete(interest);
	}

	public void deleteContactPhoneEntity(ContactPhoneEntity contactPhone) {
		//this.getHibernateTemplate().delete(contactPhone);
		this.sessionFactory.getCurrentSession().delete(contactPhone);
	}

	public List<ContactPhoneEntity> getAllContactPhoneEntitys() {
		String queryString = "from ContactPhoneEntity";
		//List<ContactPhoneEntity> users = this.getHibernateTemplate().find(queryString);
		List<ContactPhoneEntity> users = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
		return users;
	}

	public ContactPhoneEntity getContactPhoneEntity(long id) {
		//return (ContactPhoneEntity)this.getHibernateTemplate().get(ContactPhoneEntity.class, id);
		return (ContactPhoneEntity)this.sessionFactory.getCurrentSession().get(ContactPhoneEntity.class, id);
	}

	public List<ContactPhoneEntity> getContactPhoneEntity(ContactPhoneEntity exampleEntity) {
		//List<ContactPhoneEntity> users = this.getHibernateTemplate().findByExample(exampleEntity);
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ContactPhoneEntity.class);
		List<ContactPhoneEntity> users = criteria.list();
		return users;
	}

	
}


