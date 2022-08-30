package com.devcollege.services;

import java.util.List;
import java.util.Map;

import com.devcollege.entities.Student;
import com.devcollege.entities.StudentWallet;
import com.devcollege.exceptions.StudentNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


public interface StudentService {
	
	public Student addStudent(Student student);

	public String updateStudentById(Student student, String studentId);
	
	public void deleteStudent(String studentId);
	
	public Student getStudentById(String studentId);
	
	public List<Student> getAllStudents() throws StudentNotFoundException;

	public Float addWalletAmount(String studentId,StudentWallet studentWallet);

	public Map<String,String> getWalletDetail(java.lang.String studentId);

}