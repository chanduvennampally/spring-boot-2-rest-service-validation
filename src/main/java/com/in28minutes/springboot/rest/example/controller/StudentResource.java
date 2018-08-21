package com.in28minutes.springboot.rest.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.rest.example.model.Student;
import com.in28minutes.springboot.rest.example.service.StudentService;

@RestController
public class StudentResource {

	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public List<Student> retrieveAllStudents() {
		return studentService.findAll();
	}

	@GetMapping("/students/{id}")
	public Resource<Student> retrieveStudent(@PathVariable long id) {
		return studentService.findById(id);
	}

	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable long id) {
		studentService.deleteById(id);
	}

	@PostMapping("/students")
	public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student) {
		return studentService.createStudent(student);
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@Valid @RequestBody Student student, @PathVariable long id) {
		return studentService.updateStudent(student, id);
	}
}
