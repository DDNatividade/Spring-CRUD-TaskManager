package com.FirstCrudSpring.app.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FirstCrudSpring.app.models.User;

public interface UserService extends JpaRepository<User, String>{
	
}
