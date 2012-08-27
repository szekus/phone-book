package com.opensource.phonebook.server.rpc;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensource.phonebook.client.services.UserService;
import com.opensource.phonebook.shared.dto.PositionDTO;
import com.opensource.phonebook.shared.dto.UserDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath*:applicationContext.xml" })
public class UserServiceImplTests extends TestCase
{

    private static final Log logger = LogFactory.getLog(UserServiceImplTests.class);

    @Autowired
    private UserService service;

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
    private final static String email = "tom@tomholmes.net";
    private final static int positionId = 2;
    private final static String firstname = "tom";
    private final static String lastname = "holmes";
    private final static String username = "user1";
    private final static String password = "pass1";
    private final static String securityQuestion1 = "question1";
    private final static String securityQuestion2 = "question2";
    private final static String securityAnswer1 = "answer1";
    private final static String securityAnswer2 = "answer2";

    private UserDTO createUserDto()
    {
        UserDTO userDto = new UserDTO();
        userDto.setId(id);
        userDto.setActive(true);
        userDto.setEmail(email);
        PositionDTO position = new PositionDTO();
        position.setId(positionId);
        position.setActive(true);
        userDto.setPosition(position);
        userDto.setFirstname(firstname);
        userDto.setLastname(lastname);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setSecurityQuestion1(securityQuestion1);
        userDto.setSecurityAnswer1(securityAnswer1);
        userDto.setSecurityQuestion2(securityQuestion2);
        userDto.setSecurityAnswer2(securityAnswer2);
        return userDto;
    }

    // @Test
    public void testCreateUser() throws Exception
    {
        UserDTO userDto = createUserDto();
        // ==================================================
        UserDTO newUserDto = service.add(userDto);
        assertNotNull(newUserDto);
        // ==================================================
    }

    @Test
    public void testFetchAll() throws Exception
    {
        UserDTO dummy = new UserDTO();
        dummy.setFirstname("test_description");
        // ==================================================
        List<UserDTO> users = service.fetch();
        System.out.println("users: count=" + users.size());
        assertNotNull(users);
        // ==================================================
    }

    @Test
    public void testFetchByExample() throws Exception
    {
        UserDTO dummy = new UserDTO();
        dummy.setUsername("demo");
        // ==================================================
        List<UserDTO> users = service.fetch(dummy);
        UserDTO user = users.get(0);
        System.out.println("testFetchByExample: users: id=" + user.getId());
        System.out.println("testFetchByExample: users: active=" + user.isActive());
        System.out.println("testFetchByExample: users: descr=" + user.getFirstname());
        System.out.println("testFetchByExample: users=" + user.toString());
        assertNotNull(user);
        // ==================================================
    }

    @Test
    public void testFetchById() throws Exception
    {
        long id = 1;
        // ==================================================
        UserDTO user = service.fetch(id);
        System.out.println("users: id=" + user.getId());
        System.out.println("users: active=" + user.isActive());
        System.out.println("users: descr=" + user.getFirstname());
        System.out.println("users=" + user.toString());
        assertNotNull(user);
        // ==================================================
    }

    @Test
    public void testUpdatePassword() throws Exception
    {
        long userId = 2;
        String updatedPassword = "updated";
        // ==================================================
        UserDTO userDto = new UserDTO();
        userDto.setId(userId);
        userDto.setActive(true);
        userDto.setPassword(updatedPassword);
        service.update(userDto);
        // ==================================================
        UserDTO user = service.fetch(userId);
        System.out.println("users: userId=" + user.getId());
        System.out.println("users: active=" + user.isActive());
        System.out.println("users: firstname=" + user.getFirstname());
        System.out.println("users: password=" + user.getPassword());
        System.out.println("users=" + user.toString());
        assertNotNull(user);
        // ==================================================
    }

}
