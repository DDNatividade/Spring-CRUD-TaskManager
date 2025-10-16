package com.FirstCrudSpring.app.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.FirstCrudSpring.app.DAO.UserRepository;
import com.FirstCrudSpring.app.models.PermissionEntity;
import com.FirstCrudSpring.app.models.UserEntity;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private  UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Buscamos al usuario por los datos. Si no lo encontrase envía una excepción
		UserEntity user =repository.searchUserByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User " + username + "not found"));
		
		//Lista donde Spring security guarda temporalente las autoridades
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        //Anadimos a la lista un nuevo usuario de simple granted authority
        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(user.getRol().getRoleEnum().name())));

        //A ese rol le hemos de añadir los permisos que tiene
        Set<PermissionEntity> permissions = user.getRol().getPermissionList();
        for (PermissionEntity permission : permissions) {
        	authorityList.add(new SimpleGrantedAuthority(permission.getName()));
        }
     

        //Nuevo objeto que spring security usará para gestionar permisos. Se elimina al cerrar sesión
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                authorityList);
		

	}



}
