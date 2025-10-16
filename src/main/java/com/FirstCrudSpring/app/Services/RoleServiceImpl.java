package com.FirstCrudSpring.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FirstCrudSpring.app.DAO.RoleRepository;
import com.FirstCrudSpring.app.models.RoleEntity;
import com.FirstCrudSpring.app.models.RoleEnum;

@Service
public class RoleServiceImpl  implements RoleService{
	
	@Autowired
	RoleRepository repository;

	@Override
	public RoleEntity findById(Long id) {
		// TODO Auto-generated method stub
		return repository.getReferenceById(id);
	}

	@Override
	public RoleEntity findByRole(RoleEnum role) {
		// TODO Auto-generated method stub
		return repository.findUserByRole(role);
	}

}
