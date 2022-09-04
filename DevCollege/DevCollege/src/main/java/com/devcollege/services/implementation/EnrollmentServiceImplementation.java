package com.devcollege.services.implementation;

import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.NoDataFoundException;
import com.devcollege.exceptions.NotFoundException;
import com.devcollege.payloads.EnrollmentDto;
import com.devcollege.repositories.CourseRepository;
import com.devcollege.repositories.EnrollmentRepository;
import com.devcollege.repositories.StudentRepository;
import com.devcollege.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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

		if (enrolledCourse.getNoOfSlot() >= 1) {
			enrolledCourse.setNoOfSlot((enrolledCourse.getNoOfSlot() - 1));
		}else {
			return "Course is not available for enrollment, It's full..!!";
		}



//		if(enrolledStudent.getStudentId().equals(enrollment.getStudentId())) {
//			return "You are not able to enroll to this course, because you have already enrolled the course..!!";
//		}else
		if(enrolledStudent.getWalletAmount()>=enrolledCourse.getCourseFee()) {
//			if(enrolledStudent.getStudentId().equals(enrollment.getStudentId())) {
//				return "You are not able to enroll to this course, because you have already enrolled the course..!!";
//			}
			enrolledStudent.setWalletAmount(enrolledStudent.getWalletAmount() - enrolledCourse.getCourseFee());
//			studentRepository.save(enrolledStudent);
			enrollment.setCourseStatus("Allocated");
			enrollment.setStudentId(enrollment.getStudentId());
			enrollment.setCourseId(enrollment.getCourseId());
			enrollment.setCourseStartDatetime(enrollment.getCourseStartDatetime());
			Enrollment getId = enrollmentRepository.save(enrollment);
			courseRepository.save(enrolledCourse);

			return "Successfully Enrolled for " + enrolledStudent.getStudentName() + " in course " + enrolledCourse.getCourseName()
					+ " for " + getId.getEnrolId();
		}else {
			float remainingAmount = (enrolledCourse.getCourseFee() - enrolledStudent.getWalletAmount());
			return "Insufficient balance in wallet, Student need " + remainingAmount + " for enrollment";
		}
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
		Student checkStudent = studentRepository.findById(studentId).orElseThrow(()
				-> new NotFoundException("StudentId","",studentId));

		List<Enrollment> enrolmentList = enrollmentRepository.findAll();
		List<Enrollment> studentDetail = new ArrayList<>();

		for(Enrollment e : enrolmentList){
			if(checkStudent.getStudentId().equals(e.getStudentId())) {
				studentDetail.add(e);
				return studentDetail;
			}
		}
		return studentDetail;
	}

	@Override
	public Map<String,String> changeStatus(Enrollment enrollment,String enrolId) throws NotFoundException {
		Enrollment enrollmentStatus = enrollmentRepository.findById(enrolId).orElseThrow(
				() -> new NoDataFoundException("Failed to Change Status for enrolment Id " + enrolId));
//		System.out.println(enrollment.getCourseStatus());
//		enrollmentStatus.setCourseStatus("Cancelled");
//		enrollmentRepository.save(enrollmentStatus);
		Student student = studentRepository.findById(enrollmentStatus.getStudentId()).orElseThrow();

		Course course = courseRepository.findById(enrollmentStatus.getCourseId()).orElseThrow();

		if (enrollmentStatus.getCourseStatus().equals("Cancelled")) {
			if(enrollment.getCourseStatus().equals("InProgress")){
				Map<String,String> detailedMessage = new HashMap<>();
				detailedMessage.put("Failed to Change Status for enrolment Id " , enrolId);
				return detailedMessage;
			} else if (enrollment.getCourseStatus().equals("Completed")) {
				Map<String,String> detailedMessage = new HashMap<>();
				detailedMessage.put( "Failed to Change Status for enrolment Id " , enrolId);
				return detailedMessage;
			}else if(enrollment.getCourseStatus().equals("Cancelled")){
				Map<String,String> detailedMessage = new HashMap<>();
				detailedMessage.put("Failed to Change Status for enrolment Id " , enrolId);
				return detailedMessage;
			}
		}

//		if(enrollmentStatus.getCourseStatus().equals("Allocated")) {
//			if(enrollment.getCourseStatus().equals("Cancelled")) {
//			enrollmentStatus.setCourseStatus(enrollment.getCourseStatus());
//			course.setNoOfSlot((course.getNoOfSlot() + 1));
//			float total = student.getWalletAmount() + course.getCourseFee();
//			student.setWalletAmount(total);
////			studentRepository.save(student);
////			courseRepository.save(course);
//			enrollmentRepository.save(enrollmentStatus);
//			return "Successfully changed the status " + enrollmentStatus.getCourseStatus() + " for " + enrolId;
//		}
		if (enrollmentStatus.getCourseStatus().equals("Allocated")) {
			if(enrollmentStatus.getCourseStatus().equals("Cancelled")) {

				Date courseStartDateTime = enrollmentStatus.getCourseStartDatetime();
				LocalDateTime cancelledTime = LocalDateTime.now();
				long difference = courseStartDateTime.getDate() - cancelledTime.getDayOfMonth();

				if (difference >= 2) {
					student.setWalletAmount(student.getWalletAmount() + course.getCourseFee());
					course.setNoOfSlot(course.getNoOfSlot()+1);

					Map<String,String> detailedMessage = new HashMap<>();
					detailedMessage.put("100 %" , " amount refunded..!!");
					return detailedMessage;
				} else if (difference >= 1) {
					Float oneDayRefund;
					oneDayRefund = ((course.getCourseFee() / 100) * 70);
					student.setWalletAmount(student.getWalletAmount()  + oneDayRefund);
					course.setNoOfSlot(course.getNoOfSlot()+1);

					Map<String,String> detailedMessage = new HashMap<>();
					detailedMessage.put("70 %" , " amount refunded..!!");
					return detailedMessage;
				} else if (difference <= 1) {
					Float hoursRefund;
					hoursRefund = (course.getCourseFee() / 2);
					student.setWalletAmount(student.getWalletAmount() + hoursRefund);
					course.setNoOfSlot(course.getNoOfSlot() + 1);
					Map<String,String> detailedMessage = new HashMap<>();
					detailedMessage.put("50 %" , " amount refunded..!!");
					return detailedMessage;
				}
			} else if(enrollment.getCourseStatus().equalsIgnoreCase("InProgress")) {
				enrollmentStatus.setCourseStatus(enrollment.getCourseStatus());
			}
		}
		enrollmentStatus.setCourseStatus(enrollment.getCourseStatus());
		enrollmentRepository.save(enrollmentStatus);
		studentRepository.save(student);
		courseRepository.save(course);
		Map<String,String> detailedMessage = new HashMap<>();
		detailedMessage.put("Successfully changed the status for " , enrolId);
		return detailedMessage;
	}

	@Override
	public String checkAvailability(String courseId) throws NotFoundException {
//		Enrollment checkCourse = enrollmentRepository.findById(enrollment.getEnrolId()).orElse(null);
		Course checkCourse = courseRepository.findById(courseId).orElseThrow(()
				-> new NotFoundException("courseId", "", courseId));

		if(checkCourse.getNoOfSlot()>0){
			return courseId + checkCourse.getCourseName() + " available for enrollment.";
		}else {
			return courseId + checkCourse.getCourseName() + " not available for enrollment.";
		}
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