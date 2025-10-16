package com.FirstCrudSpring.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FirstCrudSpring.app.DAO.PermissionRepository;
import com.FirstCrudSpring.app.models.PermissionEntity;

@Service
public class PermissionServiceImpl implements PersmissionService {

	@Autowired
	PermissionRepository repository;
	
	@Override
	public PermissionEntity findPbyID(long id) {
		// TODO Auto-generated method stub
		return repository.getReferenceById(id);
	}

}
