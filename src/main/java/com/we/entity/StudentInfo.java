package com.we.entity;

import javax.persistence.*;

/**
 * Created by aman.ullah on 3/11/2019.
 */
@Entity
@Table(name = "student_info")
public class StudentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "course_title")
    private String courseTitle;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "semester_info")
    private String semesterInfo;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private String createdOn;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSemesterInfo() {
        return semesterInfo;
    }

    public void setSemesterInfo(String semesterInfo) {
        this.semesterInfo = semesterInfo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", deptName='" + deptName + '\'' +
                ", semesterInfo='" + semesterInfo + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
