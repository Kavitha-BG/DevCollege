package com.devcollege.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="courses")
@NoArgsConstructor
@Getter
@Setter
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private String courseId;
	
	@Column(name="course_name",nullable=false,length=100)
	private String courseName;
	
	@Column(name="course_description",nullable=false)
	private String courseDescription;
	
	@Column(name="no_of_registration_allowed",nullable=false,length=10)
	private int noOfRegistrationAllowed;
	
	@Column(name="course_fee",nullable=false,length=10)
	private float courseFee;
	
	@Column(name="course_duration",nullable=false,length=10)
	private int courseDuration;
	
	@Column(name="course_tag",nullable=false,length=10)
	private String courseTag;
	
	
}
