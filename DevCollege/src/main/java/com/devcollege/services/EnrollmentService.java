package com.devcollege.services;

import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.EnrollmentNotFoundException;
import com.devcollege.exceptions.NotFoundException;
import com.devcollege.payloads.EnrollmentDto;

import java.util.List;


public interface EnrollmentService {
	
	public String addEnrollmentForCourse(Enrollment enrollment) throws EnrollmentNotFoundException;
	
	public EnrollmentDto getEnrollmentById(String enrolId) throws EnrollmentNotFoundException;
	
	public List<EnrollmentDto> getAllEnrollments() throws EnrollmentNotFoundException;

	public Enrollment getEnrollmentByStudentId(Student studentId, Enrollment enrollment) throws EnrollmentNotFoundException;

	public String changeStatus(String enrolId) throws EnrollmentNotFoundException ;

	public String checkAvailability(String courseId) throws NotFoundException;

	//String checkAvailability(String courseId) throws EnrollmentNotFoundException;

//	public List<String> courseSuggestion(Student studentId, Course course) throws EnrollmentNotFoundException;

	List<Course> courseSuggestion(String studentId) throws EnrollmentNotFoundException;

}
