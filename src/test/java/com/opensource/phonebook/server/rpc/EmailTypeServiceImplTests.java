package com.opensource.phonebook.server.rpc;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.opensource.phonebook.client.services.EmailTypeService;
import com.opensource.phonebook.shared.dto.EmailTypeDTO;

public class EmailTypeServiceImplTests  extends TestCase {
	
	private static final Log logger = LogFactory.getLog(EmailTypeServiceImplTests.class);

	private static ApplicationContext context = null;
	
	private EmailTypeService service;
	
	@org.junit.Before
	public void setUp() throws Exception {
		context = new org.springframework.context.support.ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
		System.out.println("setUp: context: " + context.toString() );
		service = (EmailTypeService)context.getBean("emailTypeService");
		System.out.println("setUp: service: " + service);
	}
	
	@org.junit.After
 	public void tearDown() {
		service = null;
		context = null;
 		System.out.println("tearDown: context set null.");
 	}

	public void testFetchAll() throws Exception {
		EmailTypeDTO dummy = new EmailTypeDTO();
		dummy.setDescription("test_description");
		//==================================================
		List<EmailTypeDTO> emailTypes = service.fetch();
		System.out.println("emailTypes: count=" + emailTypes.size());
		assertNotNull(emailTypes);
		//==================================================
	}
	
	public void testFetchByExample() throws Exception {
		EmailTypeDTO dummy = new EmailTypeDTO();
		dummy.setId(2);
		//==================================================
		List<EmailTypeDTO> emailTypes = service.fetch(dummy);
		EmailTypeDTO emailType = emailTypes.get(0);
		System.out.println("emailTypes: id=" + emailType.getId());
		System.out.println("emailTypes: active=" + emailType.isActive());
		System.out.println("emailTypes: descr=" + emailType.getDescription());
		System.out.println("emailTypes=" + emailType.toString());
		assertNotNull(emailType);
		//==================================================
	}
	
	public void testFetchById() throws Exception {
		long id = 3;
		//==================================================
		EmailTypeDTO emailType = service.fetch(id);
		System.out.println("emailTypes: id=" + emailType.getId());
		System.out.println("emailTypes: active=" + emailType.isActive());
		System.out.println("emailTypes: descr=" + emailType.getDescription());
		System.out.println("emailTypes=" + emailType.toString());
		assertNotNull(emailType);
		//==================================================
	}

	
}
