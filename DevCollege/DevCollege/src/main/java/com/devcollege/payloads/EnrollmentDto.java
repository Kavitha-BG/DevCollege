package com.devcollege.payloads;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

public class EnrollmentDto {

    private String enrolId;
    private String courseId;
    private String studentId;
    @DateTimeFormat(pattern = "YYYY/MM/DD HH:mm:ss")
    private Date courseStartDatetime;
    @DateTimeFormat(pattern = "YYYY/MM/DD HH:mm:ss")
    private Date courseEndDatetime;
    private String courseStatus;
    private String courseLink ;
    private String studentLink;

    public String getEnrolId() {
        return enrolId;
    }

    public void setEnrolId(String enrolId) {
        this.enrolId = enrolId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }

    public String getStudentLink() {
        return studentLink;
    }

    public void setStudentLink(String studentLink) {
        this.studentLink = studentLink;
    }

    public EnrollmentDto() {
    }

    public EnrollmentDto(String enrolId, String courseId, String studentId, Date courseStartDatetime,
                         Date courseEndDatetime, String courseStatus, String courseLink, String studentLink) {
        this.enrolId = enrolId;
        this.courseId = courseId;
        this.studentId = studentId;
        this.courseStartDatetime = courseStartDatetime;
        this.courseEndDatetime = courseEndDatetime;
        this.courseStatus = courseStatus;
        this.courseLink = courseLink;
        this.studentLink = studentLink;
    }
}
