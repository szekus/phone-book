package com.opensource.phonebook.server.rpc;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensource.phonebook.client.services.ContactEmailService;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactEmailDTO;
import com.opensource.phonebook.shared.dto.EmailTypeDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath*:applicationContext.xml" })
public class ContactEmailServiceImplTests extends TestCase
{

    private static final Log logger = LogFactory.getLog(ContactEmailServiceImplTests.class);

    @Autowired
    private ContactEmailService service;

    @org.junit.Before
    public void setUp() throws Exception
    {
        System.out.println("setUp: service: " + service);
    }

    @org.junit.After
    public void tearDown()
    {
        service = null;
        System.out.println("tearDown: context set null.");
    }

    private final static int id = 0;
    private final static String prefix = "Mr.";
    private final static String firstname = "Thomas";
    private final static String middlename = "Joseph";
    private final static String lastname = "Holmes";
    private final static String suffix = "Jr.";

    private final static String address1 = "42 Wheeler Circle";
    private final static String address2 = "Apartment 38";
    private final static String city = "Stoughton";
    private final static String state = "MA";
    private final static String zip = "02072";

    private final static long enteredBy = 1;
    private final static Date enteredDate = new Date();
    private final static long editedBy = 1;
    private final static Date editedDate = new Date();

    private final static Date birthDate = new Date();
    private final static boolean admin = false;

    private final static String email = "111-222-1234";
    private final static long emailTypeId = 1;

    private ContactEmailDTO createContactEmailDto()
    {
        ContactEmailDTO contactEmailDto = new ContactEmailDTO();
        contactEmailDto.setContactId(4);
        contactEmailDto.setEmailId(id);
        contactEmailDto.setEnteredDate(enteredDate);
        contactEmailDto.setEmail(email);

        EmailTypeDTO emailType = new EmailTypeDTO();
        emailType.setId(emailTypeId);
        contactEmailDto.setEmailType(emailType);

        return contactEmailDto;
    }

    // @Test
    public void testCreateContactEmail() throws Exception
    {
        System.out.println("testCreateContactEmail: START");
        ContactEmailDTO contactEmailDto = createContactEmailDto();
        // ==================================================
        ContactEmailDTO newContactEmailDto = service.add(contactEmailDto);
        assertNotNull(newContactEmailDto);
        // ==================================================
        System.out.println("testCreateContactEmail: FINISH");
    }

    @Test
    public void testFetchAll() throws Exception
    {
        System.out.println("testFetchAll: START");
        // ==================================================
        List<ContactEmailDTO> contactEmails = service.fetch();
        System.out.println("contactEmails: count=" + contactEmails.size());
        assertNotNull(contactEmails);
        // ==================================================
        System.out.println("testFetchAll: FINISH");
    }

    @Test
    public void testFetchByExample() throws Exception
    {
        System.out.println("testFetchByExample: START");
        ContactEmailDTO dummy = new ContactEmailDTO();
        dummy.setEmail(email);
        // ==================================================
        List<ContactEmailDTO> contactEmails = service.fetch(dummy);
        ContactEmailDTO contactEmail = contactEmails.get(0);
        System.out.println("testFetchByExample: contactEmail: id=" + contactEmail.getEmailId());
        System.out.println("testFetchByExample: contactEmail: contactId=" + contactEmail.getContactId());
        System.out.println("testFetchByExample: contactEmail: email=" + contactEmail.getEmail());
        System.out.println("testFetchByExample: contactEmail=" + contactEmail.toString());
        assertNotNull(contactEmail);
        // ==================================================
        System.out.println("testFetchByExample: FINISH");
    }

    @Test
    public void testFetchByContactExample1() throws Exception
    {
        System.out.println("testFetchByContactExample1: START");
        ContactDTO dummy = new ContactDTO();
        dummy.setId(0);
        // ==================================================
        List<ContactEmailDTO> contactEmails = service.fetch(dummy);
        ContactEmailDTO contactEmail = null;
        if (contactEmails != null)
        {
            System.out.println("testFetchByContactExample1: contacts: size=" + contactEmails.size());
            System.out.println("testFetchByContactExample1: contacts=" + contactEmails.toString());
            if (contactEmails.size() > 0)
            {
                contactEmail = contactEmails.get(0);
                System.out.println("testFetchByContactExample1: contactEmail: id=" + contactEmail.getEmailId());
                System.out
                .println("testFetchByContactExample1: contactEmail: contactId=" + contactEmail.getContactId());
                System.out.println("testFetchByContactExample1: contactEmail: email=" + contactEmail.getEmail());
                System.out.println("testFetchByContactExample1: contactEmail=" + contactEmail.toString());
            }
        }
        assertNotNull(contactEmails);
        // ==================================================
        System.out.println("testFetchByContactExample: FINISH");
    }

    @Test
    public void testFetchByContactExample2() throws Exception
    {
        System.out.println("testFetchByContactExample2: START");
        ContactDTO dummy = new ContactDTO();
        dummy.setId(4);
        // ==================================================
        List<ContactEmailDTO> contactEmails = service.fetch(dummy);
        ContactEmailDTO contactEmail = null;
        if (contactEmails != null)
        {
            System.out.println("testFetchByContactExample2: contacts: size=" + contactEmails.size());
            System.out.println("testFetchByContactExample2: contacts=" + contactEmails.toString());
            if (contactEmails.size() > 0)
            {
                contactEmail = contactEmails.get(0);
                System.out.println("testFetchByContactExample2: contactEmail: id=" + contactEmail.getEmailId());
                System.out
                .println("testFetchByContactExample2: contactEmail: contactId=" + contactEmail.getContactId());
                System.out.println("testFetchByContactExample2: contactEmail: email=" + contactEmail.getEmail());
                System.out.println("testFetchByContactExample2: contactEmail=" + contactEmail.toString());
            }
        }

        assertNotNull(contactEmail);
        // ==================================================
        System.out.println("testFetchByContactExample2: FINISH");
    }

    @Test
    public void testFetchById() throws Exception
    {
        System.out.println("testFetchById: START");
        long id = 4;
        // ==================================================
        ContactEmailDTO contactEmail = service.fetch(id);
        System.out.println("testFetchByContactExample: contactEmail: id=" + contactEmail.getEmailId());
        System.out.println("testFetchByContactExample: contactEmail: contactId=" + contactEmail.getContactId());
        System.out.println("testFetchByContactExample: contactEmail: email=" + contactEmail.getEmail());
        System.out.println("contacts=" + contactEmail.toString());
        assertNotNull(contactEmail);
        // ==================================================
        System.out.println("testFetchById: FINISH");
    }

}
