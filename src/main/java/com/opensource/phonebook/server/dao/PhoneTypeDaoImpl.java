package com.opensource.phonebook.server.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.shared.dto.PhoneTypeDTO;

@Transactional
@Repository("phoneTypeDao")
public class PhoneTypeDaoImpl implements PhoneTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Log logger = LogFactory.getLog(PhoneTypeDaoImpl.class);
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public PhoneTypeDTO savePhoneTypeDTO(PhoneTypeDTO phoneType) {
		//this.getHibernateTemplate().saveOrUpdate(phoneType);
		return phoneType;
	}

	public void deletePhoneTypeDTO(Long phoneTypeId) {
		//this.getgetHibernateTemplate()().delete(interest);
	}

	public void deletePhoneTypeDTO(PhoneTypeDTO phoneType) {
		//this.getHibernateTemplate().delete(phoneType);
	}

	public List<PhoneTypeDTO> getAllPhoneTypeDTOs() {
		String queryString = "from PhoneTypeDTO";
		//List<PhoneTypeDTO> users = this.getHibernateTemplate().find(queryString);
		List<PhoneTypeDTO> users = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
		return users;
	}

	public PhoneTypeDTO getPhoneTypeDTO(long id) {
		//return (PhoneTypeDTO)this.getHibernateTemplate().get(PhoneTypeDTO.class, id);
		return (PhoneTypeDTO)this.sessionFactory.getCurrentSession().get(PhoneTypeDTO.class, id);
	}

	public List<PhoneTypeDTO> getPhoneTypeDTO(PhoneTypeDTO exampleEntity) {
		//List<PhoneTypeDTO> users = this.getHibernateTemplate().findByExample(exampleEntity);
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(PhoneTypeDTO.class);
		List<PhoneTypeDTO> users = criteria.list();
		return users;
	}

}


