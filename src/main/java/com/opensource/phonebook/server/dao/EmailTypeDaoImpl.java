package com.opensource.phonebook.server.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.shared.dto.EmailTypeDTO;

@Transactional
@Repository("emailTypeDao")
public class EmailTypeDaoImpl implements EmailTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Log logger = LogFactory.getLog(EmailTypeDaoImpl.class);
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public EmailTypeDTO saveEmailTypeDTO(EmailTypeDTO emailType) {
		//this.getHibernateTemplate().saveOrUpdate(emailType);
		return emailType;
	}

	public void deleteEmailTypeDTO(Long emailTypeId) {
		//this.getgetHibernateTemplate()().delete(interest);
	}

	public void deleteEmailTypeDTO(EmailTypeDTO emailType) {
		//this.getHibernateTemplate().delete(emailType);
	}

	public List<EmailTypeDTO> getAllEmailTypeDTOs() {
		String queryString = "from EmailTypeDTO";
		//List<EmailTypeDTO> users = this.getHibernateTemplate().find(queryString);
		List<EmailTypeDTO> users = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
		return users;
	}

	public EmailTypeDTO getEmailTypeDTO(long id) {
		//return (EmailTypeDTO)this.getHibernateTemplate().get(EmailTypeDTO.class, id);
		return (EmailTypeDTO)this.sessionFactory.getCurrentSession().get(EmailTypeDTO.class, id);
	}

	public List<EmailTypeDTO> getEmailTypeDTO(EmailTypeDTO exampleEntity) {
		//List<EmailTypeDTO> users = this.getHibernateTemplate().findByExample(exampleEntity);
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(EmailTypeDTO.class);
		List<EmailTypeDTO> users = criteria.list();
		return users;
	}

}

