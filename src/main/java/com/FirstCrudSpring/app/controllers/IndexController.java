package com.FirstCrudSpring.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.FirstCrudSpring.app.Services.TaskServiceImpl;
import com.FirstCrudSpring.app.Services.UserServiceImpl;
import com.FirstCrudSpring.app.models.UserEntity;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller()
public class IndexController {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	TaskServiceImpl taskService;


	@GetMapping("/")
	private String showLogin(Model model) {
		UserEntity user= new UserEntity();
		model.addAttribute("user", new UserEntity());
		return "login"; 
	}

	

}
