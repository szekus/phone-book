package com.opensource.phonebook.server.dao;

import java.util.List;

import com.opensource.phonebook.domain.UserEntity;

public interface UserDao {
	
	public UserEntity saveUserEntity(UserEntity user);
	
	public UserEntity createUserEntity(UserEntity user);
	
	public UserEntity updateUserEntity(UserEntity user);
	
	public void deleteUserEntity(Long userId);
	public void deleteUserEntity(UserEntity user);
	
	public List<UserEntity> getAllUserEntitys();
	
	// Retrieve
	public UserEntity getUserEntity(long id);
	public List<UserEntity> getUsersEntity(UserEntity exampleEntity);
	
	public List<UserEntity> getUserEntityByLogin(String username, String password);
	
}



