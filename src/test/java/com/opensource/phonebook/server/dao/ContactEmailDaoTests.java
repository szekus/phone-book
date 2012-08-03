package com.opensource.phonebook.server.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactEmailDTO;
import com.opensource.phonebook.shared.dto.EmailTypeDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class ContactEmailDaoTests extends TestCase {

	final Logger logger = LoggerFactory.getLogger(ContactEmailDaoTests.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	@Autowired
	private ContactDao contactDao;
	@Autowired
	private ContactEmailDao contactEmailDao;
	
	private long id  = 2;
	private boolean active = true;
	private String address1 = "123 main street";
	private String address2 ="Apt. 456";
	private boolean admin = false;
	private String dob = "11/03/1966";
	private Date birthDate = null;
	private String city ="Randolph";
	private long companyId = 0;
	private String firstName = "first_name";
	private String lastName = "last_name";
	private String password = "password";
	private String username = "username";
	private String prefix = "Mr.";
	private String suffix = "Jr.";
	private String state = "MA";
	private String zip ="12345-1234";
	private String emailNew = "tom@tomholmes.net";
	private String emailUpd = "tholmes@yahoo.com";
	private long emailTypeId = 1;
	
	private ContactDTO contact;

	protected void setUp() throws Exception {
		System.out.println("setup: Loading application context");
		System.out.println("setup: Done loading application context");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		System.out.println("tearDown: START");
		System.out.println("tearDown: FINISH");
		contact = null;
	}
	
	@Test
	public void testCreate() throws Exception {
		System.out.println("testCreate: START");
		// =================================================================================
		EmailTypeDTO emailType = new EmailTypeDTO();
		emailType.setId(emailTypeId);
		contact = contactDao.getContactDTO(id);
		// =================================================================================
		ContactEmailDTO contactEmail = new ContactEmailDTO();
		//contactEmail.setId(id);
		contactEmail.setContact(contact);
		contactEmail.setEmail(emailNew);
		contactEmail.setEnteredDate(new Date());	
		contactEmail.setEmailType(emailType);
		System.out.println("testCreate: " + contactEmail.toString());
		// ***************************************************************
		System.out.println("testCreate: START: CREATE");
		contactEmail = contactEmailDao.saveContactEmailDTO(contactEmail);
		assertNotNull(contactEmail);
		System.out.println("testCreate: FINISH: CREATE");
		// =================================================================================
		contactEmailDao.deleteContactEmailDTO(contactEmail);
		// =================================================================================
	}
	
	@Test
	public void testContactRetrieve() {
		System.out.println("testContactRetrieve: START");
		// =================================================================================
		// =================================================================================
		// ***************************************************************
		System.out.println("testContactRetrieve: START: CREATE");
		List<ContactEmailDTO> contactEmails = contactEmailDao.getAllContactEmailDTOs();
		assertNotNull(contactEmails);
		for(ContactEmailDTO contactEmail:contactEmails) {
			assertNotNull(contactEmail.getEmailId());
			assertNotNull(contactEmail.getContact());
			assertNotNull(contactEmail.getEmailType());
			assertEquals(contactEmail.getContact().getId(),id);
			assertEquals(contactEmail.getEmailType().getId(),emailTypeId);
			//************************************************************
			System.out.println("testContactRetrieve: contactEmail=" + contactEmail.toString());
		}
		System.out.println("testContactRetrieve: FINISH: CREATE");
		// =================================================================================
	}
	
	@Test
	public void testContactRetrieveById() {
		System.out.println("testContactRetrieveById: START");
		// =================================================================================
		// =================================================================================
		// ***************************************************************
		System.out.println("testContactRetrieveById: START: CREATE");
		ContactEmailDTO contactEmail = contactEmailDao.getContactEmailDTO(id);
		assertNotNull(contactEmail.getEmailId());
		//************************************************************
		assertNotNull(contactEmail.getEmailId());
		assertNotNull(contactEmail.getContact());
		assertNotNull(contactEmail.getEmailType());
		assertEquals(contactEmail.getContact().getId(),id);
		assertEquals(contactEmail.getEmailType().getId(),emailTypeId);
		//************************************************************
		System.out.println("testContactRetrieve: contactEmail=" + contactEmail.toString());
		System.out.println("testContactRetrieveById: FINISH: CREATE");
		// =================================================================================
	}
	
	public void XtestContactDelete() {
		System.out.println("testContactDelete: START");
		// =================================================================================
		ContactEmailDTO contactEmail;
		ContactEmailDTO contactEmailGet;
		// =================================================================================
		// ***************************************************************
		int id = 10;
		contactEmail = contactEmailDao.getContactEmailDTO(id);
		contactEmailDao.deleteContactEmailDTO(contactEmail);
		contactEmailGet =  contactEmailDao.getContactEmailDTO(id);
		assertEquals(null,contactEmailGet);
		// ***************************************************************
		System.out.println("testContactDelete: FINISH: CREATE");
		// =================================================================================
	}

	/*
	public void X_testContactEmailDTOByName() {
		System.out.println("testContactEmailDTOByName: START");
		// =================================================================================
		String interestName1 = "TEST";
		String interestUuid1 = "AAA";
		String interestPath1 = "ABC/AAA";
		ContactEmailDTO interest1 = new ContactEmailDTO();
		interest1.setContactEmailDTOName(interestName1);
		interest1.setContactEmailDTOUuid(interestUuid1);
		interest1.setContactEmailDTOPath(interestPath1);
		System.out.println("testContactEmailDTOByName: " + interestName1 + " " + interestUuid1 + " " + interestPath1);
		interest1 = contactEmailDao.saveContactEmailDTO(interest1);
		assertNotNull(interest1);
		// =================================================================================
		String interestName2 = "TEST";
		String interestUuid2 = "BBB";
		String interestPath2 = "ABC/BBB";
		ContactEmailDTO interest2 = new ContactEmailDTO();
		interest2.setContactEmailDTOName(interestName2);
		interest2.setContactEmailDTOUuid(interestUuid2);
		interest2.setContactEmailDTOPath(interestPath2);
		System.out.println("testContactEmailDTOByName: " + interestName2 + " " + interestUuid2 + " " + interestPath2);
		interest2 = contactEmailDao.saveContactEmailDTO(interest2);
		assertNotNull(interest2);
		// =================================================================================
		String interestName3 = "TEST";
		String interestUuid3 = "BBB";
		String interestPath3 = "ABC/BBB";
		ContactEmailDTO interest3 = new ContactEmailDTO();
		interest3.setContactEmailDTOName(interestName3);
		interest3.setContactEmailDTOUuid(interestUuid3);
		interest3.setContactEmailDTOPath(interestPath3);
		System.out.println("testContactEmailDTOByName: " + interestName3 + " " + interestUuid3 + " " + interestPath3);
		interest3 = contactEmailDao.saveContactEmailDTO(interest3);
		assertNotNull(interest2);
		// =================================================================================
		String interestName = "TEST";
		List<ContactEmailDTO> interests = contactEmailDao.getContactEmailDTOsByName(interestName);
		System.out.println("testContactEmailDTOByName: interests: size=" + interests.size() );
		assertEquals(3,interests.size());
		// =================================================================================
		System.out.println("testContactEmailDTOCRUS: START: DELETE");
		contactEmailDao.deleteContactEmailDTO(interest1);
		contactEmailDao.deleteContactEmailDTO(interest2);
		contactEmailDao.deleteContactEmailDTO(interest3);
		System.out.println("testContactEmailDTOCRUS: FINISH: DELETE");
		// =================================================================================
	}
	*/
	
	//@Test
	public void testContactUpdate() {
		System.out.println("testContactUpdate: START");
		// =================================================================================
		String updateCity ="Randolph";
		String updateFirstName = "updated_fn";
		String updateLastName = "updated_ln";
		String updatePassword = "updated_pwd";
		String updateUsername = "updated_username";
		// =================================================================================
		ContactEmailDTO contactEmail = contactEmailDao.getContactEmailDTO(id);
//		assertEquals(contactEmail.getAddress1(),address1);
//		assertEquals(contactEmail.getAddress2(),address2);
//		assertEquals(contactEmail.getCity(),city);
//		assertEquals(contactEmail.getCompanyId(),companyId);
//		assertEquals(contactEmail.getFirstName(),firstName);
//		assertEquals(contactEmail.getId(),id);
//		assertEquals(contactEmail.getLastName(),lastName);
//		//assertEquals(contactEmail.getMiddleName(),middleName);
//		assertEquals(contactEmail.getPassword(),password);
//		assertEquals(contactEmail.getPrefix(),prefix);
//		assertEquals(contactEmail.getState(),state);
//		assertEquals(contactEmail.getSuffix(),suffix);
//		assertEquals(contactEmail.getUsername(),username);
//		assertEquals(contactEmail.getZip(),zip);
		System.out.println("testContactUpdate: " + contactEmail.toString());
		// ***************************************************************
//		contactEmail.setCity(updateCity);
//		contactEmail.setFirstName(updateFirstName);
//		contactEmail.setLastName(updateLastName);
//		contactEmail.setPassword(updatePassword);
//		contactEmail.setUsername(updateUsername);
		// ***************************************************************
		System.out.println("testContactUpdate: START: CREATE");
		contactEmail = contactEmailDao.saveContactEmailDTO(contactEmail);
		assertNotNull(contactEmail);
//		assertEquals(contactEmail.getCity(),updateCity);
//		assertEquals(contactEmail.getFirstName(),updateFirstName);
//		assertEquals(contactEmail.getLastName(),updateLastName);
//		assertEquals(contactEmail.getPassword(),updatePassword);
//		assertEquals(contactEmail.getUsername(),updateUsername);
		System.out.println("testContactUpdate: FINISH: CREATE");
		// =================================================================================
	}
	

}


