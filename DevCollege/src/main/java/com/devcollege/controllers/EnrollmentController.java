package com.devcollege.controllers;

import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.EnrollmentNotFoundException;
import com.devcollege.exceptions.NoSuchElementException;
import com.devcollege.exceptions.NotFoundException;
import com.devcollege.payloads.EnrollmentDto;
import com.devcollege.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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
	public ResponseEntity<List<EnrollmentDto>> getAllEnrollments() throws EnrollmentNotFoundException {
		List<EnrollmentDto> enrollmentList = enrollmentService.getAllEnrollments();
		return new ResponseEntity<List<EnrollmentDto>>(enrollmentList, HttpStatus.OK);
	}

	@GetMapping("/getstudent/{studentId}")
	public ResponseEntity<List<Enrollment>> getEnrollmentByStudentId(@Valid @PathVariable String studentId) throws NoSuchElementException {
		List<Enrollment> retrieveEnrollment = enrollmentService.getEnrollmentByStudentId(studentId) ;
		return new ResponseEntity<List<Enrollment>>( retrieveEnrollment, HttpStatus.OK);
	}

	@PostMapping("/status/{enrollId}")
	public ResponseEntity<String> changeStatus(@PathVariable String enrollId) throws EnrollmentNotFoundException{
		String updatedStatus = enrollmentService.changeStatus(enrollId);
		return ResponseEntity.ok("Successfully change the status from "
				+ getAllEnrollments().getStatusCode() + "to"  + updatedStatus + " for enrol id "+ enrollId);
	}

	@GetMapping("/availability/{courseId}")
	public ResponseEntity<String> checkAvailability(@PathVariable String courseId) throws NotFoundException {
		String checkCourseAvailability = enrollmentService.checkAvailability(courseId);
		return ResponseEntity.ok(courseId + " " +" available for enrolment.");
	}

	@GetMapping("/suggestion/{studentId}")
			//, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Course>> courseSuggestion(@PathVariable String studentId) throws NotFoundException, EnrollmentNotFoundException {
		List<Course> suggestCourse = enrollmentService.courseSuggestion(studentId);
		return new ResponseEntity<List<Course>>(suggestCourse, HttpStatus.OK);
//		return null;
	}

}
