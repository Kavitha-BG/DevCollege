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
	public ResponseEntity<String> addStudent(@Valid @RequestBody Student student) {
		Student savedStudent = studentService.addStudent(student);
		return ResponseEntity.ok("Successfully Added Student details for "+ student.getStudentId());
	}
	
	@PutMapping("/updatestudent")
	public ResponseEntity<String> updateStudentById(@Valid @RequestBody Student student, @PathVariable String studentId) {
//		Student updatedStudent = studentService.updateStudentDetail(student, studentId);
//		if (studentRepository.findById(studentId).isPresent()) {
//			throw new NoSuchElementFoundException();
//		}
//		student.setStudentId(studentId);
//		studentRepository.save(student);
//		Student updatedStudent = studentService.updateStudentDetail(student,studentId);
		Student savedStudent = studentService.addStudent(student);
		return ResponseEntity.ok("Successfully Updated Student details for "+ savedStudent);
//		return new ResponseEntity<Student>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletestudent/{studentId}")
	public ResponseEntity<String> deleteStudent(@Valid @PathVariable String studentId) {
		studentService.deleteStudent(studentId);
		return ResponseEntity.ok("Successfully Deleted Student details for "+ studentId);
//		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/get")
	public ResponseEntity<Student> getStudentById(@Valid @RequestParam(value="studentId", required=false) String studentId) {
		Student retrieveStudent = studentService.getStudentById(studentId);
		return new ResponseEntity<Student>(retrieveStudent, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Student>> getAllStudent() throws StudentNotFoundException {
		List<Student> studentList = studentService.getAllStudent();
		return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
	}

//	@PostMapping("studentwallet/{studentId}")
//	public Student addWalletAmount(Float amount){
//
//	}
//
//	@PutMapping("studentwallet/{studentId}")
//	public Student getWalletAmount(Float walletAmount){
//
//	}
}
//@RequestParam(value="studentId", required=false)
