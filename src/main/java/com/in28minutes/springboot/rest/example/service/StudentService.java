package com.in28minutes.springboot.rest.example.service;

import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import com.in28minutes.springboot.rest.example.model.Student;

public interface StudentService {

	List<Student> findAll();
	
	Resource<Student>  findById(Long id);
	
	void deleteById(Long id);
	
	ResponseEntity<Object> updateStudent(Student student, Long id);
	
	ResponseEntity<Object> createStudent(Student student);	
}
