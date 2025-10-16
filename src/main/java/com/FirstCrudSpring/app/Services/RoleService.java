package com.FirstCrudSpring.app.Services;

import com.FirstCrudSpring.app.models.RoleEntity;
import com.FirstCrudSpring.app.models.RoleEnum;

public interface RoleService {
	public RoleEntity findById(Long id);
	public RoleEntity findByRole(RoleEnum role);

}
