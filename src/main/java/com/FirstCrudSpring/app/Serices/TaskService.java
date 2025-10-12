package com.FirstCrudSpring.app.Serices;

import java.util.List;


import com.FirstCrudSpring.app.models.Tasks;

public interface TaskService {
	public List<Tasks> findByEmail(String email);
	public Tasks findById(int id);
	public List<Tasks> sortedByIdDes();
	public List<Tasks> sortedByDateDes();
	public List<Tasks> sortedByDateUp();
}
