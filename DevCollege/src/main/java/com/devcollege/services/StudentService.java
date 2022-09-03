package com.devcollege.services;

import java.util.List;
import java.util.Map;

import com.devcollege.entities.Student;
import com.devcollege.entities.StudentWallet;
import com.devcollege.exceptions.CourseNotFoundException;
import com.devcollege.exceptions.StudentNotFoundException;


public interface StudentService {
	
	public Student addStudent(Student student);

	public Student updateStudentById(Student student, String studentId);
	
	public String deleteStudent(String studentId) throws CourseNotFoundException;
	
	public Student getStudentById(String studentId) throws StudentNotFoundException;
	
	public List<Student> getAllStudents() throws StudentNotFoundException;

	public Float addWalletAmount(String studentId,StudentWallet studentWallet);

	public Map<String,String> getWalletDetail(java.lang.String studentId);

}