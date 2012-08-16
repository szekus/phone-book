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
import com.opensource.phonebook.domain.PositionEntity;
import com.opensource.phonebook.domain.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class UserDaoTests extends TestCase {

	final Logger logger = LoggerFactory.getLogger(UserDaoTests.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	@Autowired
	private ContactDao contactDao;
	
	@Autowired
	private UserDao userDao;
	
	private long id  = 2;
	private boolean active = true;
	private String address1 = "123 main street";
	private String address2 ="Apt. 456";
	private boolean admin = false;
	private String dob = "11/03/1966";
	private Date birthDate = null;
	private String city ="Randolph";
	private long companyId = 0;
	private String firstname = "first_name";
	private String lastname = "last_name";
	private String password = "password";
	private String username = "username";
	private String prefix = "Mr.";
	private String suffix = "Jr.";
	private String state = "MA";
	private String zip ="12345-1234";
	private long positionId = 2;
	
	private String email = "tom@tomholmes.net";
	private String securityQuestion1 = "Meaning of Life?";
	private String securityAnswer1 = "42";
	private String securityQuestion2 = "What city were you born in?";
	private String securityAnswer2 = "Fall River";
	
	private ContactEntity contactEntity = null;
	
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
	
	private HashSet<ContactEntity> createContacts(UserEntity user) throws Exception {
		System.out.println("createContacts: START");
		HashSet<ContactEntity> contacts = new HashSet<ContactEntity>();
		// =================================================================================
		ContactEntity contact1 = new ContactEntity();
		contact1.setUser(user);
		contact1.setAddress1(address1);
		contact1.setAddress2(address2);
		contact1.setAdmin(admin);
		contact1.setBirthDate(birthDate);
		contact1.setCity(city);
		contact1.setCompanyId(companyId);
		contact1.setFirstName(firstname);
		contact1.setLastName(lastname);
		contact1.setPrefix(prefix);
		contact1.setState(state);
		contact1.setSuffix(suffix);
		contact1.setZip(zip);
		contact1.setEmails(createEmails(contact1));
		contacts.add(contact1);
		System.out.println("createContacts: " + contact1.toString());
		// =================================================================================
		ContactEntity contact2 = new ContactEntity();
		contact2.setUser(user);
		contact2.setAddress1(address1);
		contact2.setAddress2(address2);
		contact2.setAdmin(admin);
		contact2.setBirthDate(birthDate);
		contact2.setCity(city);
		contact2.setCompanyId(companyId);
		contact2.setFirstName(firstname);
		contact2.setLastName(lastname);
		contact2.setPrefix(prefix);
		contact2.setState(state);
		contact2.setSuffix(suffix);
		contact2.setZip(zip);
		contact2.setEmails(createEmails(contact2));
		contacts.add(contact2);
		System.out.println("createContacts: " + contact2.toString());
		// =================================================================================
		ContactEntity contact3 = new ContactEntity();
		contact3.setUser(user);
		contact3.setAddress1(address1);
		contact3.setAddress2(address2);
		contact3.setAdmin(admin);
		contact3.setBirthDate(birthDate);
		contact3.setCity(city);
		contact3.setCompanyId(companyId);
		contact3.setFirstName(firstname);
		contact3.setLastName(lastname);
		contact3.setPrefix(prefix);
		contact3.setState(state);
		contact3.setSuffix(suffix);
		contact3.setZip(zip);
		contact3.setEmails(createEmails(contact3));
		contacts.add(contact3);
		System.out.println("createContacts: " + contact3.toString());
		// =================================================================================
		return contacts;
	}
	
	//@Test
	public void testUserCreate() throws Exception {
		System.out.println("testUserCreate: START");
		// =================================================================================
		PositionEntity position = new PositionEntity();
		position.setId(positionId);
		// =================================================================================
		UserEntity user = new UserEntity();
		//user.setId(id);
		user.setActive(active);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setPassword(password);
		user.setUsername(username);
		user.setPosition(position);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		
		user.setEmail(email);
		user.setSecurityQuestion1(securityQuestion1);
		user.setSecurityAnswer1(securityAnswer1);
		user.setSecurityQuestion2(securityQuestion2);
		user.setSecurityAnswer2(securityAnswer2);
		
		user.setContacts(createContacts(user));
		System.out.println("testUserCreate: " + user.toString());
		// ***************************************************************
		System.out.println("testUserCreate: START: CREATE");
		user = userDao.saveUserEntity(user);
		assertNotNull(user);
		System.out.println("testUserCreate: FINISH: CREATE");
		// =================================================================================
	}
	
	@Test
	public void testUserRetrieveAll() {
		System.out.println("testUserRetrieveAll: START");
		// =================================================================================
		// =================================================================================
		// ***************************************************************
		System.out.println("testUserRetrieveAll: START: CREATE");
		List<UserEntity> users = userDao.getAllUserEntitys();
		assertNotNull(users);
		for(UserEntity user:users) {
			assertNotNull(user.getId());
			//assertNotNull(user.isActive());
			//************************************************************
//			assertEquals(user.getAddress1(),address1);
//			assertEquals(user.getAddress2(),address2);
//			assertEquals(user.getCity(),city);
//			assertEquals(user.getCompanyId(),companyId);
//			assertEquals(user.getFirstName(),firstName);
//			assertEquals(user.getId(),id);
//			assertEquals(user.getLastName(),lastName);
//			//assertEquals(user.getMiddleName(),middleName);
//			assertEquals(user.getPassword(),password);
//			assertEquals(user.getPrefix(),prefix);
//			assertEquals(user.getState(),state);
//			assertEquals(user.getSuffix(),suffix);
//			assertEquals(user.getUsername(),username);
//			assertEquals(user.getZip(),zip);
			//************************************************************
			System.out.println("testUserRetrieveAll: user=" + user.toString());
		}
		System.out.println("testUserRetrieveAll: FINISH: CREATE");
		// =================================================================================
	}
	
	@Test
	public void testUserRetrieveById() {
		System.out.println("testUserRetrieveById: START");
		// =================================================================================
		long id = 1;
		// =================================================================================
		// ***************************************************************
		System.out.println("testUserRetrieveById: START: CREATE");
		UserEntity user = userDao.getUserEntity(id);
		assertNotNull(user.getId());
		//assertNotNull(user.isActive());
		//************************************************************
		assertEquals(user.getFirstname(),"Demo");
		assertEquals(user.getId(),id);
		assertEquals(user.getLastname(),"Demo");
		assertEquals(user.getPassword(),"demo");
		assertEquals(user.getUsername(),"demo");
		
		assertEquals(email,user.getEmail());
		assertEquals(securityQuestion1,user.getSecurityQuestion1());
		assertEquals(securityAnswer1,user.getSecurityAnswer1());
		assertEquals(securityQuestion2,user.getSecurityQuestion2());
		assertEquals(securityAnswer2,user.getSecurityAnswer2());
		//************************************************************
		System.out.println("testUserRetrieve: user=" + user.toString());
		System.out.println("testUserRetrieveById: FINISH: CREATE");
		// =================================================================================
	}
	
	//@Test
	public void testUserRetrieveByExample() {
		System.out.println("testUserRetrieveByExample: START");
		// =================================================================================
		UserEntity exampleEntity = new UserEntity();
		exampleEntity.setUsername("demo");
		// =================================================================================
		// ***************************************************************
		System.out.println("testUserRetrieveByExample: START: CREATE");
		List<UserEntity> users = userDao.getUsersEntity(exampleEntity);
		UserEntity user = users.get(0);
		assertNotNull(user.getId());
		//assertNotNull(user.isActive());
		//************************************************************
		assertEquals(user.getFirstname(),"Demo");
		assertEquals(user.getId(),id);
		assertEquals(user.getLastname(),"Demo");
		assertEquals(user.getPassword(),"demo");
		assertEquals(user.getUsername(),"demo");
		//************************************************************
		System.out.println("testUserRetrieveByExample: user=" + user.toString());
		System.out.println("testUserRetrieveByExample: FINISH: CREATE");
		// =================================================================================
	}
	
		@Test
		public void testUserRetrieveByUsernamePassword() {
			System.out.println("testUserRetrieveByUsernamePassword: START");
			// =================================================================================
			int id = 1;
			String username = "demo";
			String password = "demo";
			UserEntity exampleEntity = new UserEntity();
			exampleEntity.setUsername(username);
			exampleEntity.setPassword(password);
			// =================================================================================
			// ***************************************************************
			System.out.println("testUserRetrieveByUsernamePassword: START: CREATE");
			List<UserEntity> users = userDao.getUserEntityByLogin(username, password);
			assertNotNull(users);
			assertEquals(1,users.size());
			UserEntity user = users.get(0);
			assertNotNull(user.getId());
			//************************************************************
			assertEquals(user.getFirstname(),"Demo");
			assertEquals(user.getId(),id);
			assertEquals(user.getLastname(),"Demo");
			assertEquals(user.getPassword(),"demo");
			assertEquals(user.getUsername(),"demo");
			//************************************************************
			System.out.println("testUserRetrieveByExample: user=" + user.toString());
			System.out.println("testUserRetrieveByExample: FINISH: CREATE");
			// =================================================================================
		}
	
	//@Test
	public void testUserDelete() {
		System.out.println("testUserDelete: START");
		long id = 3;
		// =================================================================================
		UserEntity user = userDao.getUserEntity(id);
		assertNotNull(user);
		System.out.println("testUserDelete: " + user.toString());
		userDao.deleteUserEntity(user);
		System.out.println("testUserDelete: user deleted");
		user = userDao.getUserEntity(id);
		assertEquals(user,null);
		// ***************************************************************
		System.out.println("testUserDelete: FINISH");
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
		//contact.setPassword(updatePassword);
		//contact.setUsername(updateUsername);
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
		//assertEquals(contact.getPassword(),updatePassword);
		//assertEquals(contact.getUsername(),updateUsername);
		assertEquals(contact.getEmails().size(),2);
		System.out.println("testContactUpdate: FINISH: CREATE");
		// =================================================================================
	}
	

}


