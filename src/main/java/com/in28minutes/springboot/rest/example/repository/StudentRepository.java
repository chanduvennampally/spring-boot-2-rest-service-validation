package com.in28minutes.springboot.rest.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.springboot.rest.example.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
