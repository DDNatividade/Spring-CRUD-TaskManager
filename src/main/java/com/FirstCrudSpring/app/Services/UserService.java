package com.FirstCrudSpring.app.Services;

import java.util.List;


import com.FirstCrudSpring.app.models.UserEntity;


public interface UserService  {

	public List <UserEntity> listAll();
	
	public UserEntity findByEmail(String mail);
	
	public void saveUser(List<UserEntity> list);

	
	
	
}
