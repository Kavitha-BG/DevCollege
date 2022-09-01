package com.devcollege.services.implementation;

import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.EmptyInputException;
import com.devcollege.exceptions.EnrollmentNotFoundException;
import com.devcollege.exceptions.NotFoundException;
import com.devcollege.payloads.EnrollmentDto;
import com.devcollege.repositories.CourseRepository;
import com.devcollege.repositories.EnrollmentRepository;
import com.devcollege.repositories.StudentRepository;
import com.devcollege.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImplementation implements EnrollmentService {

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public String addEnrollmentForCourse(Enrollment enrollment) throws EnrollmentNotFoundException {
		Student enrolledStudent = studentRepository.findById(enrollment.getStudentId()).orElseThrow(()
				-> new NotFoundException("studentId","",enrollment.getStudentId()));

		Course enrolledCourse = courseRepository.findById(enrollment.getCourseId()).orElseThrow(()
				-> new NotFoundException("courseId","",enrollment.getCourseId()));
		Calendar calender = Calendar.getInstance();
		calender.setTime(enrollment.getCourseStartDatetime());
		calender.add(Calendar.MINUTE, enrolledCourse.getCourseDuration());
		Date date = calender.getTime();
		enrollment.setCourseEndDatetime(date);

		if(enrolledStudent.getWalletAmount()>=enrolledCourse.getCourseFee()){
			enrolledStudent.setWalletAmount(enrolledStudent.getWalletAmount() - enrolledCourse.getCourseFee());
			studentRepository.save(enrolledStudent);
			enrollment.setCourseStatus("Allocated");
			enrollment.setStudentId(enrollment.getStudentId());
			enrollment.setCourseId(enrollment.getCourseId());
			enrollment.setCourseStartDatetime(enrollment.getCourseStartDatetime());
			Enrollment getId = enrollmentRepository.save(enrollment);

			return "Successfully Enrolled for " + enrolledStudent.getStudentName() + " in course " + enrolledCourse.getCourseName()
					+ " for " + getId.getEnrolId();
		}else
			return "Student wallet amount should be greater than course fee";
	}

	@Override
	public EnrollmentDto getEnrollmentById(String enrolId) throws EnrollmentNotFoundException {
		Enrollment enrollment = enrollmentRepository.findById(enrolId).orElseThrow(()
				-> new EnrollmentNotFoundException("Enrollment Id: " + enrolId + " does not exist."));

		Student student = studentRepository.findById(enrollment.getStudentId()).orElseThrow();

		Course course = courseRepository.findById(enrollment.getCourseId()).orElseThrow();

		EnrollmentDto enrolledList = new EnrollmentDto();
		enrolledList.setEnrolId(enrollment.getEnrolId());
		enrolledList.setCourseId(enrollment.getCourseId());
		enrolledList.setCourseStatus(enrollment.getCourseStatus());
		enrolledList.setStudentId(enrollment.getStudentId());
		enrolledList.setCourseStartDatetime(enrollment.getCourseStartDatetime());
		enrolledList.setCourseEndDatetime(enrollment.getCourseEndDatetime());
		enrolledList.setCourseLink("http://localhost:8080/course/get/" + course.getCourseId());
		enrolledList.setStudentLink("http://localhost:8080/student/get/" + student.getStudentId());

		return enrolledList;
	}

	@Override
	public List<Enrollment> getAllEnrollments() throws EnrollmentNotFoundException {
		List<Enrollment> enrollmentList = enrollmentRepository.findAll();
		List<EnrollmentDto> enrollmentDtos = new ArrayList<>();
		for (Enrollment enrol: enrollmentList) {
			EnrollmentDto enrollmentDto= getEnrollmentById(enrol.getEnrolId());
			enrollmentDtos.add(enrollmentDto);
		}
		return enrollmentDtos;
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
		} else {
			//		return new EnrollmentNotFoundException("Course Id: " + courseId + " does not exist");
			return null;
		}
	}

	@Override
	public List<String> courseSuggestion(Student studentId, Course course) throws EnrollmentNotFoundException {
		List<String> courseList = new ArrayList<>();
		enrollmentRepository.findAll();
		if (studentId.getHighestQualification().equalsIgnoreCase(course.getCourseTag())) {

//			ResponseEntity<Course[]> responseEntity =
//					restTemplate.getForEntity("http://localhost:8080/course/getAll", Course[].class);
//			Course[] courseArray = responseEntity.getBody();
//			return Arrays.stream(courseArray)
//					.map(Course::getCourseName)
//					.collect(Collectors.toList());
//		} else {
//			throw  new EnrollmentNotFoundException("Student Id : " + studentId + " doesn't exist.");
//		}

		}
		return null;
	}
}