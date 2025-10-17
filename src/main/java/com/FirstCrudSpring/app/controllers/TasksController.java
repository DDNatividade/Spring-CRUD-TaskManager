package com.FirstCrudSpring.app.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.FirstCrudSpring.app.Services.TaskServiceImpl;
import com.FirstCrudSpring.app.Services.UserServiceImpl;
import com.FirstCrudSpring.app.models.PriorityEnum;
import com.FirstCrudSpring.app.models.Tasks;
import com.FirstCrudSpring.app.models.TasksDTO;
import com.FirstCrudSpring.app.models.UserEntity;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TasksController {
	
	
	@Autowired
	UserServiceImpl userService;
	@Autowired
	TaskServiceImpl taskService;
	

	
	@GetMapping("/newTask")
	public String showCreatePage(Model model, @AuthenticationPrincipal User springUser) {
		TasksDTO tasksDTO = new TasksDTO();
		model.addAttribute("tasksDTO", tasksDTO);
	    model.addAttribute("priorities", PriorityEnum.values());
		System.out.println("Email actual: " + springUser.getUsername());

		return "NewTask";
	}
	
	//Binding result debe ir justo después del usuario recuerda
	@PostMapping("/newTask")
	public String addNewTask(@Valid @ModelAttribute TasksDTO tasksDTO,	BindingResult result,
			@AuthenticationPrincipal User springUser, 
			  Model model) {
		
	
		
		if(result.hasErrors())	{
	        model.addAttribute("priorities", PriorityEnum.values());
			return "NewTask";}
		
		// Creamos una variable date
		Date createdAt = new Date();
		
		String email = springUser.getUsername();
	    UserEntity user = userService.findByEmail(email);

		Tasks task = new Tasks();
		
		task.setCategory(tasksDTO.getCategory());
		task.setName(tasksDTO.getName());
		task.setDueDate(createdAt);
		task.setUser(user);
		task.setDescription(tasksDTO.getDescription());
		task.setPriority(tasksDTO.getPriority());

		// Guardamos los cambios
		taskService.saveTask(task);
		return "redirect:/user";
	}
	
	
	@GetMapping("/edit")
	public String showEditPage(Model model, @RequestParam int id, @AuthenticationPrincipal User springUse) {
		try {
			Tasks task = taskService.findById(id);
			List<PriorityEnum> priorities=List.of(PriorityEnum.values());
			
			model.addAttribute("task", task);
			model.addAttribute("priorities",priorities);
			
			TasksDTO taskDTO = new TasksDTO();
			taskDTO.setName(task.getName());
			taskDTO.setPriority(task.getPriority());
			taskDTO.setCategory(task.getCategory());
			taskDTO.setDescription(task.getDescription());

			
			model.addAttribute("tasksDTO", taskDTO);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return "EditTask";
		
	}
	
	@PostMapping("/edit")
	public String editTask(@Valid @ModelAttribute TasksDTO tasksDTO,
			BindingResult result,
			@AuthenticationPrincipal User springUser, 
			@RequestParam int id,
			Model model) {
		
		
		if(result.hasErrors()) {
			List<PriorityEnum> priorities=List.of(PriorityEnum.values());
			model.addAttribute("priorities",priorities);

			return "EditTask";
		}
		
	    Tasks task = taskService.findById(id);
	    if (task == null) {
	        throw new RuntimeException("Task not found with id: " + id);
	    }
		
		
		task.setName(tasksDTO.getName());
		task.setCategory(tasksDTO.getCategory());
		task.setDescription(tasksDTO.getDescription());
		task.setPriority(tasksDTO.getPriority());
		System.out.println("El elemento es: " + task.getId());
		
		taskService.saveTask(task);

		return "redirect:/user";
	}
		
		
		@GetMapping("/delete")
		public String deleteTask( @RequestParam int id, @AuthenticationPrincipal User springUse) {
			taskService.deleteTask(taskService.findById(id));
			
			return "redirect:/user";
			
		}
			
		
	
		
		
	}
	
	
	

