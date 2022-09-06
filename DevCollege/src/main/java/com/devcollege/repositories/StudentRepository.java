package com.devcollege.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcollege.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

	
//	List<Student> findByStudentId(String studentId);
	Student findByStudentId(String studentId);	
	
}
