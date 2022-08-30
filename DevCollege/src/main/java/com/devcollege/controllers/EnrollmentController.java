package com.devcollege.controllers;

import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.StudentNotFoundException;
import com.devcollege.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/enrollment")
public class EnrollmentController {
	@Autowired
	private EnrollmentService enrollmentService;

	@PostMapping("/add")
	public ResponseEntity<String> addEnrolmentForCourse(@Valid @RequestBody Enrollment enrollment) {
		Enrollment savedEnrollment = enrollmentService.addEnrollmentForCourse(enrollment);
		return ResponseEntity.ok("Successfully Added Student details for "+ enrollment.getEnrollmentId());
	}

	@GetMapping("/get/{enrollId}")
	public ResponseEntity<Enrollment> getEnrollmentById(@Valid @PathVariable String enrollId) {
		Enrollment retrieveEnrollment = enrollmentService.getEnrollmentById(enrollId);
		return new ResponseEntity<Enrollment>(retrieveEnrollment, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Enrollment>> getAllEnrollments() throws StudentNotFoundException {
		List<Enrollment> enrollmentList = enrollmentService.getAllEnrollments();
		return new ResponseEntity<List<Enrollment>>(enrollmentList, HttpStatus.OK);
	}

	@GetMapping("/get/{studentId}")
	public ResponseEntity<Enrollment> getEnrollmentByStudentId(@Valid @PathVariable String enrollID) {
		Enrollment retrieveEnrollment = enrollmentService.getEnrollmentById(enrollID);
		return new ResponseEntity<Enrollment>(retrieveEnrollment, HttpStatus.OK);
	}

	@GetMapping("/status/{enrollId}")
	public ResponseEntity<Enrollment> changeStatus(@Valid @PathVariable String enrollId) {
		return null;
	}

	@GetMapping("/availability/{courseId}")
	public ResponseEntity<Course> checkAvailability(@Valid @PathVariable String courseId) {
		return null;
	}

	@GetMapping("/suggestion/{studentId}")
	public ResponseEntity<Student> courseSuggestion(@Valid @PathVariable String studentId) {
		return null;
	}
}
//@RequestParam(value="studentId", required=false)
