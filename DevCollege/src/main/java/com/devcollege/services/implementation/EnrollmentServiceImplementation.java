package com.devcollege.services.implementation;

import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.EnrollmentNotFoundException;
import com.devcollege.exceptions.NoDataFoundException;
import com.devcollege.exceptions.NotFoundException;
import com.devcollege.payloads.CourseDto;
import com.devcollege.payloads.EnrollmentDto;
import com.devcollege.repositories.CourseRepository;
import com.devcollege.repositories.EnrollmentRepository;
import com.devcollege.repositories.StudentRepository;
import com.devcollege.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.LongStream;

@Service
public class EnrollmentServiceImplementation implements EnrollmentService {
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public String addEnrollmentForCourse(Enrollment enrollment) throws NotFoundException {
		Student enrolledStudent = studentRepository.findById(enrollment.getStudentId()).orElseThrow(()
				-> new NotFoundException("studentId","",enrollment.getStudentId()));

		Course enrolledCourse = courseRepository.findById(enrollment.getCourseId()).orElseThrow(()
				-> new NotFoundException("courseId","",enrollment.getCourseId()));
		Calendar calender = Calendar.getInstance();
		calender.setTime(enrollment.getCourseStartDatetime());
		calender.add(Calendar.HOUR, enrolledCourse.getCourseDuration());
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
	public EnrollmentDto getEnrollmentById(String enrolId) throws NotFoundException {
		Enrollment enrollment = enrollmentRepository.findById(enrolId).orElseThrow(()
				-> new NotFoundException("studentId","",enrolId));

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
	public List<EnrollmentDto> getAllEnrollments() throws NoDataFoundException {
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
//
//		Student studentStatus = studentRepository.findById(student.getStudentId()).get();
//
//		Course courseStatus = courseRepository.findById(course.getCourseId()).get();

		enrollmentStatus.setCourseStatus(enrollmentStatus.getCourseStatus());
		if (enrollmentStatus.equals("Cancelled")) {

//			Float halfAmount;
//			halfAmount=(courseStatus.getCourseFee()/2);

//			studentStatus.setWalletAmount(student.halfAmount);
//
//			studentStatus.setWalletAmount(enrolledStudent.getWalletAmount() - enrolledCourse.getCourseFee());
//			studentRepository.save(enrolledStudent);

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
	public String checkAvailability(String courseId) throws NotFoundException {
//		Enrollment checkCourse = enrollmentRepository.findById(enrollment.getEnrolId()).orElse(null);
		Course checkCourse = courseRepository.findById(courseId).orElseThrow();

		Enrollment checkAvailable = enrollmentRepository.findById(courseId).orElseThrow(()
				-> new NotFoundException("courseId", "", courseId));

//		Enrollment totalSlot = enrollmentRepository.count(courseId.equalsIgnoreCase(checkAvailable.getCourseStatus()));
		if (courseId.equalsIgnoreCase("Allocated")) {
//			Enrollment totalSlot = enrollmentRepository.count(checkAvailable.getCourseStatus());
			int totalCount;
//			@Query(value="SELECT course_status FROM
//					enrollments enr
//					WHERE enr.getCourseStatus() = "Allocated" AND enr.courseId =  ")
//					List<String> getTotalSlot(Long id, Long id2);
		}
		if (checkAvailable.getCourseStatus().equalsIgnoreCase("Cancelled") ||
				checkAvailable.getCourseStatus().equalsIgnoreCase("Completed") ) {
//				String query = "select count(*) from courses where course_status = 'Allocated' ";
//				int counter = 0;
//				if (checkAvailable.getCourseStatus().equalsIgnoreCase("Allocated")) {
//					counter += 1;
//					counter++;
//				}
//			if (totalSlot < checkCourse.getNoOfSlot()) {
//				return courseId + " not available for enrollment.";
//			}
		}
		return courseId + " available for enrollment.";
	}

	@Override
	public List<Course> courseSuggestion(String studentId) throws NotFoundException {
		Student checkStudent = studentRepository.findById(studentId).orElseThrow(()
				-> new NotFoundException("studentId","",studentId));

		List<Course> courseList = courseRepository.findAll();
		List<Course> selectedCourse = new ArrayList<>();
		
		for(Course c : courseList){
			if(checkStudent.getHighestQualification().equalsIgnoreCase(c.getCourseTag())) {
				selectedCourse.add(c);
			}
		}
		return selectedCourse;
	}
}