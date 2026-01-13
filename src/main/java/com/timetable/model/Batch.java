package com.timetable.model;

import java.sql.Timestamp;

public class Batch {
    private int batchId;
    private String batchName;
    private int deptId;
    private String academicYear;
    private Timestamp createdAt;
    
    // Getters and Setters
    public int getBatchId() {
        return batchId;
    }
    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }
    public String getBatchName() {
        return batchName;
    }
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
    public int getDeptId() {
        return deptId;
    }
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    public String getAcademicYear() {
        return academicYear;
    }
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", batchName=" + batchName + ", deptId=" + deptId + ", academicYear="
				+ academicYear + ", createdAt=" + createdAt + "]";
	}
}