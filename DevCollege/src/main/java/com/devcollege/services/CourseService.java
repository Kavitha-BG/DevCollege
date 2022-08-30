package com.devcollege.services;

import com.devcollege.entities.Course;

import java.util.List;


public interface CourseService {
	
	public Course addCourse(Course course);

	public String updateCourseById(Course course, String courseId);
	
	public void deleteCourse(String courseId);
	
	public Course getCourseById(String courseId);
	
	public List<Course> getAllCourses();

}
