package com.devcollege.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.devcollege.entities.Student;
import com.devcollege.payloads.StudentDto;
import com.devcollege.repositories.StudentRepository;
import com.devcollege.services.StudentService;
import com.devcollege.entities.*;

public class StudentServiceImplementation implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentDto studentDto;
	
	@Override
	public void addStudentDetail(Student student) {
		studentRepository.save(student);
	}

	@Override
	public StudentDto updateStudentDetail(StudentDto student, String studentId) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentDetail(String studentId) {
		studentRepository.save(studentId);
	}

	@Override
	public void getStudentDetail(String studentId) {
		studentRepository.findOne(studentId);
	}

	@Override
	public List<StudentDto> getAllStudentDetail() {
		List<StudentDto> students = new ArrayList<>();
		studentRepository.findAll()
		.forEach(students::add);
		return students;
	}

	@Override
	public StudentDto addWalletAmount(Float walletAmount) {
		return null;
	}

	@Override
	public void getWalletDetail() {

	}
	
	//converting StudentDto to student
	private StudentDto convertDtoToStudent(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setStudentId(student.getStudentId());
		studentDto.setStudentName(student.getStudentName());
		studentDto.setHighestQualification(student.getHighestQualification());
		studentDto.setStudentContactNo(student.getStudentContactNo());
		studentDto.setWalletAmount(student.getWalletAmount());
		return studentDto;
	}
	
	

}
