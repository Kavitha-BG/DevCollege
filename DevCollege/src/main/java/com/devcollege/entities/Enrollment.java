package com.devcollege.entities;

import com.devcollege.sequencestylegenerator.SequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enrollment_seq")
	@GenericGenerator(
			name = "enrollment_seq",
			strategy = "com.devcollege.sequencestylegenerator.SequenceIdGenerator",
			parameters = {
					@Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EN"),
					@Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
			} )
	@Column(name="enroll_id", updatable = false, nullable=false)
	private String enrollId;

	@DateTimeFormat(pattern = "YYYY/MM/DD HH:mm:ss")
	@Column(name="course_start_datetime",nullable=false)
	private Date courseStartDatetime;

	@DateTimeFormat(pattern = "YYYY/MM/DD HH:mm:ss")
	@Column(name="course_end_datetime",nullable=false)
	private Date courseEndDatetime;

	public Enrollment() {
	}

	public Enrollment(String enrollmentId, Date courseStartDatetime, Date courseEndDatetime) {
		super();
		this.enrollId = enrollmentId;
		this.courseStartDatetime = courseStartDatetime;
		this.courseEndDatetime = courseEndDatetime;
	}

	public String getEnrollmentId() {
		return enrollId;
	}

	public void setEnrollmentId(String enrollmentId) {
		this.enrollId = enrollmentId;
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
		return "Enrollment [enrollmentId=" + enrollId + ", courseStartDatetime=" + courseStartDatetime
				+ ", courseEndDatetime=" + courseEndDatetime + "]";
	}
	
}
