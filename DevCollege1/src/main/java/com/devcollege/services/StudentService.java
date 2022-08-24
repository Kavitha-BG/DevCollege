package com.devcollege.services;

import java.util.List;

import com.devcollege.payloads.StudentDto;

public interface StudentService {
	
	public StudentDto addStudentDetail(StudentDto studenDto);
	
	public StudentDto updateStudentDetail(StudentDto student, String studentId);
	
	public void deleteStudentDetail(String studentId);
	
	public StudentDto getStudentDetail(String studentid);
	
	public List<StudentDto> getAllStudentDetail();
	
	
	public StudentDto addWalletAmount(Float walletAmount);
	
	public void getWalletDetail();
	
}
