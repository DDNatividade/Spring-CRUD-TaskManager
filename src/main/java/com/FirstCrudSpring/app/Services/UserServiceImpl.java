package com.FirstCrudSpring.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.FirstCrudSpring.app.DAO.UserRepository;
import com.FirstCrudSpring.app.models.User;

@Service
public class UserServiceImpl implements UserService{


	
	@Autowired
	UserRepository repository;
	
	
	public void validate(Object target, Errors errors) {
		User user=(User) target;
		if(repository.findById(user.getEmail()).isEmpty()) {
			errors.reject("err", "Usuario no localizado");
		}
		
	}
	
	@Override
	public List<User> listAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findUserByEmail(email);
	}



}
