package com.opensource.phonebook.shared.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDTOTests extends TestCase {

	final Logger logger = LoggerFactory.getLogger(UserDTOTests.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");

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
	public void testUserCreate() throws Exception {
		System.out.println("testUserCreate: START");
		// =================================================================================
		int id = 0;
		String username = "user_name";
		String password ="password";
		boolean active = true;
		boolean admin = true;
		String dob = "11/03/1966";
		String email = "demo@demo.com";
		String securityQuestion1 = "XXX";
		String securityAnswer1 = "XXX";
		String securityQuestion2 = "YYY";
		String securityAnswer2 = "YYY";
		Date birthDate = sdf.parse(dob);
		// =================================================================================
		System.out.println("testUserCreate: START: CREATE");
		UserDTO user = new UserDTO();
		assertNotNull(user);
		// ***************************************************************
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setActive(active);
		user.setEmail(email);
		user.setSecurityQuestion1(securityQuestion1);
		user.setSecurityAnswer1(securityAnswer1);
		user.setSecurityQuestion2(securityQuestion2);
		user.setSecurityAnswer2(securityAnswer2);
		System.out.println("testUserCreate: user=" + user.toString());
		// ***************************************************************
		assertNotNull(user.getId());
		assertNotNull(user.getUsername());
		assertNotNull(user.getPassword());
		assertNotNull(user.isActive());
		assertNotNull(user.toString());
		// ***************************************************************
		assertEquals(id,user.getId());
		assertEquals(username,user.getUsername());
		assertEquals(password,user.getPassword());
		assertEquals(active,user.isActive());
		assertEquals(email,user.getEmail());
		assertEquals(securityQuestion1,user.getSecurityQuestion1());
		assertEquals(securityAnswer1,user.getSecurityAnswer1());
		assertEquals(securityQuestion2,user.getSecurityQuestion2());
		assertEquals(securityAnswer2,user.getSecurityAnswer2());
		// =================================================================================
		System.out.println("testUserCreate: FINISH: CREATE");
	}

}
