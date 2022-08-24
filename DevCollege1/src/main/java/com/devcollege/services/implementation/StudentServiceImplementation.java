package com.devcollege.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcollege.entities.Student;
import com.devcollege.payloads.StudentDto;
import com.devcollege.repositories.StudentRepository;
import com.devcollege.services.StudentService;
import com.devcollege.exceptions.*;

@Service
public class StudentServiceImplementation implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	private StudentDto studentDto;
	
	@Override
	public StudentDto addStudentDetail(StudentDto studenDto) {
		Student student = this.convertDtoToStudent(studentDto);
		Student savedStudent = this.studentRepository.save(student);
		return this.convertStudentToDto(savedStudent);
	}

	@Override
	public StudentDto updateStudentDetail(StudentDto studentDto, String studentId) {
		Student student = this.studentRepository.findByStudentId(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("student","studentId","studentId"));
		
		student.setStudentId(studentDto.getStudentId());
		student.setStudentName(studentDto.getStudentName());
		student.setHighestQualification(studentDto.getHighestQualification());
		student.setStudentContactNo(studentDto.getStudentContactNo());
		student.setWalletAmount(studentDto.getWalletAmount());
		
		Student updatedStudent = this.studentRepository.save(student);
		StudentDto StudentDto1 = this.convertStudentToDto(updatedStudent);
		return StudentDto1;
	}

	@Override
	public void deleteStudentDetail(String studentId) {
		Student student = this.studentRepository.findByStudentId(studentId)
				.orElseThrow(()-> new ResourceNotFoundException("student","studentId","studentId"));
		
		this.studentRepository.delete(student);
	}

	@Override
	public StudentDto getStudentDetail(String studentId) {
		Student student = this.studentRepository.findByStudentId(studentId)
				.orElseThrow(()-> new ResourceNotFoundException("student","studentId","studentId"));
		
		return this.convertStudentToDto(student);
	}

	@Override
	public List<StudentDto> getAllStudentDetail() {
		List<Student> students = this.studentRepository.findAll();
		
		List<StudentDto> studentDto = students.stream().map(student->this.convertStudentToDto(student)).collect(Collectors.toList());
		return studentDto;
	}

	@Override
	public StudentDto addWalletAmount(Float walletAmount) {
		return null;
	}

	@Override
	public void getWalletDetail() {

	}
	
	//converting StudentDto to student
	private StudentDto convertStudentToDto(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setStudentId(student.getStudentId());
		studentDto.setStudentName(student.getStudentName());
		studentDto.setHighestQualification(student.getHighestQualification());
		studentDto.setStudentContactNo(student.getStudentContactNo());
		studentDto.setWalletAmount(student.getWalletAmount());
		return studentDto;
	}
	
	//converting student to StudentDto
	private Student convertDtoToStudent(StudentDto studenDto) {
		Student student = new Student();
		student.setStudentId(studentDto.getStudentId());
		student.setStudentName(studentDto.getStudentName());
		student.setHighestQualification(studentDto.getHighestQualification());
		student.setStudentContactNo(studentDto.getStudentContactNo());
		student.setWalletAmount(studentDto.getWalletAmount());
		return student;
	}

}
