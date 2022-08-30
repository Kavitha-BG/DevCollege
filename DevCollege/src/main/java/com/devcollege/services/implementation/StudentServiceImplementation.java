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
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class StudentServiceImplementation implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student addStudent(Student student) {
		if (student.getStudentName().isEmpty() || student.getStudentContactNo().length() == 0 ||
				student.getHighestQualification().isEmpty() || student.getWalletAmount().isNaN()) {
			throw new EmptyInputException();
		} else {
			Student savedStudent = studentRepository.save(student);
			return savedStudent;
		}
	}

	@Override
	public String updateStudentById(Student student, String studentId) {
		Student updateStudent = studentRepository.findById(studentId).orElse(null);
		if (!updateStudent.getStudentName().isEmpty() || updateStudent.getStudentContactNo().length() != 0 ||
				!updateStudent.getHighestQualification().isEmpty()) {

			updateStudent.setStudentName(student.getStudentName());
			updateStudent.setHighestQualification(student.getHighestQualification());
			updateStudent.setStudentContactNo(student.getStudentContactNo());

			studentRepository.save(updateStudent);
			return "Successfully updated student detail for course: " + studentId;
		} else {
			throw new NoSuchElementFoundException(studentId + " doesn't exist.");
		}
	}

	@Override
	public void deleteStudent(String studentId) {
		Student student = studentRepository.findById(studentId).orElse(null);
		if (student != null) {
			studentRepository.delete(student);
//			return "Successfully deleted student detail for course: " + studentId;
		} else {
			throw new InvalidInputException(studentId + " doesn't exist.");
		}
	}

	@Override
	public Student getStudentById(String studentId) {
		Student student = studentRepository.findById(studentId).orElse(null);
		if (student != null) {
			return student;
		} else {
			throw new StudentNotFoundException("Student Id: " + studentId + " does not exist.");
		}
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
				-> new EmptyInputException("Student Id: " + " doesn't exist."));

		Map<String,String> details = new HashMap<>();
		details.put("StudentId", studentId);
		details.put("WalletAmount", "" +retrieveStudentWallet.getWalletAmount());

		return details;
	}

}

