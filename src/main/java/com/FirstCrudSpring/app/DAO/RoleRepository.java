package com.FirstCrudSpring.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.FirstCrudSpring.app.models.RoleEntity;
import com.FirstCrudSpring.app.models.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	   @Query(value="SELECT * FROM roles WHERE role_name =?",nativeQuery = true)
       public RoleEntity findUserByRole(RoleEnum role);
}
