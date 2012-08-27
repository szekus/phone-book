package com.opensource.phonebook.server.rpc;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.client.services.LoginService;
import com.opensource.phonebook.domain.UserEntity;
import com.opensource.phonebook.server.dao.UserDao;
import com.opensource.phonebook.shared.Mapping;
import com.opensource.phonebook.shared.dto.UserDTO;

@SuppressWarnings("serial")
@Transactional
@Service("loginService")
public class LoginServiceImpl extends BaseRPC implements LoginService
{
    private static final Log logger = LogFactory.getLog(LoginServiceImpl.class);

    @Autowired
    private UserDao userDao;

    public UserDao getUserDao()
    {
        return userDao;
    }

    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER, readOnly = true)
    public UserDTO login(String username, String password)
    {
        UserDTO user = null;
        if (username != null && password != null)
        {
            List<UserEntity> users = userDao.getUserEntityByLogin(username, password);
            if (users != null && users.size() > 0)
            {
                user = Mapping.createUser(users.get(0));
            }
        }
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER, readOnly = true)
    public UserDTO login(String userId)
    {
        UserDTO user = null;
        if (userId != null)
        {
            long id = Long.parseLong(userId);
            UserEntity userEntity = userDao.getUserEntity(id);
            if (userEntity != null)
            {
                user = Mapping.createUser(userEntity);
            }
        }
        return user;
    }
}
