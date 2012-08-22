package com.opensource.phonebook.server.rpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.phonebook.client.services.UserService;
import com.opensource.phonebook.domain.PositionEntity;
import com.opensource.phonebook.domain.UserEntity;
import com.opensource.phonebook.server.dao.UserDao;
import com.opensource.phonebook.shared.Mapping;
import com.opensource.phonebook.shared.dto.UserDTO;

@SuppressWarnings("serial")
@Transactional
@Service("userService")
public class UserServiceImpl extends BaseRPC implements UserService {

    @Autowired
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Transactional
	public UserDTO add(UserDTO userDto)
	{
	    UserEntity newUser = new UserEntity();
	    newUser.setId(userDto.getId());
	    newUser.setActive(userDto.isActive());
	    newUser.setEmail(userDto.getEmail());
	    newUser.setFirstname(userDto.getFirstname());
	    newUser.setLastname(userDto.getLastname());
	    newUser.setUsername(userDto.getUsername());
	    newUser.setPassword(userDto.getPassword());
	    //***************************************************
	    newUser.setSecurityQuestion1(userDto.getSecurityQuestion1());
	    newUser.setSecurityAnswer1(userDto.getSecurityAnswer1());
	    newUser.setSecurityQuestion2(userDto.getSecurityQuestion2());
	    newUser.setSecurityAnswer2(userDto.getSecurityAnswer2());
	    //***************************************************
	    PositionEntity positionEntity = new PositionEntity();
	    positionEntity.setId(userDto.getPosition().getId());
	    newUser.setPosition(positionEntity);
	    //***************************************************
		UserEntity userEntity = userDao.createUserEntity(newUser);
		UserDTO newUserDto = Mapping.createUser(userEntity);
		return newUserDto;
	}

	@Transactional
	public void remove(UserDTO record) {
		// TODO Auto-generated method stub
	}

	@Transactional
	public void update(UserDTO record) {
		// TODO Auto-generated method stub
	}

	@Transactional
	public List<UserDTO> fetch(UserDTO exampleEntity) {
		List<UserDTO> userList = null;
		if(exampleEntity != null)
		{
			UserEntity exampleUserEntity = new UserEntity();
			exampleUserEntity.setActive(exampleEntity.isActive());
			exampleUserEntity.setEmail(exampleEntity.getEmail());
			exampleUserEntity.setId(exampleEntity.getId());
			exampleUserEntity.setFirstname(exampleEntity.getFirstname());
			exampleUserEntity.setLastname(exampleEntity.getLastname());
			exampleUserEntity.setPassword(exampleEntity.getPassword());
			exampleUserEntity.setSecurityAnswer1(exampleEntity.getSecurityAnswer1());
			exampleUserEntity.setSecurityAnswer2(exampleEntity.getSecurityAnswer2());
			exampleUserEntity.setSecurityQuestion1(exampleEntity.getSecurityQuestion1());
			exampleUserEntity.setSecurityQuestion2(exampleEntity.getSecurityQuestion2());
			
			List<UserEntity> userEntityList = userDao.getUsersEntity(exampleUserEntity);
			if(userEntityList != null)
			{
				userList = new ArrayList<UserDTO>();
				for(UserEntity userEntity : userEntityList)
				{
					UserDTO userDTO = Mapping.createUser(userEntity);
					if(userDTO != null)
					{
						userList.add(userDTO);
					}
				}
			}
		}
		return userList;
	}

	@Transactional
	public List<UserDTO> fetch() {
		List<UserDTO> userList = null;
		List<UserEntity> userEntityList = userDao.getAllUserEntitys();
		if(userEntityList != null)
		{
			userList = new ArrayList<UserDTO>();
			for(UserEntity userEntity : userEntityList)
			{
				UserDTO userDTO = Mapping.createUser(userEntity);
				if(userDTO != null)
				{
					userList.add(userDTO);
				}
			}
		}
		return userList;
	}

	@Transactional
	public UserDTO fetch(long id) {
		UserEntity userEntity = userDao.getUserEntity(id);
		return Mapping.createUser(userEntity);
	}
		
}

