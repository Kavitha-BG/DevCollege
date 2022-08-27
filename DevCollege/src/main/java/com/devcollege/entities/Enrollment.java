package com.devcollege.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="enrollments")
public class Enrollment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private String enrollmentId;
	
	@Column(name="course_start_datetime",nullable=false,length=10)
	private Date courseStartDatetime;
	
	@Column(name="course_end_datetime",nullable=false,length=10)
	private Date courseEndDatetime;

	public Enrollment() {
	}

	public Enrollment(String enrollmentId, Date courseStartDatetime, Date courseEndDatetime) {
		super();
		this.enrollmentId = enrollmentId;
		this.courseStartDatetime = courseStartDatetime;
		this.courseEndDatetime = courseEndDatetime;
	}

	public String getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(String enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public Date getCourseStartDatetime() {
		return courseStartDatetime;
	}

	public void setCourseStartDatetime(Date courseStartDatetime) {
		this.courseStartDatetime = courseStartDatetime;
	}

	public Date getCourseEndDatetime() {
		return courseEndDatetime;
	}

	public void setCourseEndDatetime(Date courseEndDatetime) {
		this.courseEndDatetime = courseEndDatetime;
	}

	@Override
	public String toString() {
		return "Enrollment [enrollmentId=" + enrollmentId + ", courseStartDatetime=" + courseStartDatetime
				+ ", courseEndDatetime=" + courseEndDatetime + "]";
	}
	
//	@ManyToOne()
//	@JoinColumn(name = "student_id")
//	private List<Student> students = new ArrayList<>();
//	
//	@ManyToOne
//	@JoinColumn(name = "course_id")
//	private List<Course> courses = new  ArrayList<>();
		
	
	
	
	
}
