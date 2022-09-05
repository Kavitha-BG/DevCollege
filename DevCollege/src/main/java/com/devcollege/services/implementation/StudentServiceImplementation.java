package com.devcollege.services.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.StudentWallet;
import com.devcollege.payloads.EnrollmentDto;
import com.devcollege.repositories.CourseRepository;
import com.devcollege.repositories.EnrollmentRepository;
import com.devcollege.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devcollege.entities.Student;
import com.devcollege.repositories.StudentRepository;
import com.devcollege.services.StudentService;
import com.devcollege.exceptions.*;

@Service
public class StudentServiceImplementation implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private EnrollmentService enrollmentService;

	@Override
	public Student addStudent(Student student) {
		if (student.getStudentName().isEmpty() || student.getStudentContactNo().length() == 0 ||
				student.getHighestQualification().isEmpty() || student.getWalletAmount().isNaN()) {
			throw new NotFoundException("studentId","", student.getStudentId());
		} else {
			Student savedStudent = studentRepository.save(student);
			return savedStudent;
		}
	}

	@Override
	public Student updateStudentById(Student student, String studentId) {
		Student updateStudent = studentRepository.findById(studentId).orElseThrow(()
				-> new NotFoundException("studentId","", studentId));

			updateStudent.setStudentName(student.getStudentName());
			updateStudent.setHighestQualification(student.getHighestQualification());
			updateStudent.setStudentContactNo(student.getStudentContactNo());

			Student savedStudent = studentRepository.save(updateStudent);
			return savedStudent;
	}

	@Override
	public Map<String,String> deleteStudent(String studentId) throws NotFoundException{

		Student student = studentRepository.findById(studentId).orElseThrow(()
				-> new NotFoundException("studentId", "", studentId));

//		float total = 0;
//		List<Enrollment> enrollmentList = enrollmentRepository.findAll();
//
//		for (Enrollment e: enrollmentList) {
//			if (studentId.equals(e.getStudentId())) {
//				String courseId = e.getCourseId();
//				Course course = courseRepository.findById(courseId).get();
//				Float fee = course.getCourseFee();
//				total = (total + fee) / 2;
//
//				enrollmentRepository.deleteById(e.getEnrolId());
//				Map<String, String> message = new HashMap<>();
//				message.put("Successfully deleted Student details And amount will be " +
//						"refunded in original payment method within 24 hours :", studentId);
//				return message.toString();
////			}
////		}
//
////			if(enrollmentList.equals(studentId)){
////				studentRepository.deleteById(studentId);
////				return "Successfully deleted Student details with " + studentId +
////						" And amount " + total/2 +" will be refunded in original payment method within 24 hours.";
//			} else {
//				Map<String, String> message = new HashMap<>();
//				studentRepository.deleteById(studentId);
//				return "Successfully Deleted Student details :" + "" + studentId;
//			}
//		}
//			return  null;
		List<Enrollment> enrollmentList = enrollmentRepository.findAll();
		ArrayList<String> list = new ArrayList<String>();
		for (Enrollment e: enrollmentList) {
			String id= e.getStudentId();
			list.add(id);
		}

		System.out.println();
		boolean flag = false;
		for (int i=0; i<list.size(); i++){
			List<Enrollment> enrollments = this.enrollmentRepository.getAllEnrollments(list.get(i));
			List<EnrollmentDto> enrolDtoList = new ArrayList<>();
			for (Enrollment e: enrollments){
				EnrollmentDto response = enrollmentService.getEnrollmentById(e.getEnrolId());
				response.setCourseStatus("Cancelled");
				enrollmentRepository.save(e);
				Map<String,String> msg = new HashMap<String,String>();
				msg.put(" Enrolment as cancelled and deleted", studentId);
				return msg;
			}
		}
		return null;
	}

	@Override
	public Student getStudentById(String studentId) throws NotFoundException {
		Student student = studentRepository.findById(studentId).orElseThrow(()
				-> new NotFoundException("studentId","", studentId));
		return student;
	}

	@Override
	public List<Student> getAllStudents() throws NoDataFoundException {
		List<Student> studentList = studentRepository.findAll();

		if (!studentList.isEmpty()) {
			return studentRepository.findAll();
		} else {
			throw new NoDataFoundException("errorMessage");
		}
	}

	@Override
	public Float addWalletAmount(String studentId, StudentWallet studentWallet ) {
		Student student = studentRepository.findById(studentId).orElseThrow(()
				-> new NotFoundException("studentId","", studentId));


		if(studentWallet.getAmount() > 0) {
			Float finalAmount = student.getWalletAmount() + studentWallet.getAmount();
			student.setWalletAmount(finalAmount);
			studentRepository.save(student);

			return finalAmount;
		} else {
			throw new RuntimeException("Amount should be positive numeric value");
		}
	}

	@Override
	public Map<String,String> getWalletDetail(String studentId) {

		Student retrieveStudentWallet = studentRepository.findById(studentId).orElseThrow(()
				-> new NotFoundException("studentId","", studentId));

		Map<String,String> details = new HashMap<>();
		details.put("StudentId", studentId);
		details.put("WalletAmount", "" +retrieveStudentWallet.getWalletAmount());

		return details;
	}

}

