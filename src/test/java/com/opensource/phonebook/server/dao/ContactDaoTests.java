package com.opensource.phonebook.server.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibm.icu.text.SimpleDateFormat;
import com.opensource.phonebook.domain.ContactEmailEntity;
import com.opensource.phonebook.domain.ContactEntity;
import com.opensource.phonebook.domain.EmailTypeEntity;
import com.opensource.phonebook.domain.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class ContactDaoTests extends TestCase {

	final Logger logger = LoggerFactory.getLogger(ContactDaoTests.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	@Autowired
	private ContactDao contactDao;
	
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
	
	protected void setUp() throws Exception {
		System.out.println("setup: Loading application context");
		System.out.println("setup: Done loading application context");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		System.out.println("tearDown: START");
		System.out.println("tearDown: FINISH");
	}
	
	private HashSet<ContactEmailEntity> createEmails(ContactEntity contact) {
		HashSet<ContactEmailEntity> emails = new HashSet<ContactEmailEntity>();
		logger.debug("createEmails: START");
		ContactEmailEntity contactEmail = null;
		//============================================================
		String email1 = "tom1@tomholmes1.net";
		EmailTypeEntity emailType1 = new EmailTypeEntity();
		emailType1.setId(1);
		contactEmail = new ContactEmailEntity();
		contactEmail.setContact(contact);
		contactEmail.setEmail(email1);
		contactEmail.setEnteredDate(new Date());	
		contactEmail.setEmailType(emailType1);
		emails.add(contactEmail);
		//**********************************************
		String email2 = "tom2@tomholmes2.net";
		EmailTypeEntity emailType2 = new EmailTypeEntity();
		emailType2.setId(2);
		contactEmail = new ContactEmailEntity();
		contactEmail.setContact(contact);
		contactEmail.setEmail(email2);
		contactEmail.setEnteredDate(new Date());	
		contactEmail.setEmailType(emailType2);
		emails.add(contactEmail);
		//**********************************************
		String email3 = "tom3@tomholmes3.net";
		EmailTypeEntity emailType3 = new EmailTypeEntity();
		emailType3.setId(3);
		contactEmail = new ContactEmailEntity();
		contactEmail.setContact(contact);
		contactEmail.setEmail(email3);
		contactEmail.setEnteredDate(new Date());	
		contactEmail.setEmailType(emailType3);
		emails.add(contactEmail);
		//============================================================
		logger.debug("createEmails: FINISH");
		return emails;
	}
	
	@Test
    public void testContactFetchByUser() throws Exception {
        System.out.println("testContactFetchByUser: START");
        UserEntity user = new UserEntity();
        user.setId(1);
        
        List<ContactEntity> contacts = contactDao.getContactEntityByUser(user);
        if(contacts != null)
        {
            System.out.println("testContactFetchByUser: size=" + contacts.size());
        }
        assertNotNull(contacts);
        
        System.out.println("testContactFetchByUser: FINISH");
    }
	
	//@Test
	public void testContactSave() throws Exception {
		System.out.println("testContactSave: START");
		// =================================================================================
//		long id  = 1;
//		boolean active = true;
//		String address1 = "123 main street";
//		String address2 ="Apt. 456";
//		boolean admin = false;
//		String dob = "11/03/1966";
//		Date birthDate = sdf.parse(dob);
//		String city ="Randolph";
//		long companyId = 0;
//		String firstName = "first_name";
//		String lastName = "last_name";
//		String password = "password";
//		String username = "username";
//		String prefix = "Mr.";
//		String suffix = "Jr.";
//		String state = "MA";
//		String zip ="12345-1234";
		// =================================================================================
		ContactEntity contact = new ContactEntity();
		//contact.setId(id);
		contact.setAddress1(address1);
		contact.setAddress2(address2);
		contact.setAdmin(admin);
		contact.setBirthDate(birthDate);
		contact.setCity(city);
		contact.setCompanyId(companyId);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setPrefix(prefix);
		contact.setState(state);
		contact.setSuffix(suffix);
		contact.setZip(zip);
		contact.setEmails(createEmails(contact));
		System.out.println("testContactSave: " + contact.toString());
		// ***************************************************************
		System.out.println("testContactSave: START: CREATE");
		contact = contactDao.saveContactEntity(contact);
		assertNotNull(contact);
		System.out.println("testContactSave: FINISH: CREATE");
		// =================================================================================
	}
	
	@Test
	public void testContactRetrieve() {
		System.out.println("testContactRetrieve: START");
		// =================================================================================
		// =================================================================================
		// ***************************************************************
		System.out.println("testContactRetrieve: START: CREATE");
		List<ContactEntity> contacts = contactDao.getAllContactEntitys();
		assertNotNull(contacts);
		for(ContactEntity contact:contacts) {
			assertNotNull(contact.getId());
			//************************************************************
//			assertEquals(contact.getAddress1(),address1);
//			assertEquals(contact.getAddress2(),address2);
//			assertEquals(contact.getCity(),city);
//			assertEquals(contact.getCompanyId(),companyId);
//			assertEquals(contact.getFirstName(),firstName);
//			assertEquals(contact.getId(),id);
//			assertEquals(contact.getLastName(),lastName);
//			//assertEquals(contact.getMiddleName(),middleName);
//			assertEquals(contact.getPassword(),password);
//			assertEquals(contact.getPrefix(),prefix);
//			assertEquals(contact.getState(),state);
//			assertEquals(contact.getSuffix(),suffix);
//			assertEquals(contact.getUsername(),username);
//			assertEquals(contact.getZip(),zip);
			//************************************************************
			System.out.println("testContactRetrieve: contact=" + contact.toString());
		}
		System.out.println("testContactRetrieve: FINISH: CREATE");
		// =================================================================================
	}
	
	@Test
	public void testContactRetrieveById() {
		System.out.println("testContactRetrieveById: START");
		// =================================================================================
		long id = 2;
		// =================================================================================
		// ***************************************************************
		System.out.println("testContactRetrieveById: START: CREATE");
		ContactEntity contact = contactDao.getContactEntity(id);
		assertNotNull(contact.getId());
		//************************************************************
		assertEquals(contact.getAddress1(),address1);
		assertEquals(contact.getAddress2(),address2);
		assertEquals(contact.getCity(),city);
		assertEquals(contact.getCompanyId(),companyId);
		//assertEquals(contact.getFirstName(),firstName);
		assertEquals(contact.getId(),id);
		//assertEquals(contact.getLastName(),lastName);
		//assertEquals(contact.getMiddleName(),middleName);
		//assertEquals(contact.getPassword(),password);
		assertEquals(contact.getPrefix(),prefix);
		assertEquals(contact.getState(),state);
		assertEquals(contact.getSuffix(),suffix);
		//assertEquals(contact.getUsername(),username);
		assertEquals(contact.getZip(),zip);
		assertEquals(4,contact.getEmails().size());
		assertEquals(0,contact.getPhones().size());
		assertEquals(0,contact.getLinks().size());
		//************************************************************
		System.out.println("testContactRetrieveById: contact=" + contact.toString());
		System.out.println("testContactRetrieveById: FINISH: CREATE");
		// =================================================================================
	}
	
	//@Test
	public void XtestContactDelete() {
		System.out.println("testContactDelete: START");
		long id = 4;
		// =================================================================================
		ContactEntity contact = contactDao.getContactEntity(id);
		assertNotNull(contact);
		System.out.println("testContactDelete: " + contact.toString());
		contactDao.deleteContactEntity(contact);
		System.out.println("testContactDelete: contact deleted");
		contact = contactDao.getContactEntity(id);
		assertEquals(contact,null);
		// ***************************************************************
		System.out.println("testContactDelete: FINISH");
		// =================================================================================
	}

	/*
	public void X_testContactEntityByName() {
		System.out.println("testContactEntityByName: START");
		// =================================================================================
		String interestName1 = "TEST";
		String interestUuid1 = "AAA";
		String interestPath1 = "ABC/AAA";
		ContactEntity interest1 = new ContactEntity();
		interest1.setContactEntityName(interestName1);
		interest1.setContactEntityUuid(interestUuid1);
		interest1.setContactEntityPath(interestPath1);
		System.out.println("testContactEntityByName: " + interestName1 + " " + interestUuid1 + " " + interestPath1);
		interest1 = contactDao.saveContactEntity(interest1);
		assertNotNull(interest1);
		// =================================================================================
		String interestName2 = "TEST";
		String interestUuid2 = "BBB";
		String interestPath2 = "ABC/BBB";
		ContactEntity interest2 = new ContactEntity();
		interest2.setContactEntityName(interestName2);
		interest2.setContactEntityUuid(interestUuid2);
		interest2.setContactEntityPath(interestPath2);
		System.out.println("testContactEntityByName: " + interestName2 + " " + interestUuid2 + " " + interestPath2);
		interest2 = contactDao.saveContactEntity(interest2);
		assertNotNull(interest2);
		// =================================================================================
		String interestName3 = "TEST";
		String interestUuid3 = "BBB";
		String interestPath3 = "ABC/BBB";
		ContactEntity interest3 = new ContactEntity();
		interest3.setContactEntityName(interestName3);
		interest3.setContactEntityUuid(interestUuid3);
		interest3.setContactEntityPath(interestPath3);
		System.out.println("testContactEntityByName: " + interestName3 + " " + interestUuid3 + " " + interestPath3);
		interest3 = contactDao.saveContactEntity(interest3);
		assertNotNull(interest2);
		// =================================================================================
		String interestName = "TEST";
		List<ContactEntity> interests = contactDao.getContactEntitysByName(interestName);
		System.out.println("testContactEntityByName: interests: size=" + interests.size() );
		assertEquals(3,interests.size());
		// =================================================================================
		System.out.println("testContactEntityCRUS: START: DELETE");
		contactDao.deleteContactEntity(interest1);
		contactDao.deleteContactEntity(interest2);
		contactDao.deleteContactEntity(interest3);
		System.out.println("testContactEntityCRUS: FINISH: DELETE");
		// =================================================================================
	}
	*/
	
	//@Test
	public void testContactUpdate() {
		System.out.println("testContactUpdate: START");
		id = 4;
		// =================================================================================
		String updateCity ="upd_Randolph4";
		String updateFirstName = "updated_fn4";
		String updateLastName = "updated_ln4";
		String updatePassword = "updated_pwd4";
		String updateUsername = "updated_username4";
		// =================================================================================
		ContactEntity contact = contactDao.getContactEntity(id);
//		assertEquals(contact.getAddress1(),address1);
//		assertEquals(contact.getAddress2(),address2);
//		assertEquals(contact.getCity(),city);
//		assertEquals(contact.getCompanyId(),companyId);
//		assertEquals(contact.getFirstName(),firstName);
//		assertEquals(contact.getId(),id);
//		assertEquals(contact.getLastName(),lastName);
//		//assertEquals(contact.getMiddleName(),middleName);
//		assertEquals(contact.getPassword(),password);
//		assertEquals(contact.getPrefix(),prefix);
//		assertEquals(contact.getState(),state);
//		assertEquals(contact.getSuffix(),suffix);
//		assertEquals(contact.getUsername(),username);
//		assertEquals(contact.getZip(),zip);
		System.out.println("testContactUpdate: " + contact.toString());
		// ***************************************************************
		contact.setCity(updateCity);
		contact.setFirstName(updateFirstName);
		contact.setLastName(updateLastName);
		// ***************************************************************
		
		ContactEmailEntity contactEmail = null;
		for(ContactEmailEntity x : contact.getEmails() )
		{
			if(x.getEmailId()==11)
			{
				contactEmail = x;
			}
		}
			
		contact.getEmails().remove(contactEmail);
		// ***************************************************************
		System.out.println("testContactUpdate: START: CREATE");
		contact = contactDao.saveContactEntity(contact);
		assertNotNull(contact);
		assertEquals(contact.getCity(),updateCity);
		assertEquals(contact.getFirstName(),updateFirstName);
		assertEquals(contact.getLastName(),updateLastName);
		assertEquals(contact.getEmails().size(),2);
		System.out.println("testContactUpdate: FINISH: CREATE");
		// =================================================================================
	}
	

}

