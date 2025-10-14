package com.FirstCrudSpring.app;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.FirstCrudSpring.app.DAO.UserRepository;
import com.FirstCrudSpring.app.models.PermissionEntity;
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
    CommandLineRunner init(UserRepository userRepository) {

        return args -> {
            /* Create PERMISSIONS */
            PermissionEntity createPermission = PermissionEntity.builder()
                    .name("CREATE")
                    .build();

            PermissionEntity readPermission = PermissionEntity.builder()
                    .name("READ")
                    .build();

            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name("UPDATE")
                    .build();

            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name("DELETE")
                    .build();

            PermissionEntity refactorPermission = PermissionEntity.builder()
                    .name("REFACTOR")
                    .build();

            /* Create ROLES */
            RoleEntity roleAdmin = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            RoleEntity roleUser = RoleEntity.builder()
                    .roleEnum(RoleEnum.USER)
                    .permissionList(Set.of(createPermission, readPermission))
                    .build();

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
            		

           
            userRepository.saveAll(List.of(userDaniel));
        };
    }

}

