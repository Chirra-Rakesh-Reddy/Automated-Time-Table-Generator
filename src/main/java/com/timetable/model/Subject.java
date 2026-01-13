package com.timetable.model;

import java.sql.Timestamp;

public class Subject {
    private int subjectId;
    private String subjectName;
    private String subjectCode;
    private int deptId;
    private int hoursPerWeek;
    private Timestamp createdAt;
    
    // Getters and Setters
    public int getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public String getSubjectCode() {
        return subjectCode;
    }
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    public int getDeptId() {
        return deptId;
    }
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    public int getHoursPerWeek() {
        return hoursPerWeek;
    }
    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName=" + subjectName + ", subjectCode=" + subjectCode
				+ ", deptId=" + deptId + ", hoursPerWeek=" + hoursPerWeek + ", createdAt=" + createdAt + "]";
	}
}