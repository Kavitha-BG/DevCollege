package com.devcollege.repositories;

import com.devcollege.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {

//	Enrollment findByEnrolId(String enrolId);
	
}
