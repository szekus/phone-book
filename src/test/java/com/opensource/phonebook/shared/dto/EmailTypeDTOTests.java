package com.opensource.phonebook.shared.dto;

import junit.framework.TestCase;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailTypeDTOTests extends TestCase {

	final Logger logger = LoggerFactory.getLogger(EmailTypeDTOTests.class);

	protected void setUp() throws Exception {
		System.out.println("setup: Loading application context");
		System.out.println("setup: Done loading application context");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		System.out.println("tearDown: START");
		System.out.println("tearDown: FINISH");
	}
	
	@Test
	public void testEmailTypeCreate() {
		System.out.println("testEmailTypeCreate: START");
		// =================================================================================
		int id = 0;
		String description = "Test";
		boolean active = true;
		// =================================================================================
		System.out.println("testEmailTypeCreate: START: CREATE");
		EmailTypeDTO emailType = new EmailTypeDTO();
		assertNotNull(emailType);
		// ***************************************************************
		emailType.setId(id);
		emailType.setDescription(description);
		emailType.setActive(active);
		System.out.println("testEmailTypeCreate: emailType=" + emailType.toString());
		// ***************************************************************
		assertNotNull(emailType.getId());
		assertNotNull(emailType.getDescription());
		assertNotNull(emailType.isActive());
		assertNotNull(emailType.toString());
		// ***************************************************************
		assertEquals(emailType.getId(),id);
		assertEquals(emailType.getDescription(),description);
		assertEquals(emailType.isActive(),active);
		// =================================================================================
		System.out.println("testEmailTypeCreate: FINISH: CREATE");
		// =================================================================================
	}
	
}
