package com.devcollege.services.implementation;

import com.devcollege.entities.Course;
import com.devcollege.exceptions.*;
import com.devcollege.repositories.CourseRepository;
import com.devcollege.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public String updateCourseById(Course course, String courseId) throws NotFoundException {
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
	public Map<String,String> deleteCourse(String courseId) throws NotFoundException {
		Course course = courseRepository.findById(courseId).orElseThrow(()
				-> new NotFoundException("courseId", "" ,courseId));

		String getStatus = courseRepository.getStatusByCourseId(courseId);
		if (getStatus != null) {
			if (courseRepository.getStatusByCourseId(courseId).equalsIgnoreCase("Allocated") ||
					courseRepository.getStatusByCourseId(courseId).equalsIgnoreCase("InProgress")) {
				Map<String, String> msg = new HashMap<String, String>();
				msg.put("Failed to delete course details", courseId);
				msg.put("It is allocated to some student ", "");
				return msg;
			}

			if (courseRepository.getStatusByCourseId(courseId).equalsIgnoreCase("Completed") ||
					courseRepository.getStatusByCourseId(courseId).equalsIgnoreCase("Cancelled")) {
				this.courseRepository.delete(course);
				Map<String, String> message = new HashMap<String, String>();
				message.put("Successfully deleted course details :", courseId);
				return message;
			}
		}
		this.courseRepository.delete(course);
		Map<String,String> message = new HashMap<String,String>();
		message.put("Successfully deleted course details :",courseId);
		return message;
	}

	@Override
	public Course getCourseById(String courseId) throws NotFoundException {
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
