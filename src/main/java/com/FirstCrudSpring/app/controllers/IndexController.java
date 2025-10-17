package com.FirstCrudSpring.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.FirstCrudSpring.app.Services.TaskServiceImpl;
import com.FirstCrudSpring.app.Services.UserServiceImpl;
import com.FirstCrudSpring.app.models.UserEntity;


@Controller
public class IndexController {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	TaskServiceImpl taskService;


	@GetMapping("/")
	private String showLogin(Model model) {
		model.addAttribute("user", new UserEntity());
		return "login"; 
	}

	

}
