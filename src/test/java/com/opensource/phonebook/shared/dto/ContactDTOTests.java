package com.opensource.phonebook.shared.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactDTOTests extends TestCase
{

    final Logger logger = LoggerFactory.getLogger(ContactDTOTests.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");

    protected void setUp() throws Exception
    {
        System.out.println("setup: Loading application context");
        System.out.println("setup: Done loading application context");
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        System.out.println("tearDown: START");
        System.out.println("tearDown: FINISH");
    }

    @Test
    public void testContactCreate() throws Exception
    {
        System.out.println("testContactCreate: START");
        // =================================================================================
        int id = 0;
        String username = "user_name";
        String password = "password";
        boolean active = true;
        boolean admin = true;
        String dob = "11/03/1966";
        Date birthDate = sdf.parse(dob);
        // =================================================================================
        System.out.println("testContactCreate: START: CREATE");
        ContactDTO contact = new ContactDTO();
        assertNotNull(contact);
        // ***************************************************************
        contact.setId(id);
        contact.setBirthDate(birthDate);
        System.out.println("testContactCreate: contact=" + contact.toString());
        // ***************************************************************
        assertNotNull(contact.getId());
        assertNotNull(contact.getBirthDate());
        assertNotNull(contact.toString());
        // ***************************************************************
        assertEquals(contact.getId(), id);
        assertEquals(contact.getBirthDate(), birthDate);
        assertEquals(sdf.format(contact.getBirthDate()), dob);
        // =================================================================================
        System.out.println("testContactCreate: FINISH: CREATE");
    }

}
