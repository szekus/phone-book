package com.opensource.phonebook.shared.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//CREATE TABLE `link_type` (
//  `link_type_id` int(11) NOT NULL AUTO_INCREMENT,
//  `link_type_description` varchar(45) NOT NULL,
//  `link_type_active` tinyint(1) NOT NULL DEFAULT '1',
//  PRIMARY KEY (`link_type_id`),
//  UNIQUE KEY `link_type_description_UNIQUE` (`link_type_description`)
//) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8$$

/**
 * The persistent class for the Positions database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name="link_type")
public class LinkTypeDTO implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="link_type_id")
	private long id;
	
	@Column(name="link_type_description")
	private String description;
	
	@Column(name="link_type_active")
	private boolean active;

	@Override
	public String toString() {
		return "EmailTypeDTO [id=" + id + ", description=" + description
				+ ", active=" + active + "]";
	}
	
}

