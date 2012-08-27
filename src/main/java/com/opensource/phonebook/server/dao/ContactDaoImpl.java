package com.opensource.phonebook.server.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.domain.ContactEntity;
import com.opensource.phonebook.domain.UserEntity;

@Transactional
@Repository("contactDao")
public class ContactDaoImpl implements ContactDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private static final Log logger = LogFactory.getLog(ContactDaoImpl.class);

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ContactEntity createContactEntity(ContactEntity contact)
    {
        // this.getHibernateTemplate().saveOrUpdate(contact);
        this.sessionFactory.getCurrentSession().persist(contact);
        return contact;
    }

    @Override
    public ContactEntity saveContactEntity(ContactEntity contact)
    {
        // this.getHibernateTemplate().saveOrUpdate(contact);
        this.sessionFactory.getCurrentSession().saveOrUpdate(contact);
        return contact;
    }

    @Override
    public ContactEntity updateContactEntity(ContactEntity contact)
    {
        // this.getHibernateTemplate().saveOrUpdate(contact);
        this.sessionFactory.getCurrentSession().merge(contact);
        return contact;
    }

    @Override
    public void deleteContactEntity(long contactId)
    {
        // this.sessionFactory.getCurrentSession().delete(contactId,ContactEntity.class);
    }

    @Override
    public void deleteContactEntity(ContactEntity contact)
    {
        this.sessionFactory.getCurrentSession().delete(contact);
    }

    @Override
    public List<ContactEntity> getAllContactEntitys()
    {
        String queryString = "from ContactEntity";
        // List<ContactEntity> users = this.getHibernateTemplate().find(queryString);
        List<ContactEntity> users = this.sessionFactory.getCurrentSession().createQuery(queryString).list();
        return users;
    }

    @Override
    public ContactEntity getContactEntity(long id)
    {
        // return (ContactEntity)this.getHibernateTemplate().get(ContactEntity.class, id);
        return (ContactEntity) this.sessionFactory.getCurrentSession().get(ContactEntity.class, id);
    }

    @Override
    public List<ContactEntity> getContactEntity(ContactEntity exampleEntity)
    {
        // List<ContactEntity> users = this.getHibernateTemplate().findByExample(exampleEntity);
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ContactEntity.class);
        List<ContactEntity> users = criteria.list();
        return users;
    }

    @Override
    public List<ContactEntity> getContactEntityByUser(UserEntity exampleEntity)
    {
        Query query =
            this.sessionFactory.getCurrentSession().createQuery("from ContactEntity ce where ce.user = :user");
        query.setParameter("user", exampleEntity);
        List<ContactEntity> contacts = query.list();
        return contacts;
    }

}
