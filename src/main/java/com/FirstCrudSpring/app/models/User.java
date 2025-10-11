package com.FirstCrudSpring.app.models;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@NotEmpty
	private String email;
	
	
	private String name;
	
	
	private String surname;
	
	@NotEmpty
	private String password;
	
	@OneToMany(mappedBy ="user",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Tasks> tasks;
	
	
	public List<Tasks> getTasks() {
		return tasks;
	}
	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
