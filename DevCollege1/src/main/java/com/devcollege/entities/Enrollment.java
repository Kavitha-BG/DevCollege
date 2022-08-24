package com.devcollege.entities;

import java.util.Date;

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
@Table(name="enrollments")
@NoArgsConstructor
@Getter
@Setter
public class Enrollment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private String enrollmentId;
	
	@Column(name="course_start_datetime",nullable=false,length=10)
	private Date courseStartDatetime;
	
	@Column(name="course_end_datetime",nullable=false,length=10)
	private Date courseEndDatetime;
	
//	private String studentId= studentId;
//	private String courseId= courseId;
	
}
