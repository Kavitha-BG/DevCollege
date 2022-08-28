package com.devcollege.services.implementation;

import java.util.List;


import com.devcollege.entities.StudentWallet;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.devcollege.entities.Student;
import com.devcollege.repositories.StudentRepository;
import com.devcollege.services.StudentService;
import com.devcollege.exceptions.*;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class StudentServiceImplementation implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student addStudent(Student student) {
		if (student.getStudentName().isEmpty() || student.getStudentContactNo().length() == 0 ||
				student.getHighestQualification().isEmpty() || student.getWalletAmount().isNaN()) {
			throw new EmptyInputException("Student Id: " + " doesn't exist.");
		} else {
			Student savedStudent = studentRepository.save(student);
			return savedStudent;
		}
	}

	@Override
	public String updateStudentById(Student student, String studentId) {
		Student updateStudent = studentRepository.findById(studentId).orElse(null);
		if (updateStudent != null) {
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
//			studentRepository.deleteById(studentId);
			studentRepository.delete(student);
//			return "Successfully deleted student detail for course: " + studentId;
		} else {
			throw new InvalidInputException(studentId + " doesn't exist.");
		}
	}

	@Override
	public Student getStudentById(String studentId) {
		Student student = studentRepository.findByStudentId(studentId);
		if (student != null) {
			return student;
		} else {
			throw new StudentNotFoundException("Student Id: " + studentId + " does not exist.");
		}
	}


	@Override
	public List<Student> getAllStudent() throws StudentNotFoundException {
		List<Student> studentList = studentRepository.findAll();
		if (!studentList.isEmpty()) {
			return studentRepository.findAll();
		} else {
			throw new StudentNotFoundException("No data found..!!");
		}
	}

//	@Override
//	public Student addWalletAmount(@RequestParam String studentId, Float amount) {
//		try {
//			StudentWallet studentWallet = studentRepository.findByStudentId(studentId).get(0);
//			studentWallet.getAmount() = studentWallet.getAmount() + amount;
//			studentRepository.save(studentWallet);
//		} catch(IndexOutOfBoundsException exception) {
//
//		}

	//	if(walletAmount != null) {
//			throw new EmptyInputException("Student Id: "+ " doesn't exist.");
//		} else {
//			Student savedAmount = studentRepository.save(walletAmount);
//			return savedAmount;
//		}
//	}

//	@Override
//	public Student getWalletDetail() {
//		try {
//			StudentWallet studentWallet = studentRepository.findByStudentId(getWalletDetail();
//			if(studentWallet != null){
//				return studentRepository.findById(getWalletDetail());
//				studentWallet.getAmount() = studentWallet.getAmount() + amount;
//				studentRepository.save(studentWallet);
//			}else{
//				throw new StudentNotFoundException("No data found..!!");
//			}
//
//			List<Student> studentList = studentRepository.findAll();
//			if(!studentList.isEmpty()) {
//				return studentRepository.findAll();
//			} else {
//				throw new StudentNotFoundException("No data found..!!");
//			}
//	}
}
