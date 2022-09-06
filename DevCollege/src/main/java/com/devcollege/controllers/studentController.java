package com.devcollege.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.StudentNotFoundException;
import com.devcollege.services.StudentService;


@RestController
@Validated
@RequestMapping("/api/students")
public class studentController {
	
	@Autowired
	private StudentService studentService;
	

	
	@PostMapping("/addstudent")
	public ResponseEntity<String> addStudentDetail(@Valid @RequestBody Student student) {
		
		Student savedStudent = studentService.addStudentDetail(student);
		return ResponseEntity.ok("Successfully Added Student details for "+ savedStudent);
	}
	
	@PutMapping("/updatestudent")
	public ResponseEntity<Student> updateStudentDetail(@Valid @RequestBody Student student, @PathVariable String studentId, int ResponseEntity) {
//		Student updatedStudent = studentService.updateStudentDetail(student, studentId);
//		if (studentRepository.findById(studentId).isPresent()) {
//			throw new NoSuchElementFoundException();
//		}
//		student.setStudentId(studentId);
//		studentRepository.save(student);
		
		
//		Student updatedStudent = studentService.updateStudentDetail(student,studentId);
		return new ResponseEntity<Student>(HttpStatus.CREATED);

						
		
	}
	
	@DeleteMapping("/deletestudent/{studentId}")
	public ResponseEntity<String> deleteStudentDetail(@Valid @PathVariable String studentId) {
		
		studentService.deleteStudentDetail(studentId);
		
//		return ResponseEntity.ok("Successfully Deleted Student details for "+ studentId);

		
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Student>> getAllStudentDetail() throws StudentNotFoundException {
		
		List<Student> studentList = studentService.getAllStudentDetail();
		return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
		
	}
	
	@GetMapping("/get")
	public ResponseEntity<Student> getStudentDetail(@Valid @RequestParam(value="studentId", required=false) String studentId) {

		
		Student retrieveStudent = studentService.getStudentDetail(studentId);	
		return new ResponseEntity<Student>(retrieveStudent, HttpStatus.OK);
		
	}
		
}


//@RequestParam(value="studentId", required=false)
















