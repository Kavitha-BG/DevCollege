package com.devcollege.repositories;

import com.devcollege.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
//	List<Student> findByStudentId(String studentId);
	Course findByCourseId(String courseId);
	
}
