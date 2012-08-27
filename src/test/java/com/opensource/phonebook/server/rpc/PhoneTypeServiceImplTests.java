package com.opensource.phonebook.server.rpc;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensource.phonebook.client.services.PhoneTypeService;
import com.opensource.phonebook.shared.dto.PhoneTypeDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath*:applicationContext.xml" })
public class PhoneTypeServiceImplTests extends TestCase
{

    private static final Log logger = LogFactory.getLog(PhoneTypeServiceImplTests.class);

    private static ApplicationContext context = null;

    @Autowired
    private PhoneTypeService service;

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

    @Test
    public void testFetchAll() throws Exception
    {
        PhoneTypeDTO dummy = new PhoneTypeDTO();
        dummy.setDescription("test_description");
        // ==================================================
        List<PhoneTypeDTO> phoneTypes = service.fetch();
        System.out.println("phoneTypes: count=" + phoneTypes.size());
        System.out.println("phoneTypes: count=" + phoneTypes.toString());
        assertNotNull(phoneTypes);
        // ==================================================
    }

    @Test
    public void testFetchByExample() throws Exception
    {
        PhoneTypeDTO dummy = new PhoneTypeDTO();
        dummy.setId(2);
        // ==================================================
        List<PhoneTypeDTO> phoneTypes = service.fetch(dummy);
        PhoneTypeDTO phoneType = phoneTypes.get(0);
        System.out.println("phoneTypes: id=" + phoneType.getId());
        System.out.println("phoneTypes: active=" + phoneType.isActive());
        System.out.println("phoneTypes: descr=" + phoneType.getDescription());
        System.out.println("phoneTypes=" + phoneType.toString());
        assertNotNull(phoneType);
        // ==================================================
    }

    @Test
    public void testFetchById() throws Exception
    {
        long id = 3;
        // ==================================================
        PhoneTypeDTO phoneType = service.fetch(id);
        System.out.println("phoneTypes: id=" + phoneType.getId());
        System.out.println("phoneTypes: active=" + phoneType.isActive());
        System.out.println("phoneTypes: descr=" + phoneType.getDescription());
        System.out.println("phoneTypes=" + phoneType.toString());
        assertNotNull(phoneType);
        // ==================================================
    }

}
