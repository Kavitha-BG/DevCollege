package com.devcollege.repositories;

import com.devcollege.entities.Course;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    @Query(nativeQuery= true,value="SELECT * FROM enrollments where course_id=:courseId")
    public String getStatusByCourseId(@Param("courseId") String courseId);
}
