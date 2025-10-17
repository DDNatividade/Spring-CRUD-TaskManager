package com.FirstCrudSpring.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.FirstCrudSpring.app.DAO.TaskRepository;
import com.FirstCrudSpring.app.models.Tasks;

import jakarta.transaction.Transactional;

@Service
@Transactional
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
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

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

	@Override
	public void saveTask(Tasks task) {
		repository.save(task);
		
	}

	@Override
	public void deleteTask(Tasks task) {
		repository.delete(task);
	}
	
	

}
