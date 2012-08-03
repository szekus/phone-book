package com.opensource.phonebook.server.rpc;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.opensource.phonebook.client.services.PhoneTypeService;
import com.opensource.phonebook.shared.dto.PhoneTypeDTO;

public class PhoneTypeServiceImplTests  extends TestCase {
	
	private static final Log logger = LogFactory.getLog(PhoneTypeServiceImplTests.class);

	private static ApplicationContext context = null;
	
	private PhoneTypeService service;
	
	@org.junit.Before
	public void setUp() throws Exception {
		context = new org.springframework.context.support.ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
		System.out.println("setUp: context: " + context.toString() );
		service = (PhoneTypeService)context.getBean("phoneTypeService");
		System.out.println("setUp: service: " + service);
	}
	
	@org.junit.After
 	public void tearDown() {
		service = null;
		context = null;
 		System.out.println("tearDown: context set null.");
 	}

	public void testFetchAll() throws Exception {
		PhoneTypeDTO dummy = new PhoneTypeDTO();
		dummy.setDescription("test_description");
		//==================================================
		List<PhoneTypeDTO> phoneTypes = service.fetch();
		System.out.println("phoneTypes: count=" + phoneTypes.size());
		assertNotNull(phoneTypes);
		//==================================================
	}
	
	public void testFetchByExample() throws Exception {
		PhoneTypeDTO dummy = new PhoneTypeDTO();
		dummy.setId(2);
		//==================================================
		List<PhoneTypeDTO> phoneTypes = service.fetch(dummy);
		PhoneTypeDTO phoneType = phoneTypes.get(0);
		System.out.println("phoneTypes: id=" + phoneType.getId());
		System.out.println("phoneTypes: active=" + phoneType.isActive());
		System.out.println("phoneTypes: descr=" + phoneType.getDescription());
		System.out.println("phoneTypes=" + phoneType.toString());
		assertNotNull(phoneType);
		//==================================================
	}
	
	public void testFetchById() throws Exception {
		long id = 3;
		//==================================================
		PhoneTypeDTO phoneType = service.fetch(id);
		System.out.println("phoneTypes: id=" + phoneType.getId());
		System.out.println("phoneTypes: active=" + phoneType.isActive());
		System.out.println("phoneTypes: descr=" + phoneType.getDescription());
		System.out.println("phoneTypes=" + phoneType.toString());
		assertNotNull(phoneType);
		//==================================================
	}

	
}

