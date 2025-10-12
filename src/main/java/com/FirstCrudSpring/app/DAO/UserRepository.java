package com.FirstCrudSpring.app.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.FirstCrudSpring.app.models.User;



//JpaRepository como su propio nombre nos indica hace de la clase DAO que es la clase que se encarga de
//gestionar las consultas a la base de datos.

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	

	   
	   @Query(value="SELECT * FROM users WHERE email =?",nativeQuery = true)
       public User findUserByEmail(String email);
	   
}
