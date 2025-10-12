package com.FirstCrudSpring.app.Serices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.FirstCrudSpring.app.DAO.TaskRepository;
import com.FirstCrudSpring.app.models.Tasks;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository repository;

	@Override
	public List<Tasks> findByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findTasksByEmail(email);
	}

	@Override
	public Tasks findById(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public List<Tasks> sortedByIdDes() {
		// TODO Auto-generated method stub
		return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	@Override
	public List<Tasks> sortedByDateDes() {
		// TODO Auto-generated method stub
		return repository.findAll(Sort.by(Sort.Direction.DESC, "dueDate"));
	}

	@Override
	public List<Tasks> sortedByDateUp() {
		// TODO Auto-generated method stub
		return repository.findAll(Sort.by(Sort.Direction.ASC, "dueDate"));
	}
	
	

}
