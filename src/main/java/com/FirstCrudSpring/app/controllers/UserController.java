package com.FirstCrudSpring.app.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.FirstCrudSpring.app.Services.TaskServiceImpl;
import com.FirstCrudSpring.app.Services.UserServiceImpl;
import com.FirstCrudSpring.app.models.Tasks;
import com.FirstCrudSpring.app.models.UserEntity;
import org.springframework.security.core.userdetails.User;

@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	@Autowired
	TaskServiceImpl taskService;

	
	@GetMapping("/user")
	private String showTasks(Model model, @AuthenticationPrincipal User springUser) {

	    String email = springUser.getUsername();
	    UserEntity user = userService.findByEmail(email);
	    List<Tasks> tasks = taskService.findByEmail(email);

	    model.addAttribute("tasks", tasks);
	    model.addAttribute("user", user);
	    return "tasks";
	}	


}
