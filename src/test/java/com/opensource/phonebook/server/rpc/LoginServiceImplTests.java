package com.opensource.phonebook.server.rpc;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensource.phonebook.client.services.LoginService;
import com.opensource.phonebook.shared.dto.UserDTO;

@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginServiceImplTests extends TestCase {
	
	private static final Log logger = LogFactory.getLog(LoginServiceImplTests.class);

	private static ApplicationContext context = null;
	
	@Autowired
	private LoginService service;
	
	public LoginService getService() {
		return service;
	}

	public void setService(LoginService service) {
		this.service = service;
	}

	@org.junit.Before
	public void setUp() throws Exception {
		//context = new org.springframework.context.support.ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
		//System.out.println("setUp: context: " + context.toString() );
		//service = (LoginService)context.getBean("loginService");
		//System.out.println("setUp: service: " + service);
	}
	
	@org.junit.After
 	public void tearDown() {
		service = null;
		context = null;
 		System.out.println("tearDown: context set null.");
 	}

	@Test
	public void testLoginExistingUser() throws Exception {
		String username = "demo";
		String password = "demo";
		UserDTO user = service.login(username, password);
		assertNotNull(user);
	}
	
	@Test
	public void testLoginExistingUserBadPassword() throws Exception {
		String username = "demo";
		String password = "XXX";
		UserDTO user = service.login(username, password);
		assertNull(user);
	}
	
	@Test
	public void testLoginNonExistingUser() throws Exception {
		String username = "XXX";
		String password = "XXX";
		UserDTO user = service.login(username, password);
		assertNull(user);
	}

}
