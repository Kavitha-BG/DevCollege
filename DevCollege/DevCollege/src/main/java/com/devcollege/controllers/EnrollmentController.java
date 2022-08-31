package com.devcollege.controllers;

import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.EnrollmentNotFoundException;
import com.devcollege.exceptions.StudentNotFoundException;
import com.devcollege.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/enrollment")
public class EnrollmentController {
	@Autowired
	private EnrollmentService enrollmentService;

	@PostMapping("/add")
	public ResponseEntity<String> addEnrolmentForCourse(@Valid @RequestBody Enrollment enrollment) {
		Enrollment savedEnrollment = enrollmentService.addEnrollmentForCourse(enrollment);
		return ResponseEntity.ok("Successfully Added Student details for "+ enrollment.getEnrolId());
	}

	@GetMapping("/get/{enrollId}")
	public ResponseEntity<Enrollment> getEnrollmentById(@Valid @PathVariable String enrolId) throws EnrollmentNotFoundException {
		Enrollment retrieveEnrollment = enrollmentService.getEnrollmentById(enrolId);
		return new ResponseEntity<Enrollment>(retrieveEnrollment, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Enrollment>> getAllEnrollments() throws EnrollmentNotFoundException {
		List<Enrollment> enrollmentList = enrollmentService.getAllEnrollments();
		return new ResponseEntity<List<Enrollment>>(enrollmentList, HttpStatus.OK);
	}

	@GetMapping("/get/{studentId}")
	public ResponseEntity<Enrollment> getEnrollmentByStudentId(@Valid @PathVariable Student studentId, @RequestBody Enrollment enrollment) throws EnrollmentNotFoundException{
		Enrollment retrieveEnrollment = enrollmentService.getEnrollmentById(enrollment.getEnrolId()) ;
		return new ResponseEntity<Enrollment>(retrieveEnrollment, HttpStatus.OK);
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
	//		,  produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object[]> courseSuggestion(@PathVariable String studentId) {
	//	Object[] suggestCourse = enrollmentService.courseSuggestion(studentId);
	//	return new ResponseEntity<Object[]>(suggestCourse, HttpStatus.OK);
		return null;
	}

//	@PostMapping(value = "/redirect")
//	public RedirectView redirect(@RequestParam Map<String,String> input){
//
//		System.out.println(input);
//		RedirectView redirectView = new RedirectView();
//		redirectView.setUrl("http://localhost:8080/course/getAll");
//		return redirectView;
//
//	}

}
