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

import com.opensource.phonebook.client.services.ContactService;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.UserDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath*:applicationContext.xml" })
public class ContactServiceImplTests extends TestCase
{

    private static final Log logger = LogFactory.getLog(ContactServiceImplTests.class);

    @Autowired
    private ContactService service;

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

    private ContactDTO createContactDto()
    {
        ContactDTO contactDto = new ContactDTO();
        contactDto.setUserId(2);
        contactDto.setId(id);

        contactDto.setPrefix(prefix);
        contactDto.setFirstName(firstname);
        contactDto.setMiddleName(middlename);
        contactDto.setLastName(lastname);
        contactDto.setPrefix(suffix);

        contactDto.setAddress1(address1);
        contactDto.setAddress2(address2);
        contactDto.setCity(city);
        contactDto.setState(state);
        contactDto.setZip(zip);

        contactDto.setEnteredBy(enteredBy);
        contactDto.setEnteredDate(enteredDate);
        contactDto.setEditedBy(editedBy);
        contactDto.setEditedDate(editedDate);

        // contactDto.setCompanyId(companyId);
        contactDto.setBirthDate(birthDate);

        return contactDto;
    }

    // @Test
    public void testCreateContact() throws Exception
    {
        ContactDTO contactDto = createContactDto();
        // ==================================================
        ContactDTO newContactDto = service.add(contactDto);
        assertNotNull(newContactDto);
        // ==================================================
    }

    @Test
    public void testFetchAll() throws Exception
    {
        ContactDTO dummy = new ContactDTO();
        dummy.setFirstName("test_description");
        // ==================================================
        List<ContactDTO> contacts = service.fetch();
        System.out.println("contacts: count=" + contacts.size());
        assertNotNull(contacts);
        // ==================================================
    }

    @Test
    public void testFetchByExample() throws Exception
    {
        ContactDTO dummy = new ContactDTO();
        dummy.setCity(city);
        // ==================================================
        List<ContactDTO> contacts = service.fetch(dummy);
        ContactDTO contact = contacts.get(0);
        System.out.println("testFetchByExample: contacts: id=" + contact.getId());
        System.out.println("testFetchByExample: contacts: firstname=" + contact.getFirstName());
        System.out.println("testFetchByExample: contacts: lastname=" + contact.getLastName());
        System.out.println("testFetchByExample: contacts=" + contact.toString());
        assertNotNull(contact);
        // ==================================================
    }

    @Test
    public void testFetchByUserExample() throws Exception
    {
        UserDTO dummy = new UserDTO();
        dummy.setId(2);
        // ==================================================
        List<ContactDTO> contacts = service.fetch(dummy);
        ContactDTO contact = null;
        if (contacts != null)
        {
            System.out.println("testFetchByUserExample: contacts: size=" + contacts.size());
            System.out.println("testFetchByUserExample: contacts=" + contacts.toString());
            contact = contacts.get(0);
            System.out.println("testFetchByUserExample: contacts: id=" + contact.getId());
            System.out.println("testFetchByUserExample: contacts: firstname=" + contact.getFirstName());
            System.out.println("testFetchByUserExample: contacts: lastname=" + contact.getLastName());
            System.out.println("testFetchByUserExample: contacts=" + contact.toString());
        }
        assertNotNull(contact);
        // ==================================================
    }

    @Test
    public void testFetchById() throws Exception
    {
        long id = 2;
        // ==================================================
        ContactDTO contact = service.fetch(id);
        System.out.println("contacts: id=" + contact.getId());
        System.out.println("contacts: firstname=" + contact.getFirstName());
        System.out.println("contacts: lastname=" + contact.getLastName());
        System.out.println("contacts=" + contact.toString());
        assertNotNull(contact);
        // ==================================================
    }

}
