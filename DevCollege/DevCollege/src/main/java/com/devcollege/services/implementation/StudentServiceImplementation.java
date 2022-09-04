package com.devcollege.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.StudentWallet;
import com.devcollege.repositories.CourseRepository;
import com.devcollege.repositories.EnrollmentRepository;
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
	public String deleteStudent(String studentId) {
		Student student = studentRepository.findById(studentId).orElseThrow(()
				-> new NotFoundException("studentId","", studentId));

		float total = 0;
		List<Enrollment> enrollmentList = enrollmentRepository.findAll();

		for (Enrollment e: enrollmentList) {
			System.out.println(studentId);
			System.out.println(e.getStudentId());
			if(studentId.equals(e.getStudentId())){
				String courseId = e.getCourseId();
				Course course = courseRepository.findById(courseId).get();
				Float fee = course.getCourseFee();
				total += fee;
			}
		}
		if(enrollmentList.equals(studentId)){
			studentRepository.deleteById(studentId);
			return "Successfully deleted Student details with " + studentId +
					" And amount " + total/2 +" will be refunded in original payment method within 24 hours.";
		} else {
			studentRepository.deleteById(studentId);
			return "Successfully Deleted Student details ";
		}

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

		Float finalAmount = student.getWalletAmount() + studentWallet.getAmount();
		student.setWalletAmount(finalAmount);
		studentRepository.save(student);

		return finalAmount;
	}

	@Override
	public Map<String,String> getWalletDetail(String studentId) {

		Student retrieveStudentWallet = studentRepository.findById(studentId).orElseThrow(()
				-> new NoDataFoundException(studentId));

		Map<String,String> details = new HashMap<>();
		details.put("StudentId", studentId);
		details.put("WalletAmount", "" +retrieveStudentWallet.getWalletAmount());

		return details;
	}
}

