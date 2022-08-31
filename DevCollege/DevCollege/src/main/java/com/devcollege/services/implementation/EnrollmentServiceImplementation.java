package com.devcollege.services.implementation;

import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.EmptyInputException;
import com.devcollege.exceptions.EnrollmentNotFoundException;
import com.devcollege.repositories.EnrollmentRepository;
import com.devcollege.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImplementation implements EnrollmentService {

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Override
	public Enrollment addEnrollmentForCourse(Enrollment enrollment) {
		Student student = new Student();
		Course course = new Course();
//		Enrollment enrolledStudent = enrollmentRepository.findById(student.getStudentId());
		if( student.getStudentId().isEmpty() && course.getCourseId().isEmpty() ) {
			throw new EmptyInputException();
		} else {
		//	Enrollment enrolledDetails = enrollmentRepository.findById(student.getStudentId());
//			enrolledStudent.getCourseEndDatetime() = course.getCourseDuration() +  enrollment.getCourseStartDatetime();
//			student.getWalletAmount() -= course.getCourseFee();
//			enrolledStudent.setCourseStatus("Allocated");
//			return enrolledStudent;
			return null;
		}
	}

	@Override
	public Enrollment getEnrollmentById(String enrolId) throws EnrollmentNotFoundException {
		Enrollment enrollment = enrollmentRepository.findById(enrolId).orElse(null);
		if (enrollment != null) {
			return enrollment;
		} else {
			throw new EnrollmentNotFoundException("Enrollment Id: " + enrolId + " does not exist.");
		}
	}

	@Override
	public List<Enrollment> getAllEnrollments() throws EnrollmentNotFoundException {
		List<Enrollment> enrollmentList = enrollmentRepository.findAll();
		if (!enrollmentList.isEmpty()) {
			return enrollmentRepository.findAll();
		} else {
			throw new EnrollmentNotFoundException("No data found..!!");
		}
	}

	@Override
	public Enrollment getEnrollmentByStudentId(Student studentId, Enrollment enrollment) throws EnrollmentNotFoundException {
		Enrollment enrollmentList = enrollmentRepository.findById(studentId.getStudentId()).orElse(null);
		if (studentId != null) {
			return enrollment;
		} else {
			throw new EnrollmentNotFoundException(studentId.getStudentName() + " does not enrol for any course.");
		}
	}

	@Override
	public String changeStatus(String enrolId) throws EnrollmentNotFoundException {
		Enrollment enrollmentStatus = enrollmentRepository.findById(enrolId).orElseThrow(
				() -> new EnrollmentNotFoundException("Failed to Change Status for enrolment Id " + enrolId));

		enrollmentStatus.setCourseStatus(enrollmentStatus.getCourseStatus());
		if (enrollmentStatus.equals("Cancelled")) {
//			if () {
//
//			} else if () {
//
//			} else {
//
//			}
		}
		return null;
	}

	@Override
	public String checkAvailability(Course courseId) throws EnrollmentNotFoundException {

		Enrollment enrollment = new Enrollment();
		Enrollment checkCourse = enrollmentRepository.findById(enrollment.getEnrolId()).orElse(null);

		if (enrollment.getCourseStatus().equals("Cancelled") ||
				(enrollment.getCourseStatus().equals("Completed") &&
						courseId.getNoOfSlot() < courseId.getNoOfSlot())) {
			return courseId + courseId.getCourseName() + " available for enrolment.";
		}else {
	//		return new EnrollmentNotFoundException("Course Id: " + courseId + " does not exist");
			return null;
		}
	}

	@Override
	public List<String> courseSuggestion(Student studentId, Course course) throws EnrollmentNotFoundException {
		List<String> courseList = new ArrayList<>();
		enrollmentRepository.findAll();
		if(studentId.getHighestQualification().equalsIgnoreCase(course.getCourseTag())){

			ResponseEntity<Course[]> responseEntity =
					restTemplate.getForEntity("http://localhost:8080/course/getAll", Course[].class);
			Course[] courseArray = responseEntity.getBody();
			return Arrays.stream(courseArray)
					.map(Course::getCourseName)
					.collect(Collectors.toList());

		} else {
			throw  new EnrollmentNotFoundException("Student Id : " + studentId + " doesn’t exist.");
		}
	}


	RestTemplate restTemplate = new RestTemplate();

	private static final String viewCoursesDetailsURL = "http://localhost:8080/student/getAll";
	private static final String viewStudentsDetailsURL = "http://localhost:8080/student/getAll";

	public ResponseEntity<Course> allCourses(Course course) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Course> entity = new HttpEntity<Course>(course, headers);
		ResponseEntity<Course> response = restTemplate.exchange(viewCoursesDetailsURL,
				HttpMethod.GET, entity, Course.class);
		return response;
	}

	public ResponseEntity<Student> allStudents(Student student) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);
		ResponseEntity<Student> response = restTemplate.exchange(viewStudentsDetailsURL,
				HttpMethod.GET, entity, Student.class);
		return response;
	}

}


