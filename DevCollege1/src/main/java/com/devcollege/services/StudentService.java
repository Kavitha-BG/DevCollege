package com.devcollege.services;

import java.util.List;
import com.devcollege.entities.Student;


public interface StudentService {
	
	public Student addStudentDetail(Student student);
	
	public Student updateStudentDetail(Student student, String studentId);
	
	public void deleteStudentDetail(String studentId);
	
	public Student getStudentDetail(String studentid);
	
	public List<Student> getAllStudentDetail();
	
	
	public Student addWalletAmount(Float walletAmount);
	
	public void getWalletDetail();
	
}
