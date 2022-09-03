package com.devcollege.services.implementation;

import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.EnrollmentNotFoundException;
import com.devcollege.exceptions.NoDataFoundException;
import com.devcollege.exceptions.NoSuchElementException;
import com.devcollege.exceptions.NotFoundException;
import com.devcollege.payloads.EnrollmentDto;
import com.devcollege.repositories.CourseRepository;
import com.devcollege.repositories.EnrollmentRepository;
import com.devcollege.repositories.StudentRepository;
import com.devcollege.services.EnrollmentService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
	public List<Enrollment> getEnrollmentByStudentId(String studentId) throws NoSuchElementException {
//		Enrollment enrollmentList = enrollmentRepository.findById(studentId).orElseThrow(()
//				-> new NoSuchElementException(studentId + " does not enrol for any course."));
		Student checkStudent = studentRepository.findById(studentId).orElseThrow(()
				-> new NoSuchElementException(studentId + " does not enrol for any course."));

		List<Enrollment> enrolmentList = enrollmentRepository.findAll();
		List<Enrollment> studentDetail = new ArrayList<>();

		for(Enrollment e : enrolmentList){
			if(enrolmentList.contains(e.getStudentId())) {
				studentDetail.add(e);
			}
		}
		return studentDetail;
	}

	@Override
	public String changeStatus(String enrolId) throws EnrollmentNotFoundException {
		Enrollment enrollmentStatus = enrollmentRepository.findById(enrolId).orElseThrow(
				() -> new EnrollmentNotFoundException("Failed to Change Status for enrolment Id " + enrolId));

		Student studentRefundAmount = studentRepository.findById(enrollmentStatus.getCourseStatus()).get();

		Course courseAmount = courseRepository.findById(enrollmentStatus.getCourseStatus()).get();

//		Calendar calender = Calendar.getInstance();
//		calender.setTime(enrollment.getCourseStartDatetime());
//		calender.add(Calendar.HOUR, enrolledCourse.getCourseDuration());
//		Date date = calender.getTime();
//		enrollment.setCourseEndDatetime(date);


		@Pattern(regexp = "yyyy-MM-dd HH:mm:ss")
		LocalDateTime cancelledDateTime = LocalDateTime.now();
		enrollmentStatus.setCourseStatus(enrollmentStatus.getCourseStatus()+cancelledDateTime);

		Date difference = cancelledDateTime.minusHours(enrollmentStatus.getCourseStartDatetime());

//		LocalDateTime.ofInstant(enrollmentStatus.getCourseStartDatetime()-cancelledDateTime);

		long diff = cancelledDateTime.getHour() - enrollmentStatus.getCourseStartDatetime();
		long hours = TimeUnit.MILLISECONDS.toHours(diff);

		if (enrollmentStatus.equals("Cancelled")) {
			if(hours < 48 && hours > 24){
				studentRefundAmount.setWalletAmount(studentRefundAmount.getWalletAmount()+courseAmount.getCourseFee());
				studentRepository.save(studentRefundAmount);
				return "100% amount refunded to student wallet";
			} else if (hours < 24 && hours > 12) {
				Float oneDayRefund;
				oneDayRefund=((courseAmount.getCourseFee()/100)*70);
				studentRefundAmount.setWalletAmount(studentRefundAmount.getWalletAmount()+oneDayRefund);
				studentRepository.save(studentRefundAmount);
				return "70% amount refunded to student wallet";
			} else if (hours <= 12) {
				Float hoursRefund;
				hoursRefund = ((courseAmount.getCourseFee()/100)*50);
				studentRefundAmount.setWalletAmount(studentRefundAmount.getWalletAmount()+hoursRefund);
				studentRepository.save(studentRefundAmount);
				return "50% amount refunded to student wallet";
			}
		} else
			return "Not able to refund the amount";
		return enrolId;
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

		for(Course course : courseList){
			if(checkStudent.getHighestQualification().equalsIgnoreCase(course.getCourseTag())) {
				selectedCourse.add(course);
			}
		}
		return selectedCourse;
	}
}