package com.devcollege.services.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.devcollege.entities.StudentWallet;
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

	@Override
	public Student addStudent(Student student) {
		if (student.getStudentName().isEmpty() || student.getStudentContactNo().length() == 0 ||
				student.getHighestQualification().isEmpty() || student.getWalletAmount().isNaN()) {
			throw new EmptyInputException("Failed to add Student details.");
		} else {
			Student savedStudent = studentRepository.save(student);
			return savedStudent;
		}
	}

	@Override
	public Student updateStudentById(Student student, String studentId) {
		Student updateStudent = studentRepository.findById(studentId).orElseThrow(()
				-> new NoSuchElementFoundException(studentId + " doesn't exist."));

			updateStudent.setStudentName(student.getStudentName());
			updateStudent.setHighestQualification(student.getHighestQualification());
			updateStudent.setStudentContactNo(student.getStudentContactNo());

			Student savedStudent = studentRepository.save(updateStudent);
			return savedStudent;
	}

	@Override
	public void deleteStudent(String studentId) {
		Student student = studentRepository.findById(studentId).orElseThrow(()
				-> new InvalidInputException(studentId + " doesn't exist."));
		studentRepository.delete(student);
	}

	@Override
	public Student getStudentById(String studentId) throws StudentNotFoundException {
		Student student = studentRepository.findById(studentId).orElseThrow(()
				-> new StudentNotFoundException(studentId + " does not exist."));
		return student;
	}

	@Override
	public List<Student> getAllStudents() throws StudentNotFoundException {
		List<Student> studentList = studentRepository.findAll();

		if (!studentList.isEmpty()) {
			return studentRepository.findAll();
		} else {
			throw new StudentNotFoundException("No data found..!!");
		}
	}

	@Override
	public Float addWalletAmount(String studentId, StudentWallet studentWallet ) {
		Student student = studentRepository.findById(studentId).orElseThrow(()
				-> new EmptyInputException("Student Id: " + " doesn't exist."));

		Float finalAmount = student.getWalletAmount() + studentWallet.getAmount();
		student.setWalletAmount(finalAmount);
		studentRepository.save(student);

		return finalAmount;
	}

	@Override
	public Map<String,String> getWalletDetail(String studentId) {

		Student retrieveStudentWallet = studentRepository.findById(studentId).orElseThrow(()
				-> new StudentNotFoundException("Student Id: " + " doesn't exist."));

		Map<String,String> details = new HashMap<>();
		details.put("StudentId", studentId);
		details.put("WalletAmount", "" +retrieveStudentWallet.getWalletAmount());

		return details;
	}
}

