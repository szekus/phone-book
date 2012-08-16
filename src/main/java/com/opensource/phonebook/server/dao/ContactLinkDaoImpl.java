package com.opensource.phonebook.server.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.domain.ContactLinkEntity;

@Transactional
@Repository("contactLinkLinkDao")
public class ContactLinkDaoImpl implements ContactLinkDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Log logger = LogFactory.getLog(ContactDaoImpl.class);
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public ContactLinkEntity createContactLinkEntity(ContactLinkEntity contactLink) {
		//this.getHibernateTemplate().saveOrUpdate(contactLink);
		this.sessionFactory.getCurrentSession().persist(contactLink);
		return contactLink;
	}
	
	public ContactLinkEntity saveContactLinkEntity(ContactLinkEntity contactLink) {
		//this.getHibernateTemplate().saveOrUpdate(contactLink);
		this.sessionFactory.getCurrentSession().saveOrUpdate(contactLink);
		return contactLink;
	}
	
	public ContactLinkEntity updateContactLinkEntity(ContactLinkEntity contactLink) {
		//this.getHibernateTemplate().saveOrUpdate(contactLink);
		this.sessionFactory.getCurrentSession().merge(contactLink);
		return contactLink;
	}

	public void deleteContactLinkEntity(Long contactLinkId) {
		//this.getgetHibernateTemplate()().delete(interest);
	}

	public void deleteContactLinkEntity(ContactLinkEntity contactLink) {
		//this.getHibernateTemplate().delete(contactLink);
		this.sessionFactory.getCurrentSession().delete(contactLink);
	}

	public List<ContactLinkEntity> getAllContactLinkEntitys() {
		String queryString = "from ContactLinkEntity";
		//List<ContactLinkEntity> users = this.getHibernateTemplate().find(queryString);
		List<ContactLinkEntity> users = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
		return users;
	}

	public ContactLinkEntity getContactLinkEntity(long id) {
		//return (ContactLinkEntity)this.getHibernateTemplate().get(ContactLinkEntity.class, id);
		return (ContactLinkEntity)this.sessionFactory.getCurrentSession().get(ContactLinkEntity.class, id);
	}

	public List<ContactLinkEntity> getContactLinkEntity(ContactLinkEntity exampleEntity) {
		//List<ContactLinkEntity> users = this.getHibernateTemplate().findByExample(exampleEntity);
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ContactLinkEntity.class);
		List<ContactLinkEntity> users = criteria.list();
		return users;
	}

	
}


