package com.timetable.model;

import java.sql.Timestamp;

public class Department {
    private int deptId;
    private String deptName;
    private Timestamp createdAt;
    
    // Getters and Setters
    public int getDeptId() {
        return deptId;
    }
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", createdAt=" + createdAt + "]";
	}
    
    
}