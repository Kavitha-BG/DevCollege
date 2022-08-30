package com.devcollege.repositories;

import com.devcollege.entities.StudentWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcollege.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
//    StudentWallet findById(Student walletDetail);

//	List<Student> findByStudentId(String studentId);
	Student findByStudentId(String studentId);

}
