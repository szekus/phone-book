package com.opensource.phonebook.server.rpc;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.opensource.phonebook.client.services.UserService;
import com.opensource.phonebook.shared.dto.UserDTO;

public class UserServiceImplTests  extends TestCase {
	
	private static final Log logger = LogFactory.getLog(UserServiceImplTests.class);

	private static ApplicationContext context = null;
	
	private UserService service;
	
	@org.junit.Before
	public void setUp() throws Exception {
		context = new org.springframework.context.support.ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
		System.out.println("setUp: context: " + context.toString() );
		service = (UserService)context.getBean("userService");
		System.out.println("setUp: service: " + service);
	}
	
	@org.junit.After
 	public void tearDown() {
		service = null;
		context = null;
 		System.out.println("tearDown: context set null.");
 	}

	@Test
	public void testFetchAll() throws Exception {
		UserDTO dummy = new UserDTO();
		dummy.setFirstname("test_description");
		//==================================================
		List<UserDTO> users = service.fetch();
		System.out.println("users: count=" + users.size());
		assertNotNull(users);
		//==================================================
	}
	
	@Test
	public void testFetchByExample() throws Exception {
		UserDTO dummy = new UserDTO();
		dummy.setUsername("demo");
		//==================================================
		List<UserDTO> users = service.fetch(dummy);
		UserDTO user = users.get(0);
		System.out.println("testFetchByExample: users: id=" + user.getId());
		System.out.println("testFetchByExample: users: active=" + user.isActive());
		System.out.println("testFetchByExample: users: descr=" + user.getFirstname());
		System.out.println("testFetchByExample: users=" + user.toString());
		assertNotNull(user);
		//==================================================
	}
	
	@Test
	public void testFetchById() throws Exception {
		long id = 1;
		//==================================================
		UserDTO user = service.fetch(id);
		System.out.println("users: id=" + user.getId());
		System.out.println("users: active=" + user.isActive());
		System.out.println("users: descr=" + user.getFirstname());
		System.out.println("users=" + user.toString());
		assertNotNull(user);
		//==================================================
	}
	
}

