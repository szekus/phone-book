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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private long contactId;
	
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
	
	

}
