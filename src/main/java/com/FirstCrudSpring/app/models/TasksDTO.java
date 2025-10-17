package com.FirstCrudSpring.app.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class TasksDTO {

	@NotEmpty(message = "The Name is required")
	private String name;
	
	@NotEmpty(message = "Description is required")
	@NotBlank
	@Size(min = 10, message="The description should be at least 10 characters")
	@Size(max = 2000, message = "The description can not exceed 2000 characters")
	private String description;
	
	@NotEmpty(message = "Required field")
	@NotBlank
	private String category;
	
	@Enumerated(value=EnumType.STRING)
	private PriorityEnum priority;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public PriorityEnum getPriority() {
		return priority;
	}

	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}
	
	

}
