package com.timetable.model;

import java.sql.Timestamp;

public class Classroom {
    private int roomId;
    private String roomNumber;
    private String building;
    private int capacity;
    private Timestamp createdAt;
    
    // Getters and Setters
    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getBuilding() {
        return building;
    }
    public void setBuilding(String building) {
        this.building = building;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
	@Override
	public String toString() {
		return "Classroom [roomId=" + roomId + ", roomNumber=" + roomNumber + ", building=" + building + ", capacity="
				+ capacity + ", createdAt=" + createdAt + "]";
	}
}