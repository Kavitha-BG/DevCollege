package com.devcollege.services.implementation;

import com.devcollege.entities.Course;
import com.devcollege.exceptions.EmptyInputException;
import com.devcollege.exceptions.InvalidInputException;
import com.devcollege.exceptions.NoSuchElementFoundException;
import com.devcollege.exceptions.StudentNotFoundException;
import com.devcollege.repositories.CourseRepository;
import com.devcollege.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImplementation implements CourseService {
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course addCourse(Course course) {
		if (course.getCourseName().isEmpty() || course.getCourseDescription().isEmpty() || course.getCourseFee() == 0
				|| course.getCourseDuration() == 0 || course.getNoOfSlot() == 0 || course.getCourseTag().isEmpty()) {
			throw new EmptyInputException("Course Id: " + " doesn't exist.");
		} else {
			Course savedCourse = courseRepository.save(course);
			return savedCourse;
		}
	}

	@Override
	public String updateCourseById(Course course, String courseId) {
		Course updateCourse = courseRepository.findById(courseId).orElse(null);
		if (updateCourse != null) {
			updateCourse.setCourseName(course.getCourseName());
			updateCourse.setCourseDescription(course.getCourseDescription());
			updateCourse.setCourseFee(course.getCourseFee());
			updateCourse.setCourseDuration(course.getCourseDuration());
			updateCourse.setNoOfSlot(course.getNoOfSlot());
			updateCourse.setCourseTag(course.getCourseTag());
			courseRepository.save(updateCourse);
			return "Successfully updated student detail for course: " + courseId;
		} else {
			throw new NoSuchElementFoundException(courseId + " doesn't exist.");
		}
	}

	@Override
	public void deleteCourse(String courseId) {
		Course course = courseRepository.findById(courseId).orElse(null);
		if (course != null) {
			courseRepository.delete(course);
//			return "Successfully deleted student detail for course: " + studentId;
		} else {
			throw new InvalidInputException(courseId + " doesn't exist.");
		}
	}

	@Override
	public Course getCourseById(String courseId) {
		Course course = courseRepository.findByCourseId(courseId);
		if (course != null) {
			return course;
		} else {
			throw new StudentNotFoundException("Student Id: " + courseId + " does not exist.");
		}
	}


	@Override
	public List<Course> getAllCourses() throws StudentNotFoundException {
		List<Course> courseList = courseRepository.findAll();
		if (!courseList.isEmpty()) {
			return courseRepository.findAll();
		} else {
			throw new StudentNotFoundException("No data found..!!");
		}
	}

}
