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

import com.opensource.phonebook.client.services.ContactPhoneService;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactPhoneDTO;
import com.opensource.phonebook.shared.dto.PhoneTypeDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath*:applicationContext.xml" })
public class ContactPhoneServiceImplTests extends TestCase
{

    private static final Log logger = LogFactory.getLog(ContactPhoneServiceImplTests.class);

    @Autowired
    private ContactPhoneService service;

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

    private final static String phone = "111-222-1234";
    private final static long phoneTypeId = 1;

    private ContactPhoneDTO createContactPhoneDto()
    {
        ContactPhoneDTO contactPhoneDto = new ContactPhoneDTO();
        contactPhoneDto.setContactId(4);
        contactPhoneDto.setPhoneId(id);
        contactPhoneDto.setEnteredDate(enteredDate);
        contactPhoneDto.setPhone(phone);

        PhoneTypeDTO phoneType = new PhoneTypeDTO();
        phoneType.setId(phoneTypeId);
        contactPhoneDto.setPhoneType(phoneType);

        return contactPhoneDto;
    }

    // @Test
    public void testCreateContactPhone() throws Exception
    {
        System.out.println("testCreateContactPhone: START");
        ContactPhoneDTO contactPhoneDto = createContactPhoneDto();
        // ==================================================
        ContactPhoneDTO newContactPhoneDto = service.add(contactPhoneDto);
        assertNotNull(newContactPhoneDto);
        // ==================================================
        System.out.println("testCreateContactPhone: FINISH");
    }

    @Test
    public void testFetchAll() throws Exception
    {
        System.out.println("testFetchAll: START");
        // ==================================================
        List<ContactPhoneDTO> contactPhones = service.fetch();
        System.out.println("contactPhones: count=" + contactPhones.size());
        assertNotNull(contactPhones);
        // ==================================================
        System.out.println("testFetchAll: FINISH");
    }

    @Test
    public void testFetchByExample() throws Exception
    {
        System.out.println("testFetchByExample: START");
        ContactPhoneDTO dummy = new ContactPhoneDTO();
        dummy.setPhone(phone);
        // ==================================================
        List<ContactPhoneDTO> contactPhones = service.fetch(dummy);
        ContactPhoneDTO contactPhone = contactPhones.get(0);
        System.out.println("testFetchByExample: contactPhone: id=" + contactPhone.getPhoneId());
        System.out.println("testFetchByExample: contactPhone: contactId=" + contactPhone.getContactId());
        System.out.println("testFetchByExample: contactPhone: phone=" + contactPhone.getPhone());
        System.out.println("testFetchByExample: contactPhone=" + contactPhone.toString());
        assertNotNull(contactPhone);
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
        List<ContactPhoneDTO> contactPhones = service.fetch(dummy);
        ContactPhoneDTO contactPhone = null;
        if (contactPhones != null)
        {
            System.out.println("testFetchByContactExample1: contacts: size=" + contactPhones.size());
            System.out.println("testFetchByContactExample1: contacts=" + contactPhones.toString());
            if (contactPhones.size() > 0)
            {
                contactPhone = contactPhones.get(0);
                System.out.println("testFetchByContactExample1: contactPhone: id=" + contactPhone.getPhoneId());
                System.out
                .println("testFetchByContactExample1: contactPhone: contactId=" + contactPhone.getContactId());
                System.out.println("testFetchByContactExample1: contactPhone: phone=" + contactPhone.getPhone());
                System.out.println("testFetchByContactExample1: contactPhone=" + contactPhone.toString());
            }
        }
        assertNotNull(contactPhones);
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
        List<ContactPhoneDTO> contactPhones = service.fetch(dummy);
        ContactPhoneDTO contactPhone = null;
        if (contactPhones != null)
        {
            System.out.println("testFetchByContactExample2: contacts: size=" + contactPhones.size());
            System.out.println("testFetchByContactExample2: contacts=" + contactPhones.toString());
            if (contactPhones.size() > 0)
            {
                contactPhone = contactPhones.get(0);
                System.out.println("testFetchByContactExample2: contactPhone: id=" + contactPhone.getPhoneId());
                System.out
                .println("testFetchByContactExample2: contactPhone: contactId=" + contactPhone.getContactId());
                System.out.println("testFetchByContactExample2: contactPhone: phone=" + contactPhone.getPhone());
                System.out.println("testFetchByContactExample2: contactPhone=" + contactPhone.toString());
            }
        }

        assertNotNull(contactPhone);
        // ==================================================
        System.out.println("testFetchByContactExample2: FINISH");
    }

    @Test
    public void testFetchById() throws Exception
    {
        System.out.println("testFetchById: START");
        long id = 4;
        // ==================================================
        ContactPhoneDTO contactPhone = service.fetch(id);
        System.out.println("testFetchByContactExample: contactPhone: id=" + contactPhone.getPhoneId());
        System.out.println("testFetchByContactExample: contactPhone: contactId=" + contactPhone.getContactId());
        System.out.println("testFetchByContactExample: contactPhone: phone=" + contactPhone.getPhone());
        System.out.println("contacts=" + contactPhone.toString());
        assertNotNull(contactPhone);
        // ==================================================
        System.out.println("testFetchById: FINISH");
    }

}
