package com.devcollege.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcollege.payloads.StudentDto;
import com.devcollege.services.StudentService;

@RestController
@RequestMapping("/api/students")
public class studentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/addstudent")
	public ResponseEntity<StudentDto> addStudentDetail(@RequestBody StudentDto studentDto) {
		StudentDto addstudentDto = this.studentService.addStudentDetail(studentDto);
		
		return new ResponseEntity<>(addstudentDto, HttpStatus.CREATED);		
	}
	
	@PutMapping("/updatestudent")
	public ResponseEntity<StudentDto> updateStudentDetail(@RequestBody StudentDto studentDto, @PathVariable String studentId) {
		return null;
		
	}
	
	@DeleteMapping("/deletestudent/{studentId}")
	public void deleteStudentDetail(@PathVariable String studentId) {
		//return null;
		
	}
	
	@GetMapping("/get/{studentId}")
	public ResponseEntity<StudentDto> getStudentDetail(@PathVariable String studentId) {
		return null;
		
	}
	
	
}
