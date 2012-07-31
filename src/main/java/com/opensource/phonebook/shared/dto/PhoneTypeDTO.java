package com.opensource.phonebook.shared.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//CREATE TABLE `phone_type` (
//  `phone_type_id` int(11) NOT NULL AUTO_INCREMENT,
//  `phone_type_description` varchar(45) NOT NULL,
//  `phone_type_active` tinyint(1) NOT NULL DEFAULT '1',
//  PRIMARY KEY (`phone_type_id`),
//  UNIQUE KEY `phone_type_description_UNIQUE` (`phone_type_description`)
//) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8$$

/**
 * The persistent class for the Positions database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name="phone_type")
public class PhoneTypeDTO implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="phone_type_id")
	private long id;
	
	@Column(name="phone_type_description")
	private String description;
	
	@Column(name="phone_type_active")
	private boolean active;

	@Override
	public String toString() {
		return "PhoneTypeDTO [id=" + id + ", description=" + description
				+ ", active=" + active + "]";
	}
	
}

