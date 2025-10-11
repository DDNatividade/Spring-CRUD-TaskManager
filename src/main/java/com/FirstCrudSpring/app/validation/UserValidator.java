package com.FirstCrudSpring.app.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.FirstCrudSpring.app.models.User;
import com.FirstCrudSpring.app.services.UserService;

@Component
public class UserValidator implements Validator{

	@Autowired
	private UserService service;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user=(User) target;
		if(service.findById(user.getEmail()).isEmpty()) {
			errors.reject("user.invalid", "User not found");
		}
		
	}



}
