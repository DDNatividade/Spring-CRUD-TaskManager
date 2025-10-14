package com.FirstCrudSpring.app.DAO;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.FirstCrudSpring.app.models.UserEntity;



//JpaRepository como su propio nombre nos indica hace de la clase DAO que es la clase que se encarga de
//gestionar las consultas a la base de datos.

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	

	   
	   @Query(value="SELECT * FROM users WHERE email =?",nativeQuery = true)
       public UserEntity findUserByEmail(String email);
	   @Query(value="SELECT * FROM users WHERE email =?",nativeQuery = true)
	   public Optional<UserEntity> searchUserByEmail(String email);
}
