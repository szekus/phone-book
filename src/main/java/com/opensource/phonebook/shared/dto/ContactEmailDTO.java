package com.opensource.phonebook.shared.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the Positions database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name="contacts_email")
public class ContactEmailDTO implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="contacts_email_id")
	private long emailId;
	
	// bi-directional many-to-one association to ContactDTO
	@ManyToOne(fetch =  FetchType.EAGER)
	@JoinColumn(name="contact_id")
	private ContactDTO contact;
	
	// bi-directional many-to-one association to ContactDTO
	@ManyToOne(fetch =  FetchType.EAGER)
	@JoinColumn(name="email_type_id")
	private EmailTypeDTO emailType;

	@Column(name="email")
	private String email;
	
	@Column(name="entered_date")
	private String enteredDate;
	
	
}
