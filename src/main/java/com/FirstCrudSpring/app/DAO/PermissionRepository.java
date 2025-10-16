package com.FirstCrudSpring.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FirstCrudSpring.app.models.PermissionEntity;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity,Long> {
	
	
}
