package com.opensource.phonebook.shared.dto;
//
//CREATE TABLE `contacts` (
//  `contact_id` int(11) NOT NULL AUTO_INCREMENT,
//  `contact_active` tinyint(1) NOT NULL DEFAULT '1',
//  `username` varchar(45) NOT NULL,
//  `password` varchar(45) NOT NULL,
//  `prefix` varchar(45) DEFAULT NULL,
//  `first_name` varchar(45) NOT NULL,
//  `middle_name` varchar(45) DEFAULT NULL,
//  `last_name` varchar(45) NOT NULL,
//  `suffix` varchar(45) DEFAULT NULL,
//  `address1` varchar(45) DEFAULT NULL,
//  `address2` varchar(45) DEFAULT NULL,
//  `city` varchar(45) DEFAULT NULL,
//  `state` varchar(2) DEFAULT NULL,
//  `zip` varchar(45) DEFAULT NULL,
//  `company_id` int(11) DEFAULT NULL,
//  `entered_by` int(11) DEFAULT NULL,
//  `entered_date` datetime DEFAULT NULL,
//  `edited_by` int(11) DEFAULT NULL,
//  `edited_date` datetime DEFAULT NULL,
//  `birthdate` datetime DEFAULT NULL,
//  `admin` tinyint(1) NOT NULL DEFAULT '0',
//  PRIMARY KEY (`contact_id`),
//  UNIQUE KEY `username_UNIQUE` (`username`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * The persistent class for the Positions database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name="contacts")
public class ContactDTO implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="contact_id")
	private long Id;
	
//  `contact_active` tinyint(1) NOT NULL DEFAULT '1',
	@Column(name="contact_active")
	private boolean active;
	
//  `username` varchar(45) NOT NULL,
	@Column(name="username")
	private String username;
	
//  `password` varchar(45) NOT NULL,
	@Column(name="password")
	private String password;
	
//  `prefix` varchar(45) DEFAULT NULL,
	@Column(name="prefix")
	private String prefix;
	
//  `first_name` varchar(45) NOT NULL,
	@Column(name="first_name")
	private String firstName;
	
//  `middle_name` varchar(45) DEFAULT NULL,
	@Column(name="middle_name")
	private String middleName;
	
//  `last_name` varchar(45) NOT NULL,
	@Column(name="last_name")
	private String lastName;
	
//  `suffix` varchar(45) DEFAULT NULL,
	@Column(name="suffix")
	private String suffix;
	
//  `address1` varchar(45) DEFAULT NULL,
	@Column(name="address1")
	private String address1;
	
//  `address2` varchar(45) DEFAULT NULL,
	@Column(name="address2")
	private String address2;
	
//  `city` varchar(45) DEFAULT NULL,
	@Column(name="city")
	private String city;
	
//  `state` varchar(2) DEFAULT NULL,
	@Column(name="state")
	private String state;
	
//  `zip` varchar(45) DEFAULT NULL,
	@Column(name="zip")
	private String zip;
	
//  `company_id` int(11) DEFAULT NULL,
	@Column(name="company_id")
	private long companyId;
	
//  `entered_by` int(11) DEFAULT NULL,
	@Column(name="entered_by")
	private long enteredBy;
	
//  `entered_date` datetime DEFAULT NULL,
	@Column(name="entered_date")
	private Date enteredDate;
	
//  `edited_by` int(11) DEFAULT NULL,
	@Column(name="edited_by")
	private long editedBy;
	
//  `edited_date` datetime DEFAULT NULL,
	@Column(name="edited_date")
	private Date editedDate;
	
//  `birthdate` datetime DEFAULT NULL,
	@Column(name="birthdate")
	private Date birthDate;
	
//  `admin` tinyint(1) NOT NULL DEFAULT '0',
	@Column(name="admin")
	private boolean admin;
	
	@OneToMany(fetch=FetchType.EAGER)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "contact_id") 
	private Set<ContactEmailDTO> emails;
	
	@OneToMany(fetch=FetchType.EAGER)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "contact_id") 
	private Set<ContactPhoneDTO> phones;
	
	@OneToMany(fetch=FetchType.EAGER)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@JoinColumn(name = "contact_id") 
	private Set<ContactLinkDTO> links;
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(long enteredBy) {
		this.enteredBy = enteredBy;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public long getEditedBy() {
		return editedBy;
	}

	public void setEditedBy(long editedBy) {
		this.editedBy = editedBy;
	}

	public Date getEditedDate() {
		return editedDate;
	}

	public void setEditedDate(Date editedDate) {
		this.editedDate = editedDate;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public Set<ContactEmailDTO> getEmails() {
		return emails;
	}

	public void setEmails(Set<ContactEmailDTO> emails) {
		this.emails = emails;
	}

	public Set<ContactPhoneDTO> getPhones() {
		return phones;
	}

	public void setPhones(Set<ContactPhoneDTO> phones) {
		this.phones = phones;
	}

	public Set<ContactLinkDTO> getLinks() {
		return links;
	}

	public void setLinks(Set<ContactLinkDTO> links) {
		this.links = links;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result
				+ ((address1 == null) ? 0 : address1.hashCode());
		result = prime * result
				+ ((address2 == null) ? 0 : address2.hashCode());
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result
				+ ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + (int) (companyId ^ (companyId >>> 32));
		result = prime * result + (int) (editedBy ^ (editedBy >>> 32));
		result = prime * result
				+ ((editedDate == null) ? 0 : editedDate.hashCode());
		result = prime * result + ((emails == null) ? 0 : emails.hashCode());
		result = prime * result + (int) (enteredBy ^ (enteredBy >>> 32));
		result = prime * result
				+ ((enteredDate == null) ? 0 : enteredDate.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phones == null) ? 0 : phones.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactDTO other = (ContactDTO) obj;
		if (Id != other.Id)
			return false;
		if (active != other.active)
			return false;
		if (address1 == null) {
			if (other.address1 != null)
				return false;
		} else if (!address1.equals(other.address1))
			return false;
		if (address2 == null) {
			if (other.address2 != null)
				return false;
		} else if (!address2.equals(other.address2))
			return false;
		if (admin != other.admin)
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (companyId != other.companyId)
			return false;
		if (editedBy != other.editedBy)
			return false;
		if (editedDate == null) {
			if (other.editedDate != null)
				return false;
		} else if (!editedDate.equals(other.editedDate))
			return false;
		if (emails == null) {
			if (other.emails != null)
				return false;
		} else if (!emails.equals(other.emails))
			return false;
		if (enteredBy != other.enteredBy)
			return false;
		if (enteredDate == null) {
			if (other.enteredDate != null)
				return false;
		} else if (!enteredDate.equals(other.enteredDate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (links == null) {
			if (other.links != null)
				return false;
		} else if (!links.equals(other.links))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phones == null) {
			if (other.phones != null)
				return false;
		} else if (!phones.equals(other.phones))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (suffix == null) {
			if (other.suffix != null)
				return false;
		} else if (!suffix.equals(other.suffix))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContactDTO [Id=" + Id + ", active=" + active + ", username="
				+ username + ", password=" + password + ", prefix=" + prefix
				+ ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", suffix=" + suffix
				+ ", address1=" + address1 + ", address2=" + address2
				+ ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", companyId=" + companyId + ", enteredBy=" + enteredBy
				+ ", enteredDate=" + enteredDate + ", editedBy=" + editedBy
				+ ", editedDate=" + editedDate + ", birthDate=" + birthDate
				+ ", admin=" + admin + ", emails=" + emails + ", phones="
				+ phones + ", links=" + links + "]";
	}

	
	
}
