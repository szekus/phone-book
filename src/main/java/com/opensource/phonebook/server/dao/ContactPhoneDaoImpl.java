package com.opensource.phonebook.server.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.shared.dto.ContactPhoneDTO;

@Transactional
@Repository("contactPhonePhoneDao")
public class ContactPhoneDaoImpl implements ContactPhoneDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Log logger = LogFactory.getLog(ContactDaoImpl.class);
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public ContactPhoneDTO createContactPhoneDTO(ContactPhoneDTO contactPhone) {
		//this.getHibernateTemplate().saveOrUpdate(contactPhone);
		this.sessionFactory.getCurrentSession().persist(contactPhone);
		return contactPhone;
	}
	
	public ContactPhoneDTO saveContactPhoneDTO(ContactPhoneDTO contactPhone) {
		//this.getHibernateTemplate().saveOrUpdate(contactPhone);
		this.sessionFactory.getCurrentSession().saveOrUpdate(contactPhone);
		return contactPhone;
	}
	
	public ContactPhoneDTO updateContactPhoneDTO(ContactPhoneDTO contactPhone) {
		//this.getHibernateTemplate().saveOrUpdate(contactPhone);
		this.sessionFactory.getCurrentSession().merge(contactPhone);
		return contactPhone;
	}

	public void deleteContactPhoneDTO(Long contactPhoneId) {
		//this.getgetHibernateTemplate()().delete(interest);
	}

	public void deleteContactPhoneDTO(ContactPhoneDTO contactPhone) {
		//this.getHibernateTemplate().delete(contactPhone);
		this.sessionFactory.getCurrentSession().delete(contactPhone);
	}

	public List<ContactPhoneDTO> getAllContactPhoneDTOs() {
		String queryString = "from ContactPhoneDTO";
		//List<ContactPhoneDTO> users = this.getHibernateTemplate().find(queryString);
		List<ContactPhoneDTO> users = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
		return users;
	}

	public ContactPhoneDTO getContactPhoneDTO(long id) {
		//return (ContactPhoneDTO)this.getHibernateTemplate().get(ContactPhoneDTO.class, id);
		return (ContactPhoneDTO)this.sessionFactory.getCurrentSession().get(ContactPhoneDTO.class, id);
	}

	public List<ContactPhoneDTO> getContactPhoneDTO(ContactPhoneDTO exampleEntity) {
		//List<ContactPhoneDTO> users = this.getHibernateTemplate().findByExample(exampleEntity);
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ContactPhoneDTO.class);
		List<ContactPhoneDTO> users = criteria.list();
		return users;
	}

	
}


