package com.devcollege.services;

import com.devcollege.entities.Course;
import com.devcollege.exceptions.CourseNotFoundException;
import com.devcollege.exceptions.InvalidInputException;
import com.devcollege.exceptions.NoSuchElementFoundException;

import java.util.List;


public interface CourseService {
	
	public Course addCourse(Course course);

	public String updateCourseById(Course course, String courseId) throws NoSuchElementFoundException;

	public String deleteCourse(String courseId) throws InvalidInputException;
	
	public Course getCourseById(String courseId) throws CourseNotFoundException;
	
	public List<Course> getAllCourses() throws CourseNotFoundException;

}
