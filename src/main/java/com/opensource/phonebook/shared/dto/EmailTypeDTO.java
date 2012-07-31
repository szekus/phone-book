package com.opensource.phonebook.shared.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//CREATE TABLE `email_type` (
//  `email_type_id` int(11) NOT NULL AUTO_INCREMENT,
//  `email_type_description` varchar(45) NOT NULL,
//  `email_type_active` tinyint(1) NOT NULL DEFAULT '1',
//  PRIMARY KEY (`email_type_id`),
//  UNIQUE KEY `email_type_description_UNIQUE` (`email_type_description`)
//) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8$$

/**
 * The persistent class for the Positions database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name="email_type")
public class EmailTypeDTO implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="email_type_id")
	private long id;
	
	@Column(name="email_type_description")
	private String description;
	
	@Column(name="email_type_active")
	private boolean active;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "EmailTypeDTO [id=" + id + ", description=" + description
				+ ", active=" + active + "]";
	}
	
}
