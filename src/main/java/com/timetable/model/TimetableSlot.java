package com.timetable.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

public class TimetableSlot {
    private int slotId;
    private String dayOfWeek;
    private Time startTime;
    private Time endTime;
    private Timestamp createdAt;
    
    // Getters and Setters
    public int getSlotId() {
        return slotId;
    }
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
    public String getDayOfWeek() {
        return dayOfWeek;
    }
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public Time getStartTime() {
        return startTime;
    }
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
    public Time getEndTime() {
        return endTime;
    }
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
	@Override
	public String toString() {
		return "TimetableSlot [slotId=" + slotId + ", dayOfWeek=" + dayOfWeek + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", createdAt=" + createdAt + "]";
	}
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    TimetableSlot that = (TimetableSlot) o;
	    return Objects.equals(dayOfWeek, that.dayOfWeek) &&
	           Objects.equals(startTime, that.startTime) &&
	           Objects.equals(endTime, that.endTime);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(dayOfWeek, startTime, endTime);
	}
	
}

