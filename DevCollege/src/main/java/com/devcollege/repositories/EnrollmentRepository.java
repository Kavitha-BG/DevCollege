package com.devcollege.repositories;

import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {

	Enrollment findByEnrollId(String enrollId);
	
}
