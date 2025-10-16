package com.FirstCrudSpring.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.FirstCrudSpring.app.DAO.PermissionRepository;
import com.FirstCrudSpring.app.Services.RoleService;
import com.FirstCrudSpring.app.Services.UserService;
import com.FirstCrudSpring.app.models.RoleEntity;
import com.FirstCrudSpring.app.models.RoleEnum;
import com.FirstCrudSpring.app.models.UserEntity;


@SpringBootApplication
public class Application {
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	
	}

	
	@Bean
    CommandLineRunner init(UserService userService, PermissionRepository permissionRepo, RoleService roleService) {

        return args -> {

        	RoleEntity roleAdmin = roleService.findById((long) 1);


            /* CREATE USERS */     
            UserEntity userDaniel = UserEntity.builder()
            		.name("Daniel")
            		.surname("de Natividade")
            		.password(passwordEncoder.encode("LaKKMovil"))
            		.email("danielfeliciano1597@gmail.com")
            		.rol(roleAdmin)
            		.isEnabled(true)
            		.tasks(null)
            		.build();
            		
//Añadimos los datos creados

            userService.saveUser(List.of(userDaniel));
        };
    }

}

