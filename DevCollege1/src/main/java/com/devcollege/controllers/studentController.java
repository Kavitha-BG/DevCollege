package com.devcollege.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcollege.entities.Student;
import com.devcollege.repositories.StudentRepository;
import com.devcollege.services.StudentService;

@RestController
@RequestMapping("/api/students")
public class studentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping("/addstudent")
	public ResponseEntity<String> addStudentDetail(@RequestBody Student student) {
//		Student addstudent = this.studentService.addStudentDetail(student);
		this.studentRepository.save(student);
		return ResponseEntity.ok("Successfully Added Student details for "+ student);
//		return new ResponseEntity<>(addstudent, HttpStatus.CREATED);		
	}
	
	@PutMapping("/updatestudent")
	public ResponseEntity<Student> updateStudentDetail(@RequestBody Student student, @PathVariable String studentId) {
		return null;
		
	}
	
	@DeleteMapping("/deletestudent/{studentId}")
	public void deleteStudentDetail(@PathVariable String studentId) {
		//return null;
		
	}
	
	@GetMapping("/get/{studentId}")
	public ResponseEntity<Student> getStudentDetail(@PathVariable String studentId) {
		return null;
		
	}
	
	
}
