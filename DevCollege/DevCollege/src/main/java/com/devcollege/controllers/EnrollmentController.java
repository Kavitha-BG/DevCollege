package com.devcollege.controllers;

import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.EnrollmentNotFoundException;
import com.devcollege.payloads.EnrollmentDto;
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
	public ResponseEntity<String> addEnrolmentForCourse(@Valid @RequestBody Enrollment enrollment) throws EnrollmentNotFoundException{
		String savedEnrollment = enrollmentService.addEnrollmentForCourse(enrollment);
		return ResponseEntity.ok(savedEnrollment);
	}

	@GetMapping("/get/{enrolId}")
	public ResponseEntity<EnrollmentDto> getEnrollmentById(@Valid @PathVariable String enrolId) throws EnrollmentNotFoundException {
		EnrollmentDto retrieveEnrollment = enrollmentService.getEnrollmentById(enrolId);
		return new ResponseEntity<EnrollmentDto>(retrieveEnrollment, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Enrollment>> getAllEnrollments() throws EnrollmentNotFoundException {
		List<Enrollment> enrollmentList = enrollmentService.getAllEnrollments();
		return new ResponseEntity<List<Enrollment>>(enrollmentList, HttpStatus.OK);
	}

	@GetMapping("/getstudent/{studentId}")
	public ResponseEntity<EnrollmentDto> getEnrollmentByStudentId(@Valid @PathVariable Student studentId, @RequestBody Enrollment enrollment) throws EnrollmentNotFoundException{
		EnrollmentDto retrieveEnrollment = enrollmentService.getEnrollmentById(enrollment.getEnrolId()) ;
		return new ResponseEntity<EnrollmentDto>(retrieveEnrollment, HttpStatus.OK);
	}

	@PostMapping("/status/{enrollId}")
	public ResponseEntity<String> changeStatus(@PathVariable String enrollId) throws EnrollmentNotFoundException{
		String updatedStatus = enrollmentService.changeStatus(enrollId);
		return ResponseEntity.ok("Successfully change the status from "
				+ getAllEnrollments().getStatusCode() + "to"  + updatedStatus + " for enrol id "+ enrollId);
	}

	@GetMapping("/availability/{courseId}")
	public ResponseEntity<String> checkAvailability(@PathVariable Course courseId) throws EnrollmentNotFoundException{
		String checkCourseAvailability = enrollmentService.checkAvailability(courseId);
		return ResponseEntity.ok(courseId + " " + courseId.getCourseName() +" available for enrolment.");
	}

	@GetMapping("/suggestion/{studentId}")
			//, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object[]> courseSuggestion(@PathVariable String studentId) {
//		Object[] suggestCourse = enrollmentService.courseSuggestion(studentId);
//		return new ResponseEntity<Object[]>(suggestCourse, HttpStatus.OK);
		return null;
	}

}
