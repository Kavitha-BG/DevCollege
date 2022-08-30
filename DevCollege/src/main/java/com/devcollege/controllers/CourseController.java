package com.devcollege.controllers;

import com.devcollege.entities.Course;
import com.devcollege.services.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/course")
public class CourseController {

	private final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;

	@PostMapping("/addcourse")
	public ResponseEntity<String> addCourse( @RequestBody Course course) {

		logger.info("Add course :");
		Course savedCourse = courseService.addCourse(course);
		return ResponseEntity.ok("Successfully Added Course details for " + course.getCourseId());
	}
	
	@PutMapping("/updatecourse/{courseId}")
	public ResponseEntity<String> updateCourseById(@Valid @RequestBody Course course, @PathVariable String courseId) {
		String updatedCourse = courseService.updateCourseById(course, courseId);
		return ResponseEntity.ok("Successfully Updated Course details for "+ updatedCourse);
	}
	
	@DeleteMapping("/deletecourse/{courseId}")
	public ResponseEntity<String> deleteCourse(@Valid @PathVariable String courseId) {
		courseService.deleteCourse(courseId);
		return ResponseEntity.ok("Successfully Deleted Course details for "+ courseId);
//		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/{courseId}")
	public ResponseEntity<Course> getCourseById(@Valid @PathVariable String courseId) {
		Course retrieveCourse = courseService.getCourseById(courseId);
		return new ResponseEntity<Course>(retrieveCourse, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Course>> getAllCourses() {
		List<Course> courseList = courseService.getAllCourses();
		return new ResponseEntity<List<Course>>(courseList, HttpStatus.OK);
	}

}
