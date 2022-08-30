package com.devcollege.services.implementation;

import com.devcollege.entities.Enrollment;
import com.devcollege.exceptions.EmptyInputException;
import com.devcollege.exceptions.InvalidInputException;
import com.devcollege.exceptions.NoSuchElementFoundException;
import com.devcollege.exceptions.StudentNotFoundException;
import com.devcollege.repositories.EnrollmentRepository;
import com.devcollege.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImplementation implements EnrollmentService {
	@Autowired
	private EnrollmentRepository enrollmentRepository;


	@Override
	public Enrollment addEnrollmentForCourse(Enrollment enrollment) {
		return null;
	}

	@Override
	public Enrollment getEnrollmentById(String enrollmentId) {
		return null;
	}

	@Override
	public List<Enrollment> getAllEnrollments() throws StudentNotFoundException {
		return null;
	}

	@Override
	public Enrollment getEnrollmentByStudentId(String enrollmentId) {
		return null;
	}

	@Override
	public Enrollment changeStatus(String enrollId) {
		return null;
	}

	@Override
	public Enrollment checkAvailability(String courseId) {
		return null;
	}

	@Override
	public Enrollment courseSuggestion(String studentId) {
		return null;
	}
}
