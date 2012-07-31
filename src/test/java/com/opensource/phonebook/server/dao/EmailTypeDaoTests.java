package com.opensource.phonebook.server.dao;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensource.phonebook.shared.dto.EmailTypeDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class EmailTypeDaoTests extends TestCase {

	final Logger logger = LoggerFactory.getLogger(EmailTypeDaoTests.class);
	
	@Autowired
	private EmailTypeDao emailTypeDao;

	protected void setUp() throws Exception {
		System.out.println("setup: Loading application context");
		System.out.println("setup: Done loading application context");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		System.out.println("tearDown: START");
		System.out.println("tearDown: FINISH");
	}
	
	public void XtestEmailTypeSave() {
		System.out.println("testEmailTypeSave: START");
		// =================================================================================
		String emailTypeName = "Test";
		boolean emailActive = true;
		String emailTypeDescription = "Test Description";
		// =================================================================================
		EmailTypeDTO emailType = new EmailTypeDTO();
		emailType.setId(0);
		emailType.setActive(emailActive);
		emailType.setDescription(emailTypeDescription);
		System.out.println("testEmailTypeSave: " + emailTypeName + " " + emailTypeDescription);
		// ***************************************************************
		System.out.println("testEmailTypeSave: START: CREATE");
		emailType = emailTypeDao.saveEmailTypeDTO(emailType);
		assertNotNull(emailType);
		System.out.println("testEmailTypeSave: FINISH: CREATE");
		// =================================================================================
	}
	
	public void XtestEmailTypeUpdate() {
		System.out.println("testEmailTypeUpdate: START");
		// =================================================================================
		String emailTypeName = "Test Upd";
		boolean emailActive = true;
		String emailTypeDescription = "Test Description Update";
		// =================================================================================
		EmailTypeDTO emailType = new EmailTypeDTO();
		emailType.setId(0);
		emailType.setActive(emailActive);
		emailType.setDescription(emailTypeDescription);
		System.out.println("testEmailTypeUpdate: " + emailTypeName + " " + emailTypeDescription);
		// ***************************************************************
		System.out.println("testEmailTypeUpdate: START: CREATE");
		emailType = emailTypeDao.saveEmailTypeDTO(emailType);
		assertNotNull(emailType);
		assertEquals(emailActive,emailType.isActive());
		assertEquals(emailTypeDescription,emailType.getDescription());
		System.out.println("testEmailTypeUpdate: FINISH: CREATE");
		// =================================================================================
	}
	
	@Test
	public void testEmailTypeRetrieve() {
		System.out.println("testEmailTypeRetrieve: START");
		// =================================================================================
		// =================================================================================
		// ***************************************************************
		System.out.println("testEmailTypeRetrieve: START: CREATE");
		List<EmailTypeDTO> emailTypes = emailTypeDao.getAllEmailTypeDTOs();
		assertNotNull(emailTypes);
		for(EmailTypeDTO emailType:emailTypes) {
			assertNotNull(emailType.getId());
			assertNotNull(emailType.isActive());
			assertNotNull(emailType.getDescription());
			System.out.println("testEmailTypeRetrieve: emailType=" + emailType.getId() + " " + emailType.isActive() + " " + emailType.getDescription());
		}
		System.out.println("testEmailTypeRetrieve: FINISH: CREATE");
		// =================================================================================
	}
	
	@Test
	public void testEmailTypeRetrieveById() {
		System.out.println("testEmailTypeRetrieveById: START");
		// =================================================================================
		// =================================================================================
		// ***************************************************************
		System.out.println("testEmailTypeRetrieveById: START: CREATE");
		EmailTypeDTO emailType = emailTypeDao.getEmailTypeDTO(1);
		assertNotNull(emailType.getId());
		assertNotNull(emailType.isActive());
		assertNotNull(emailType.getDescription());
		System.out.println("testEmailTypeRetrieveById: emailType=" + emailType.getId() + " " + emailType.isActive() + " " + emailType.getDescription());
		System.out.println("testEmailTypeRetrieveById: FINISH: CREATE");
		// =================================================================================
	}
	
	public void XtestEmailTypeDelete() {
		System.out.println("testEmailTypeDelete: START");
		// =================================================================================
		EmailTypeDTO emailType;
		EmailTypeDTO emailTypeGet;
		// =================================================================================
		// ***************************************************************
		int id = 10;
		emailType = emailTypeDao.getEmailTypeDTO(id);
		emailTypeDao.deleteEmailTypeDTO(emailType);
		emailTypeGet =  emailTypeDao.getEmailTypeDTO(id);
		assertEquals(null,emailTypeGet);
		// ***************************************************************
		System.out.println("testEmailTypeDelete: FINISH: CREATE");
		// =================================================================================
	}

	/*
	public void X_testEmailTypeDTOByName() {
		System.out.println("testEmailTypeDTOByName: START");
		// =================================================================================
		String interestName1 = "TEST";
		String interestUuid1 = "AAA";
		String interestPath1 = "ABC/AAA";
		EmailTypeDTO interest1 = new EmailTypeDTO();
		interest1.setEmailTypeDTOName(interestName1);
		interest1.setEmailTypeDTOUuid(interestUuid1);
		interest1.setEmailTypeDTOPath(interestPath1);
		System.out.println("testEmailTypeDTOByName: " + interestName1 + " " + interestUuid1 + " " + interestPath1);
		interest1 = emailTypeDao.saveEmailTypeDTO(interest1);
		assertNotNull(interest1);
		// =================================================================================
		String interestName2 = "TEST";
		String interestUuid2 = "BBB";
		String interestPath2 = "ABC/BBB";
		EmailTypeDTO interest2 = new EmailTypeDTO();
		interest2.setEmailTypeDTOName(interestName2);
		interest2.setEmailTypeDTOUuid(interestUuid2);
		interest2.setEmailTypeDTOPath(interestPath2);
		System.out.println("testEmailTypeDTOByName: " + interestName2 + " " + interestUuid2 + " " + interestPath2);
		interest2 = emailTypeDao.saveEmailTypeDTO(interest2);
		assertNotNull(interest2);
		// =================================================================================
		String interestName3 = "TEST";
		String interestUuid3 = "BBB";
		String interestPath3 = "ABC/BBB";
		EmailTypeDTO interest3 = new EmailTypeDTO();
		interest3.setEmailTypeDTOName(interestName3);
		interest3.setEmailTypeDTOUuid(interestUuid3);
		interest3.setEmailTypeDTOPath(interestPath3);
		System.out.println("testEmailTypeDTOByName: " + interestName3 + " " + interestUuid3 + " " + interestPath3);
		interest3 = emailTypeDao.saveEmailTypeDTO(interest3);
		assertNotNull(interest2);
		// =================================================================================
		String interestName = "TEST";
		List<EmailTypeDTO> interests = emailTypeDao.getEmailTypeDTOsByName(interestName);
		System.out.println("testEmailTypeDTOByName: interests: size=" + interests.size() );
		assertEquals(3,interests.size());
		// =================================================================================
		System.out.println("testEmailTypeDTOCRUS: START: DELETE");
		emailTypeDao.deleteEmailTypeDTO(interest1);
		emailTypeDao.deleteEmailTypeDTO(interest2);
		emailTypeDao.deleteEmailTypeDTO(interest3);
		System.out.println("testEmailTypeDTOCRUS: FINISH: DELETE");
		// =================================================================================
	}
	*/

}
