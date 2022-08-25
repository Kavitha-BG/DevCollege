package com.devcollege.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="courses")
public class Course {
	
	@Id	
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="course_id", updatable = false, nullable=false)
	private String courseId;
	
	@Column(name="course_name",nullable=false,length=100)
	@NotNull
	private String courseName;
	
	@Column(name="course_description",nullable=false)
	@NotNull
	private String courseDescription;
	
	@Column(name="no_of_registration_allowed",nullable=false,length=100)
	@NotNull
	private int noOfRegistrationAllowed;
	
	@Column(name="course_fee",nullable=false,length=100)
	@NotNull
	private float courseFee;
	
	@Column(name="course_duration",nullable=false,length=100)
	@NotNull
	private int courseDuration;
	
	@Column(name="course_tag",nullable=false,length=100)
	@NotNull
	private String courseTag;

	public Course(String courseId, String courseName, String courseDescription, int noOfRegistrationAllowed,
			float courseFee, int courseDuration, String courseTag) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.noOfRegistrationAllowed = noOfRegistrationAllowed;
		this.courseFee = courseFee;
		this.courseDuration = courseDuration;
		this.courseTag = courseTag;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public int getNoOfRegistrationAllowed() {
		return noOfRegistrationAllowed;
	}

	public void setNoOfRegistrationAllowed(int noOfRegistrationAllowed) {
		this.noOfRegistrationAllowed = noOfRegistrationAllowed;
	}

	public float getCourseFee() {
		return courseFee;
	}

	public void setCourseFee(float courseFee) {
		this.courseFee = courseFee;
	}

	public int getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(int courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getCourseTag() {
		return courseTag;
	}

	public void setCourseTag(String courseTag) {
		this.courseTag = courseTag;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDescription="
				+ courseDescription + ", noOfRegistrationAllowed=" + noOfRegistrationAllowed + ", courseFee="
				+ courseFee + ", courseDuration=" + courseDuration + ", courseTag=" + courseTag + "]";
	}
	
	
	
	
}
