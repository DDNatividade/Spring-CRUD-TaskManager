package com.FirstCrudSpring.app.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.FirstCrudSpring.app.models.User;
import com.FirstCrudSpring.app.services.UserService;
import com.FirstCrudSpring.app.validation.UserValidator;

import jakarta.validation.Valid;


@Controller()
public class UserController {
	
	@Autowired
	private UserValidator validator;
	
	@Autowired
	private UserService service;
	
	/*Siempre usamos InitBinder + valid para validar nuestros objetos.
	 * 
	 * Solo hacemos uso de .validate() solo si quieres forzar validación fuera de ese flujo.*/
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}
	

	@ModelAttribute("signedUsers")
	public  List<User> signedUsers(){
		return service.findAll();
	}

	@GetMapping("/")
	private String showLogin(Model model) {
		User user= new User();
		model.addAttribute("user", user);
		return "login"; 
	}
	
	@PostMapping("/")
	private String validateLogin(@Valid User user, BindingResult result) {
		
		if(result.hasErrors())	return "login";
		
		return "redirect:/user";
	}


}
