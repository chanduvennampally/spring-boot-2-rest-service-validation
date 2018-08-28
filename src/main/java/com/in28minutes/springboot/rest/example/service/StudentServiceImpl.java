package com.in28minutes.springboot.rest.example.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.springboot.rest.example.exception.StudentNotFoundException;
import com.in28minutes.springboot.rest.example.model.Student;
import com.in28minutes.springboot.rest.example.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Resource<Student> findById(Long id) {
		Optional<Student> student = studentRepository.findById(id);
		if (!student.isPresent())
			throw new StudentNotFoundException("id-" + id);
		Resource<Student> resource = new Resource<Student>(student.get());
		return resource;

	}

	@Override
	public void deleteById(Long id) {
//	throw new OutOfMemoryError();
		studentRepository.deleteById(id);
	}

	@Override
	public ResponseEntity<Object> updateStudent(Student student, Long id) {
		Optional<Student> studentOptional = studentRepository.findById(id);
		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();
		student.setId(id);
		studentRepository.save(student);
		
        return ResponseEntity.accepted().body(student);
		
//		return ResponseEntity.noContent().build();
		
	}

	@Override
	public ResponseEntity<Object> createStudent(Student student) {

		Student savedStudent = studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

}
