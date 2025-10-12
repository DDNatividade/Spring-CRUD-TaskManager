package com.FirstCrudSpring.app.Serices;

import java.util.List;

import com.FirstCrudSpring.app.models.User;

public interface UserService  {

	public List <User> listAll();
	
	public User findByEmail(String mail);

	
	
	
}
