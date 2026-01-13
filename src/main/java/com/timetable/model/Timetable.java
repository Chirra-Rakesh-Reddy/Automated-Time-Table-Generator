package com.timetable.model;

public class Timetable {
    private int batchId;
    private String batchName;
    private String department;
    private int entryCount;
    
    // Getters and setters
    public int getBatchId() { return batchId; }
    public void setBatchId(int batchId) { this.batchId = batchId; }
    public String getBatchName() { return batchName; }
    public void setBatchName(String batchName) { this.batchName = batchName; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public int getEntryCount() { return entryCount; }
    public void setEntryCount(int entryCount) { this.entryCount = entryCount; }
    
    
	@Override
	public String toString() {
		return "Timetable [batchId=" + batchId + ", batchName=" + batchName + ", department=" + department
				+ ", entryCount=" + entryCount + "]";
	}
}