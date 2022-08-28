package com.devcollege.services;

import java.util.List;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.StudentNotFoundException;


public interface StudentService {
	
	public Student addStudent(Student student);

	public String updateStudentById(Student student, String studentId);
	
	public void deleteStudent(String studentId);
	
	public Student getStudentById(String studentId);
	
	public List<Student> getAllStudent() throws StudentNotFoundException;
	
	
//	public Student addWalletAmount(Float amount);
//
//	public Student getWalletDetail();
//
}
