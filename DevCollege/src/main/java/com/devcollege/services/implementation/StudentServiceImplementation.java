package com.devcollege.services.implementation;

import java.util.List;


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
	public Student addStudentDetail(Student student) {
		if(student.getStudentName().isEmpty()|| student.getStudentContactNo().length()==0 ||
				student.getHighestQualification().isEmpty() || student.getWalletAmount().isNaN()) {
			throw new EmptyInputException("Student Id: "+ " doesn't exist.");
		} else {
			Student savedStudent = studentRepository.save(student);
			return savedStudent;
		}
	}

	@Override
	public String updateStudentDetail(Student student, String studentId) {
		Student updateStudent = studentRepository.findById(studentId).orElse(null);
		if(updateStudent != null) {
			updateStudent.setStudentName(student.getStudentName());
			updateStudent.setHighestQualification(student.getHighestQualification());
			updateStudent.setStudentContactNo(student.getStudentContactNo());
			studentRepository.save(updateStudent);
			return "Successfully updated student detail for course: " + studentId;
		}else {
			throw new NoSuchElementFoundException(studentId + " doesn't exist.");
		}
	}

	@Override
	public void deleteStudentDetail(String studentId) {
		Student student = studentRepository.findById(studentId).orElse(null);
		if(student != null) {
//			studentRepository.deleteById(studentId);
			studentRepository.delete(student);
//			return "Successfully deleted student detail for course: " + studentId;
		}else {
			throw new InvalidInputException(studentId + " doesn't exist.");
		}
	}

	@Override
	public List<Student> getAllStudentDetail() throws StudentNotFoundException  {
		List<Student> studentList = studentRepository.findAll();
		if(!studentList.isEmpty()) {
			return studentRepository.findAll();
		} else {
			throw new StudentNotFoundException("No data found..!!");
		}
	}

	@Override
	public Student getStudentDetail(String studentId) {
		Student student = studentRepository.findByStudentId(studentId);
		if(student != null) {
			return student;
		} else {
			throw new StudentNotFoundException("Student Id: " + studentId + " does not exist.");
		}
	}

	@Override
	public Student addWalletAmount(Float walletAmount) {
		return null;
	}

	@Override
	public void getWalletDetail() {

	}
}
