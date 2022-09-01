package com.devcollege.services;

import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.EnrollmentNotFoundException;
import com.devcollege.exceptions.StudentNotFoundException;
import com.devcollege.payloads.EnrollmentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;


public interface EnrollmentService {
	
	public String addEnrollmentForCourse(Enrollment enrollment) throws EnrollmentNotFoundException;
	
	public EnrollmentDto getEnrollmentById(String enrolId) throws EnrollmentNotFoundException;
	
	public List<Enrollment> getAllEnrollments() throws EnrollmentNotFoundException;

	public Enrollment getEnrollmentByStudentId(Student studentId, Enrollment enrollment) throws EnrollmentNotFoundException;

	public String changeStatus(String enrolId) throws EnrollmentNotFoundException ;

	public String checkAvailability(Course courseId) throws EnrollmentNotFoundException;

	public List<String> courseSuggestion(Student studentId, Course course) throws EnrollmentNotFoundException;
}
