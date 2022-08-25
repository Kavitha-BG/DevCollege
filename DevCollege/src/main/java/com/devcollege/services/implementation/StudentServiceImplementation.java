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
			throw new EmptyInputException();
		}
		Student savedStudent = studentRepository.save(student);
		return savedStudent;
	}

	@Override
	public Student updateStudentDetail(Student student, String studentId) {
		return student;
//		Student student1 = this.studentRepository.findByStudentId(studentId)
//				.orElseThrow(() -> new ResourceNotFoundException("student","studentId","studentId"));
//		
//		student1.setStudentId(student1.getStudentId());
//		student1.setStudentName(student1.getStudentName());
//		student1.setHighestQualification(student1.getHighestQualification());
//		student1.setStudentContactNo(student1.getStudentContactNo());
//		student1.setWalletAmount(student1.getWalletAmount());
//		
//		Student updatedStudent = this.studentRepository.save(student1);
//		return updatedStudent;
	}

	@Override
	public void deleteStudentDetail(String studentId) {
//		Student student = this.studentRepository.findByStudentId(studentId)
//				.orElseThrow(()-> new ResourceNotFoundException("student","studentId","studentId"));
//		
//		this.studentRepository.delete(student);
	}

	@Override
	public Student getStudentDetail(String studentId) {
		return null;
////		Student student = this.studentRepository.findByStudentId(studentId)
////				.orElseThrow(()-> new ResourceNotFoundException("student","studentId","studentId"));
////		
////		return student;
	}

	@Override
	public List<Student> getAllStudentDetail() {
		return null;
//		List<Student> studentList = studentRepository.findAll();
//		
//		if(studentList.isEmpty()) {
//			throw new NoSuchElementFound("601","No data Found");
//		}
		
//		//List<Student> student = students.stream().map(student->this.student).collect(Collectors.toList());
//		return students;
	}

	@Override
	public Student addWalletAmount(Float walletAmount) {
		return null;
	}

	@Override
	public void getWalletDetail() {

	}


}
