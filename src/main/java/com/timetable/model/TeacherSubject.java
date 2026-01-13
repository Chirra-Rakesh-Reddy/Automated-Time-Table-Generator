package com.timetable.model;

public class TeacherSubject {
    private int teacherId;
    private int subjectId;
    
    // Constructors
    public TeacherSubject() {}
    
    public TeacherSubject(int teacherId, int subjectId) {
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }
    
    // Getters and Setters
    public int getTeacherId() {
        return teacherId;
    }
    
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    
    public int getSubjectId() {
        return subjectId;
    }
    
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

	@Override
	public String toString() {
		return "TeacherSubject [teacherId=" + teacherId + ", subjectId=" + subjectId + "]";
	}
}