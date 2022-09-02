package com.devcollege.services.implementation;

import com.devcollege.entities.Course;
import com.devcollege.entities.Enrollment;
import com.devcollege.entities.Student;
import com.devcollege.exceptions.*;
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
			throw new NoDataFoundException("errorMessage");
		} else {
			Course savedCourse = courseRepository.save(course);
			return savedCourse;
		}
	}

	@Override
	public String updateCourseById(Course course, String courseId) throws NoSuchElementFoundException{
		Course updateCourse = courseRepository.findById(courseId).orElse(null);
		if (updateCourse != null) {

			updateCourse.setCourseName(course.getCourseName());
			updateCourse.setCourseDescription(course.getCourseDescription());
			updateCourse.setCourseFee(course.getCourseFee());
			updateCourse.setCourseDuration(course.getCourseDuration());
			updateCourse.setNoOfSlot(course.getNoOfSlot());
			updateCourse.setCourseTag(course.getCourseTag());

			courseRepository.save(updateCourse);
			return "Successfully updated course detail for course: " + courseId;
		} else {
			throw new NotFoundException("courseId","", courseId);
		}
	}

	@Override
	public String deleteCourse(String courseId) throws InvalidInputException {
		Course course = courseRepository.findById(courseId).orElse(null);
		if (course != null) {
			//int courseAllocationCounter = 0;
			int courseAllocation = courseRepository.isCourseAllocated(courseId, "Allocated") ;

			if(courseAllocation > 0) {
				return "You cannot delete a course "+ courseId
				+ " , It is allocated to some student ";
			} else {
				courseRepository.deleteById(courseId);
				return "Successfully deleted course detail for course: " + courseId;
			}
		} else {
				throw new NotFoundException("courseId","", courseId);
		}
	}

	@Override
	public Course getCourseById(String courseId) throws CourseNotFoundException {
//		Course course = courseRepository.findById(courseId).orElse(null);
//		if (course != null) {
//			return course;
//		} else {
//			throw new CourseNotFoundException("Course Id : " + courseId + " does not exist.");
//		}

		Course course = courseRepository.findById(courseId).orElseThrow(()
				-> new NotFoundException("courseId","", courseId));
		return course;
	}


	@Override
	public List<Course> getAllCourses() throws NoDataFoundException {
		List<Course> courseList = courseRepository.findAll();
		if (!courseList.isEmpty()) {
			return courseRepository.findAll();
		} else {
			throw new NoDataFoundException("errorMessage");
		}
	}

}
