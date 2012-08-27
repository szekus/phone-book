package com.opensource.phonebook.domain;

//
// CREATE TABLE `contacts` (
// `contact_id` int(11) NOT NULL AUTO_INCREMENT,
// `prefix` varchar(45) DEFAULT NULL,
// `first_name` varchar(45) NOT NULL,
// `middle_name` varchar(45) DEFAULT NULL,
// `last_name` varchar(45) NOT NULL,
// `suffix` varchar(45) DEFAULT NULL,
// `address1` varchar(45) DEFAULT NULL,
// `address2` varchar(45) DEFAULT NULL,
// `city` varchar(45) DEFAULT NULL,
// `state` varchar(2) DEFAULT NULL,
// `zip` varchar(45) DEFAULT NULL,
// `company_id` int(11) DEFAULT NULL,
// `entered_by` int(11) DEFAULT NULL,
// `entered_date` datetime DEFAULT NULL,
// `edited_by` int(11) DEFAULT NULL,
// `edited_date` datetime DEFAULT NULL,
// `birthdate` datetime DEFAULT NULL
// PRIMARY KEY (`contact_id`),
// UNIQUE KEY `username_UNIQUE` (`username`)
// ) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * The persistent class for the Positions database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "contacts")
public class ContactEntity implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private long id;

    // bi-directional many-to-one association to UserEntity
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

// `prefix` varchar(45) DEFAULT NULL,
    @Column(name = "prefix")
    private String prefix;

// `first_name` varchar(45) NOT NULL,
    @Column(name = "first_name")
    private String firstName;

// `middle_name` varchar(45) DEFAULT NULL,
    @Column(name = "middle_name")
    private String middleName;

// `last_name` varchar(45) NOT NULL,
    @Column(name = "last_name")
    private String lastName;

// `suffix` varchar(45) DEFAULT NULL,
    @Column(name = "suffix")
    private String suffix;

// `address1` varchar(45) DEFAULT NULL,
    @Column(name = "address1")
    private String address1;

// `address2` varchar(45) DEFAULT NULL,
    @Column(name = "address2")
    private String address2;

// `city` varchar(45) DEFAULT NULL,
    @Column(name = "city")
    private String city;

// `state` varchar(2) DEFAULT NULL,
    @Column(name = "state")
    private String state;

// `zip` varchar(45) DEFAULT NULL,
    @Column(name = "zip")
    private String zip;

// `company_id` int(11) DEFAULT NULL,
    @Column(name = "company_id")
    private long companyId;

// `entered_by` int(11) DEFAULT NULL,
    @Column(name = "entered_by")
    private long enteredBy;

// `entered_date` datetime DEFAULT NULL,
    @Column(name = "entered_date")
    private Date enteredDate;

// `edited_by` int(11) DEFAULT NULL,
    @Column(name = "edited_by")
    private long editedBy;

// `edited_date` datetime DEFAULT NULL,
    @Column(name = "edited_date")
    private Date editedDate;

// `birthdate` datetime DEFAULT NULL,
    @Column(name = "birthdate")
    private Date birthDate;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(
    { org.hibernate.annotations.CascadeType.ALL })
    @JoinColumn(name = "contact_id")
    private Set<ContactEmailEntity> emails;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(
    { org.hibernate.annotations.CascadeType.ALL })
    @JoinColumn(name = "contact_id")
    private Set<ContactPhoneEntity> phones;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(
    { org.hibernate.annotations.CascadeType.ALL })
    @JoinColumn(name = "contact_id")
    private Set<ContactLinkEntity> links;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public UserEntity getUser()
    {
        return user;
    }

    public void setUser(UserEntity user)
    {
        this.user = user;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getSuffix()
    {
        return suffix;
    }

    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
    }

    public String getAddress1()
    {
        return address1;
    }

    public void setAddress1(String address1)
    {
        this.address1 = address1;
    }

    public String getAddress2()
    {
        return address2;
    }

    public void setAddress2(String address2)
    {
        this.address2 = address2;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public long getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(long companyId)
    {
        this.companyId = companyId;
    }

    public long getEnteredBy()
    {
        return enteredBy;
    }

    public void setEnteredBy(long enteredBy)
    {
        this.enteredBy = enteredBy;
    }

    public Date getEnteredDate()
    {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate)
    {
        this.enteredDate = enteredDate;
    }

    public long getEditedBy()
    {
        return editedBy;
    }

    public void setEditedBy(long editedBy)
    {
        this.editedBy = editedBy;
    }

    public Date getEditedDate()
    {
        return editedDate;
    }

    public void setEditedDate(Date editedDate)
    {
        this.editedDate = editedDate;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public Set<ContactEmailEntity> getEmails()
    {
        return emails;
    }

    public void setEmails(Set<ContactEmailEntity> emails)
    {
        this.emails = emails;
    }

    public Set<ContactPhoneEntity> getPhones()
    {
        return phones;
    }

    public void setPhones(Set<ContactPhoneEntity> phones)
    {
        this.phones = phones;
    }

    public Set<ContactLinkEntity> getLinks()
    {
        return links;
    }

    public void setLinks(Set<ContactLinkEntity> links)
    {
        this.links = links;
    }

    @Override
    public String toString()
    {
        return "ContactEntity [id=" + id + ", prefix=" + prefix + ", firstName=" + firstName + ", middleName="
            + middleName + ", lastName=" + lastName + ", suffix=" + suffix + ", address1=" + address1 + ", address2="
            + address2 + ", city=" + city + ", state=" + state + ", zip=" + zip + ", companyId=" + companyId
            + ", enteredBy=" + enteredBy + ", enteredDate=" + enteredDate + ", editedBy=" + editedBy + ", editedDate="
            + editedDate + ", birthDate=" + birthDate + ", emails=" + emails + ", phones=" + phones + ", links="
            + links + "]";
    }

}
