package com.devcollege.repositories;

import com.devcollege.entities.Course;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

	Course findByCourseId(String courseId);

    @Query(nativeQuery = true, value = "Select course_status from enrollments where student_id := 'Allocated'")
    default int isCourseAllocated(String courseId, String courseStatus) {
        return 0;
    }
}
