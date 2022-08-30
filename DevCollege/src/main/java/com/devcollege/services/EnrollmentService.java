package com.devcollege.services;

import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.StudentNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;


public interface EnrollmentService {
	
	public Enrollment addEnrollmentForCourse(Enrollment enrollment);
	
	public Enrollment getEnrollmentById(String enrollmentId);
	
	public List<Enrollment> getAllEnrollments() throws StudentNotFoundException;

	public Enrollment getEnrollmentByStudentId(String enrollmentId);

	public Enrollment changeStatus(String enrollId);

	public Enrollment checkAvailability(String courseId);

	public Enrollment courseSuggestion(String studentId);

}
