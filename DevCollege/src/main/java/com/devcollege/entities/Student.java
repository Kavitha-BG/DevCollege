package com.devcollege.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;


//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Parameter;
//
//import com.devcollege.entities.sequencestylegenerator.StudentSequenceIdGenerator;

@Entity
@Table(name="students")
public class Student {
	
	@Id	
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
//	@GenericGenerator(
//			name = "student_seq", 
//			strategy = "com.devcollege.entities.sequencestylegenerator.StudentSequenceIdGenerator",
//			parameters = {
//				@Parameter(name = StudentSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
//	            @Parameter(name = StudentSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "STD"),
//	            @Parameter(name = StudentSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") 
//} )
	
	@Column(name="student_id", updatable = false, nullable=false)
	private String studentId;
	
	
	@Column(name="student_name",nullable=false,length=50)
	@NotNull(message = "Name must not be null")
	private String studentName;
	
	@Column(name="highest_qualification",nullable=false)
	@NotNull(message = "Name must not be null")
	private String highestQualification;
	
	@Column(name="student_contact_no",nullable=false,length=10)
	@Size(min = 10, max = 10, message = "Contact number must be 10-digit ")
	@NotNull
	private String studentContactNo;
	
	@Column(name="wallet_amount",nullable=false,length=10)
	@NotNull(message = "Name must not be null")
	@Positive
	private Float walletAmount;
	
	public Student() {
		
	}
	
	public Student(@NotNull String studentId,@NotNull String studentName,@NotNull String highestQualification,@NotNull String studentContactNo,
			Float walletAmount) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.highestQualification = highestQualification;
		this.studentContactNo = studentContactNo;
		this.walletAmount = walletAmount;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}

	public String getStudentContactNo() {
		return studentContactNo;
	}

	public void setStudentContactNo(String studentContactNo) {
		this.studentContactNo = studentContactNo;
	}

	public Float getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(Float walletAmount) {
		this.walletAmount = walletAmount;
	}
	

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", highestQualification="
				+ highestQualification + ", studentContactNo=" + studentContactNo + ", walletAmount=" + walletAmount
				+ "]";
	}
	
	
	
}
