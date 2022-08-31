package com.devcollege.entities;

import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Parameter;

import javax.validation.constraints.*;

import com.devcollege.sequencestylegenerator.SequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
	@GenericGenerator(
			name = "course_seq",
			strategy = "com.devcollege.sequencestylegenerator.SequenceIdGenerator",
			parameters = {
					@Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CRS"),
					@Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
			} )
	@Column(name="course_id", updatable = false, nullable=false)
	private String courseId;
	
	@Column(name="course_name",nullable=false,length=100)
	@NotBlank(message = "Course name should not be null")
	private String courseName;
	
	@Column(name="course_description",nullable=false)
	@NotNull
	private String courseDescription;
	
	@Column(name="no_of_slot",nullable=false)
	@NotNull
//	@Digits(message="NumberOfSlot should contain 10 digits.", fraction = 0, integer = 10)
	@Positive(message= "NoOfSlot must be numeric value")
	private int noOfSlot;
	
	@Column(name="course_fee",nullable=false,length=100)
	@NotNull
	@Positive(message= "Course Fee must be numeric value")
	private float courseFee;
	
	@Column(name="course_duration",nullable=false,length=100)
	@NotNull
	@Positive(message= "Course Duration must be numeric value")
	private int courseDuration;
	
	@Column(name="course_tag",nullable=false,length=100)
	@NotBlank(message = "Course Tag should be B.E, B.Tech, Diploma, M.E, M.Tech., M.Phil., MS, BBA, BCom, BSc, MSc, BCA, MCA, LLB, MBBS, MBA")
	private String courseTag;

	public Course() {
	}

	public Course(String courseId, String courseName, String courseDescription, int noOfSlot,
				  Float courseFee, int courseDuration, String courseTag) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.noOfSlot = noOfSlot;
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

	public int getNoOfSlot() {
		return noOfSlot;
	}

	public void setNoOfSlot(int noOfSlot) {
		this.noOfSlot = noOfSlot;
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
				+ courseDescription + ", noOfSlot=" + noOfSlot + ", courseFee="
				+ courseFee + ", courseDuration=" + courseDuration + ", courseTag=" + courseTag + "]";
	}

	@OneToMany(targetEntity = Enrollment.class, cascade = CascadeType.ALL)
	@JoinColumn(
			name = "courseId",
			referencedColumnName = "course_Id"
	)
	private List<Enrollment> enrollmentList;
}