package com.opensource.phonebook.server.rpc;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.opensource.phonebook.client.services.LinkTypeService;
import com.opensource.phonebook.shared.dto.LinkTypeDTO;

public class LinkTypeServiceImplTests  extends TestCase {
	
	private static final Log logger = LogFactory.getLog(LinkTypeServiceImplTests.class);

	private static ApplicationContext context = null;
	
	private LinkTypeService service;
	
	@org.junit.Before
	public void setUp() throws Exception {
		context = new org.springframework.context.support.ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
		System.out.println("setUp: context: " + context.toString() );
		service = (LinkTypeService)context.getBean("linkTypeService");
		System.out.println("setUp: service: " + service);
	}
	
	@org.junit.After
 	public void tearDown() {
		service = null;
		context = null;
 		System.out.println("tearDown: context set null.");
 	}

	public void testFetchAll() throws Exception {
		LinkTypeDTO dummy = new LinkTypeDTO();
		dummy.setDescription("test_description");
		//==================================================
		List<LinkTypeDTO> linkTypes = service.fetch();
		System.out.println("linkTypes: count=" + linkTypes.size());
		assertNotNull(linkTypes);
		//==================================================
	}
	
	public void testFetchByExample() throws Exception {
		LinkTypeDTO dummy = new LinkTypeDTO();
		dummy.setId(2);
		//==================================================
		List<LinkTypeDTO> linkTypes = service.fetch(dummy);
		LinkTypeDTO linkType = linkTypes.get(0);
		System.out.println("linkTypes: id=" + linkType.getId());
		System.out.println("linkTypes: active=" + linkType.isActive());
		System.out.println("linkTypes: descr=" + linkType.getDescription());
		System.out.println("linkTypes=" + linkType.toString());
		assertNotNull(linkType);
		//==================================================
	}
	
	public void testFetchById() throws Exception {
		long id = 3;
		//==================================================
		LinkTypeDTO linkType = service.fetch(id);
		System.out.println("linkTypes: id=" + linkType.getId());
		System.out.println("linkTypes: active=" + linkType.isActive());
		System.out.println("linkTypes: descr=" + linkType.getDescription());
		System.out.println("linkTypes=" + linkType.toString());
		assertNotNull(linkType);
		//==================================================
	}

	
}

