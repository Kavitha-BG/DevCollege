package com.devcollege.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devcollege.entities.Student;
import com.devcollege.payloads.StudentDto;

@Service
public interface StudentService {
	
	public void addStudentDetail(Student student);
	
	public StudentDto updateStudentDetail(StudentDto student, String studentId);
	
	public void deleteStudentDetail(String studentId);
	
	public void getStudentDetail(String studentid);
	
	public List<StudentDto> getAllStudentDetail();
	
	
	public StudentDto addWalletAmount(Float walletAmount);
	
	public void getWalletDetail();
	
}
