package com.FirstCrudSpring.app.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import com.FirstCrudSpring.app.Services.TaskServiceImpl;
import com.FirstCrudSpring.app.Services.UserServiceImpl;
import com.FirstCrudSpring.app.models.Tasks;
import com.FirstCrudSpring.app.models.UserEntity;

import jakarta.servlet.http.HttpSession;


@Controller()
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	@Autowired
	TaskServiceImpl taskService;

	
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
        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
		List<Tasks> tasks=taskService.findByEmail(user.getEmail());
		model.addAttribute("tasks",tasks);
		model.addAttribute("user", user);
		return "tasks";
		
		
	}


}
