package com.timetable.model;

public class Student {
    private int studentId;
    private String studentName;
    private int batchId;
    private String batchName;
    private int userId;
    
    // Getters and setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public int getBatchId() { return batchId; }
    public void setBatchId(int batchId) { this.batchId = batchId; }
    public String getBatchName() { return batchName; }
    public void setBatchName(String batchName) { this.batchName = batchName; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}