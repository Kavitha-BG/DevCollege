package com.devcollege.services;

import java.util.List;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.StudentNotFoundException;


public interface StudentService {
	
	public Student addStudentDetail(Student student);
	
	public String updateStudentDetail(Student student, String studentId);
	
	public String deleteStudentDetail(String studentId);
	
	public Student getStudentDetail(String studentid);
	
	public List<Student> getAllStudentDetail() throws StudentNotFoundException;
	
	
	public Student addWalletAmount(Float walletAmount);
	
	public void getWalletDetail();
	
}
