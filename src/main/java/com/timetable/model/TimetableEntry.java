package com.timetable.model;

import java.sql.Timestamp;

public class TimetableEntry {
    private int entryId;
    private int slotId;
    private int teacherId;
    private int subjectId;
    private int batchId;
    private int roomId;
    private Timestamp createdAt;
    
    // Relationships (for display purposes)
    private TimetableSlot slot;
    private Teacher teacher;
    private Subject subject;
    private Batch batch;
    private Classroom classroom;
    
    // Getters and Setters
    public int getEntryId() {
        return entryId;
    }
    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }
    public int getSlotId() {
        return slotId;
    }
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
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
    public int getBatchId() {
        return batchId;
    }
    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }
    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public TimetableSlot getSlot() {
        return slot;
    }
    public void setSlot(TimetableSlot slot) {
        this.slot = slot;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    public Batch getBatch() {
        return batch;
    }
    public void setBatch(Batch batch) {
        this.batch = batch;
    }
    public Classroom getClassroom() {
        return classroom;
    }
    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
	@Override
	public String toString() {
		return "TimetableEntry [entryId=" + entryId + ", slotId=" + slotId + ", teacherId=" + teacherId + ", subjectId="
				+ subjectId + ", batchId=" + batchId + ", roomId=" + roomId + ", createdAt=" + createdAt + ", slot="
				+ slot + ", teacher=" + teacher + ", subject=" + subject + ", batch=" + batch + ", classroom="
				+ classroom + "]";
	}
}