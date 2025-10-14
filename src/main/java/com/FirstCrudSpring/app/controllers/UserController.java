package com.FirstCrudSpring.app.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.FirstCrudSpring.app.Services.TaskServiceImpl;
import com.FirstCrudSpring.app.Services.UserServiceImpl;
import com.FirstCrudSpring.app.models.Tasks;
import com.FirstCrudSpring.app.models.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller()
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	@Autowired
	TaskServiceImpl taskService;


	



	@GetMapping("/")
	private String showLogin(Model model) {
		User user= new User();
		model.addAttribute("user", user);
		return "login"; 
	}

	
	@PostMapping("/")
	private String validateLogin(@Valid User user, BindingResult result,HttpSession session, Errors errors){
		
		userService.validate(user,errors);
		if(result.hasErrors())	return "login";
		
		//Si el usuario existe guardamos el mismo en la Httpsession
		User userSession=userService.findByEmail(user.getEmail());
		
	    session.setAttribute("loggedUser", userSession);

		
		return "redirect:/user";
	}
	
	/*En Spring MVC, cada request HTTP es independiente.
Cuando haces el POST al endpoint / (login), se crea un objeto User con los datos del formulario.
Ese objeto no persiste automáticamente entre peticiones, a menos que tú lo guardes manualmente 
(por ejemplo, en la sesión).

Por eso, cuando luego haces un GET a /user, el parámetro User user no contiene el mismo objeto
 validado antes — Spring crea un nuevo objeto vacío (o intenta poblarlo desde parámetros del
  request, si los hay).*/
	
	
	@GetMapping("/user")
	private String showTasks(Model model,HttpSession session) {
		
		//Cargar el ususario logado
        User user = (User) session.getAttribute("loggedUser");
		List<Tasks> tasks=taskService.findByEmail(user.getEmail());
		model.addAttribute("tasks",tasks);
		model.addAttribute("user", user);
		return "tasks";
		
		
	}


}
