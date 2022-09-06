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
	public String updateStudentDetail(Student student, String studentId) {
			
		Student updateStudent = studentRepository.findById(studentId).orElse(null);
		
		if(updateStudent != null) {
			updateStudent.setStudentName(student.getStudentName());
			updateStudent.setHighestQualification(student.getHighestQualification());
			updateStudent.setStudentContactNo(student.getStudentContactNo());
			
			studentRepository.save(updateStudent);
			return "Successfully updated student detail for course: " + studentId;
		}else {
			throw new NoSuchElementFoundException(studentId + " doesn't exist.");
		}
		
		
//		Student updatedStudent = studentRepository.save(updateStudent);
//		return updatedStudent;
	}

	@Override
	public String deleteStudentDetail(String studentId) {
		
		Student student = studentRepository.findById(studentId).orElse(null);
		if(student != null) {
			studentRepository.deleteById(studentId);
			studentRepository.delete(student);
			return "Successfully deleted student detail for course: " + studentId;
		}else {
			throw new InvalidInputException(studentId + " doesn't exist.");
		}
//				.orElseThrow(()-> new InvalidInputException());
//		this.studentRepository.delete(student);

	}

	@Override
	public Student getStudentDetail(String studentId) {
		
//		Optional<Student> student = studentRepository.findById(studentId); 
//		if(!studentId.equals(studentId)) {
//			System.out.println("<Student-Id> does not exist.");
//		}
			
		Student student = studentRepository.findByStudentId(studentId);
		
		if(student != null) {
			return student;
		} else {
//			throw new StudentNotFoundException("Student Id: " + studentId + " does not exist."); 
		}
//		.orElseThrow(() -> new StudentNotFoundException("<Student-Id> does not exist.", studentId));
//		  
//		  
//		Student student = studentRepository.findByStudentId(studentId)
//				.orElseThrow(()-> new ResourceNotFoundException("student","studentId","studentId"));
//		
		return student;
	}

	@Override
	public List<Student> getAllStudentDetail() throws StudentNotFoundException  {
		
//		this.studentRepository.findAll();
//		if()
		
		List<Student> studentList = studentRepository.findAll();
		
		if(studentList != null) {
			return studentRepository.findAll();
//			System.out.println("No data Found");
		} else {
			throw new StudentNotFoundException("No data found..!!");

		}
		
//		return studentRepository.findAll();
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
