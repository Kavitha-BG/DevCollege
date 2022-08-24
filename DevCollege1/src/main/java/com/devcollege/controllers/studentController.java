package com.devcollege.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcollege.entities.Student;
import com.devcollege.payloads.StudentDto;
import com.devcollege.services.StudentService;

@RestController
public class studentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/addstudent")
	public void addStudentDetail(@RequestBody Student student) {
		studentService.addStudentDetail(student);		
	}
	
	@PutMapping("/updatestudent")
	public StudentDto updateStudentDetail(@RequestBody StudentDto student, @PathVariable String studentId) {
		return studentService.updateStudentDetail(student, studentId);
	}
	
	@DeleteMapping("/deletestudent/{studentId}")
	public void deleteStudentDetail(@PathVariable String studentId) {
		studentService.deleteStudentDetail(studentId);
	}
	
	@RequestMapping("/get/{studentId}")
	public void getStudentDetail(@PathVariable String studentId) {
		studentService.getStudentDetail(studentId);
	}
	
	
}
