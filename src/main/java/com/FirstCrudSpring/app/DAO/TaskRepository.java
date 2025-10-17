package com.FirstCrudSpring.app.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.FirstCrudSpring.app.models.Tasks;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Integer>{
	@Query(value="SELECT * FROM tasks WHERE email =?",nativeQuery = true)
	   List<Tasks> findTasksByEmail(String Email);
	
	
	
}
