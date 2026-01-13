package com.timetable.dao;

import com.timetable.model.Batch;
import com.timetable.model.Classroom;
import com.timetable.model.Subject;
import com.timetable.model.Teacher;
import com.timetable.model.Timetable;
import com.timetable.model.TimetableEntry;
import com.timetable.model.TimetableSlot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimetableDAO {
    public boolean addTimetableEntry(TimetableEntry entry) {
        String sql = "INSERT INTO timetable_entries (slot_id, teacher_id, subject_id, batch_id, room_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, entry.getSlotId());
            stmt.setInt(2, entry.getTeacherId());
            stmt.setInt(3, entry.getSubjectId());
            stmt.setInt(4, entry.getBatchId());
            stmt.setInt(5, entry.getRoomId());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        entry.setEntryId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    
    }
    public List<TimetableEntry> getAllTimetableEntries() {
        List<TimetableEntry> entries = new ArrayList<>();
        String sql = "SELECT te.*, ts.day_of_week, ts.start_time, ts.end_time, " +
                     "t.teacher_name, s.subject_name, s.subject_code, b.batch_name, " +
                     "r.room_number, r.building " +
                     "FROM timetable_entries te " +
                     "JOIN timetable_slots ts ON te.slot_id = ts.slot_id " +
                     "JOIN teachers t ON te.teacher_id = t.teacher_id " +
                     "JOIN subjects s ON te.subject_id = s.subject_id " +
                     "JOIN batches b ON te.batch_id = b.batch_id " +
                     "JOIN classrooms r ON te.room_id = r.room_id " +
                     "ORDER BY ts.day_of_week, ts.start_time";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                TimetableEntry entry = new TimetableEntry();
                entry.setEntryId(rs.getInt("entry_id"));
                entry.setSlotId(rs.getInt("slot_id"));
                entry.setTeacherId(rs.getInt("teacher_id"));
                entry.setSubjectId(rs.getInt("subject_id"));
                entry.setBatchId(rs.getInt("batch_id"));
                entry.setRoomId(rs.getInt("room_id"));
                entry.setCreatedAt(rs.getTimestamp("created_at"));
                
                // Set slot details
                TimetableSlot slot = new TimetableSlot();
                slot.setDayOfWeek(rs.getString("day_of_week"));
                slot.setStartTime(rs.getTime("start_time"));
                slot.setEndTime(rs.getTime("end_time"));
                entry.setSlot(slot);
                
                // Set teacher details
                Teacher teacher = new Teacher();
                teacher.setTeacherName(rs.getString("teacher_name"));
                entry.setTeacher(teacher);
                
                // Set subject details
                Subject subject = new Subject();
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setSubjectCode(rs.getString("subject_code"));
                entry.setSubject(subject);
                
                // Set batch details
                Batch batch = new Batch();
                batch.setBatchName(rs.getString("batch_name"));
                entry.setBatch(batch);
                
                // Set classroom details
                Classroom classroom = new Classroom();
                classroom.setRoomNumber(rs.getString("room_number"));
                classroom.setBuilding(rs.getString("building"));
                entry.setClassroom(classroom);
                
                entries.add(entry);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entries;
    }
    
    public List<TimetableEntry> getTimetableByBatch(int batchId) {
        List<TimetableEntry> entries = new ArrayList<>();
        String sql = "SELECT te.*, ts.day_of_week, ts.start_time, ts.end_time, " +
                     "t.teacher_name, s.subject_name, s.subject_code, b.batch_name, " +
                     "r.room_number, r.building " +
                     "FROM timetable_entries te " +
                     "JOIN timetable_slots ts ON te.slot_id = ts.slot_id " +
                     "JOIN teachers t ON te.teacher_id = t.teacher_id " +
                     "JOIN subjects s ON te.subject_id = s.subject_id " +
                     "JOIN batches b ON te.batch_id = b.batch_id " +
                     "JOIN classrooms r ON te.room_id = r.room_id " +
                     "WHERE te.batch_id = ? " +
                     "ORDER BY ts.day_of_week, ts.start_time";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, batchId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TimetableEntry entry = new TimetableEntry();
                    entry.setEntryId(rs.getInt("entry_id"));
                    entry.setSlotId(rs.getInt("slot_id"));
                    entry.setTeacherId(rs.getInt("teacher_id"));
                    entry.setSubjectId(rs.getInt("subject_id"));
                    entry.setBatchId(rs.getInt("batch_id"));
                    entry.setRoomId(rs.getInt("room_id"));
                    entry.setCreatedAt(rs.getTimestamp("created_at"));
                    
                    // Set slot details
                    TimetableSlot slot = new TimetableSlot();
                    slot.setDayOfWeek(rs.getString("day_of_week"));
                    slot.setStartTime(rs.getTime("start_time"));
                    slot.setEndTime(rs.getTime("end_time"));
                    entry.setSlot(slot);
                    
                    // Set teacher details
                    Teacher teacher = new Teacher();
                    teacher.setTeacherName(rs.getString("teacher_name"));
                    entry.setTeacher(teacher);
                    
                    // Set subject details
                    Subject subject = new Subject();
                    subject.setSubjectName(rs.getString("subject_name"));
                    subject.setSubjectCode(rs.getString("subject_code"));
                    entry.setSubject(subject);
                    
                    // Set batch details
                    Batch batch = new Batch();
                    batch.setBatchName(rs.getString("batch_name"));
                    entry.setBatch(batch);
                    
                    // Set classroom details
                    Classroom classroom = new Classroom();
                    classroom.setRoomNumber(rs.getString("room_number"));
                    classroom.setBuilding(rs.getString("building"));
                    entry.setClassroom(classroom);
                    
                    entries.add(entry);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entries;
    }
    
    public List<TimetableEntry> getTimetableByTeacher(int teacherId) {
        List<TimetableEntry> entries = new ArrayList<>();
        String sql = "SELECT te.*, ts.day_of_week, ts.start_time, ts.end_time, " +
                     "t.teacher_name, s.subject_name, s.subject_code, b.batch_name, " +
                     "r.room_number, r.building " +
                     "FROM timetable_entries te " +
                     "JOIN timetable_slots ts ON te.slot_id = ts.slot_id " +
                     "JOIN teachers t ON te.teacher_id = t.teacher_id " +
                     "JOIN subjects s ON te.subject_id = s.subject_id " +
                     "JOIN batches b ON te.batch_id = b.batch_id " +
                     "JOIN classrooms r ON te.room_id = r.room_id " +
                     "WHERE te.teacher_id = ? " +
                     "ORDER BY ts.day_of_week, ts.start_time";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, teacherId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TimetableEntry entry = new TimetableEntry();
                    entry.setEntryId(rs.getInt("entry_id"));
                    entry.setSlotId(rs.getInt("slot_id"));
                    entry.setTeacherId(rs.getInt("teacher_id"));
                    entry.setSubjectId(rs.getInt("subject_id"));
                    entry.setBatchId(rs.getInt("batch_id"));
                    entry.setRoomId(rs.getInt("room_id"));
                    entry.setCreatedAt(rs.getTimestamp("created_at"));
                    
                    // Set slot details
                    TimetableSlot slot = new TimetableSlot();
                    slot.setDayOfWeek(rs.getString("day_of_week"));
                    slot.setStartTime(rs.getTime("start_time"));
                    slot.setEndTime(rs.getTime("end_time"));
                    entry.setSlot(slot);
                    
                    // Set teacher details
                    Teacher teacher = new Teacher();
                    teacher.setTeacherName(rs.getString("teacher_name"));
                    entry.setTeacher(teacher);
                    
                    // Set subject details
                    Subject subject = new Subject();
                    subject.setSubjectName(rs.getString("subject_name"));
                    subject.setSubjectCode(rs.getString("subject_code"));
                    entry.setSubject(subject);
                    
                    // Set batch details
                    Batch batch = new Batch();
                    batch.setBatchName(rs.getString("batch_name"));
                    entry.setBatch(batch);
                    
                    // Set classroom details
                    Classroom classroom = new Classroom();
                    classroom.setRoomNumber(rs.getString("room_number"));
                    classroom.setBuilding(rs.getString("building"));
                    entry.setClassroom(classroom);
                    
                    entries.add(entry);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entries;
    }
    
    public List<TimetableEntry> getTimetableByClassroom(int roomId) {
        List<TimetableEntry> entries = new ArrayList<>();
        String sql = "SELECT te.*, ts.day_of_week, ts.start_time, ts.end_time, " +
                     "t.teacher_name, s.subject_name, s.subject_code, b.batch_name, " +
                     "r.room_number, r.building " +
                     "FROM timetable_entries te " +
                     "JOIN timetable_slots ts ON te.slot_id = ts.slot_id " +
                     "JOIN teachers t ON te.teacher_id = t.teacher_id " +
                     "JOIN subjects s ON te.subject_id = s.subject_id " +
                     "JOIN batches b ON te.batch_id = b.batch_id " +
                     "JOIN classrooms r ON te.room_id = r.room_id " +
                     "WHERE te.room_id = ? " +
                     "ORDER BY ts.day_of_week, ts.start_time";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, roomId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TimetableEntry entry = new TimetableEntry();
                    entry.setEntryId(rs.getInt("entry_id"));
                    entry.setSlotId(rs.getInt("slot_id"));
                    entry.setTeacherId(rs.getInt("teacher_id"));
                    entry.setSubjectId(rs.getInt("subject_id"));
                    entry.setBatchId(rs.getInt("batch_id"));
                    entry.setRoomId(rs.getInt("room_id"));
                    entry.setCreatedAt(rs.getTimestamp("created_at"));
                    
                    // Set slot details
                    TimetableSlot slot = new TimetableSlot();
                    slot.setDayOfWeek(rs.getString("day_of_week"));
                    slot.setStartTime(rs.getTime("start_time"));
                    slot.setEndTime(rs.getTime("end_time"));
                    entry.setSlot(slot);
                    
                    // Set teacher details
                    Teacher teacher = new Teacher();
                    teacher.setTeacherName(rs.getString("teacher_name"));
                    entry.setTeacher(teacher);
                    
                    // Set subject details
                    Subject subject = new Subject();
                    subject.setSubjectName(rs.getString("subject_name"));
                    subject.setSubjectCode(rs.getString("subject_code"));
                    entry.setSubject(subject);
                    
                    // Set batch details
                    Batch batch = new Batch();
                    batch.setBatchName(rs.getString("batch_name"));
                    entry.setBatch(batch);
                    
                    // Set classroom details
                    Classroom classroom = new Classroom();
                    classroom.setRoomNumber(rs.getString("room_number"));
                    classroom.setBuilding(rs.getString("building"));
                    entry.setClassroom(classroom);
                    
                    entries.add(entry);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entries;
    }
    
    public boolean deleteTimetableForBatch(int batchId) {
        String sql = "DELETE FROM timetable_entries WHERE batch_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, batchId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isBatchBusy(int slotId, int batchId) {
        String sql = "SELECT COUNT(*) FROM timetable_entries WHERE slot_id = ? AND batch_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, slotId);
            stmt.setInt(2, batchId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isTeacherBusy(int slotId, int teacherId) {
        String sql = "SELECT COUNT(*) FROM timetable_entries WHERE slot_id = ? AND teacher_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, slotId);
            stmt.setInt(2, teacherId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isClassroomBusy(int slotId, int roomId) {
        String sql = "SELECT COUNT(*) FROM timetable_entries WHERE slot_id = ? AND room_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, slotId);
            stmt.setInt(2, roomId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Timetable> getAllGeneratedTimetables() throws SQLException {
        List<Timetable> timetables = new ArrayList<>();
        String sql = "SELECT DISTINCT b.batch_id, b.batch_name, d.dept_name, " +
                     "COUNT(te.entry_id) as entry_count " +
                     "FROM timetable_entries te " +
                     "JOIN batches b ON te.batch_id = b.batch_id " +
                     "JOIN departments d ON b.dept_id = d.dept_id " +
                     "GROUP BY b.batch_id, b.batch_name, d.dept_name " +
                     "ORDER BY b.batch_name";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Timetable timetable = new Timetable();
                timetable.setBatchId(rs.getInt("batch_id"));
                timetable.setBatchName(rs.getString("batch_name"));
                timetable.setDepartment(rs.getString("dept_name"));
                timetable.setEntryCount(rs.getInt("entry_count"));
                timetables.add(timetable);
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return timetables;
    }

    	public List<TimetableEntry> getTimetableDetails(int batchId) throws SQLException {
    	    List<TimetableEntry> entries = new ArrayList<>();
    	    String sql = "SELECT te.*, ts.day_of_week, ts.start_time, ts.end_time, " +
    	                 "t.teacher_name, s.subject_name, s.subject_code, b.batch_name, " +
    	                 "r.room_number, r.building " +
    	                 "FROM timetable_entries te " +
    	                 "JOIN timetable_slots ts ON te.slot_id = ts.slot_id " +
    	                 "JOIN teachers t ON te.teacher_id = t.teacher_id " +
    	                 "JOIN subjects s ON te.subject_id = s.subject_id " +
    	                 "JOIN batches b ON te.batch_id = b.batch_id " +
    	                 "JOIN classrooms r ON te.room_id = r.room_id " +
    	                 "WHERE te.batch_id = ? " +
    	                 "ORDER BY ts.day_of_week, ts.start_time";
    	    
    	    try (Connection conn = DBConnection.getConnection();
    	         PreparedStatement stmt = conn.prepareStatement(sql)) {
    	        
    	        stmt.setInt(1, batchId);
    	        ResultSet rs = stmt.executeQuery();
    	        
    	        while (rs.next()) {
    	            TimetableEntry entry = mapTimetableEntry(rs);
    	            entries.add(entry);
    	        }
    	    } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    return entries;
    	}

    	private TimetableEntry mapTimetableEntry(ResultSet rs) throws SQLException {
    	    TimetableEntry entry = new TimetableEntry();
    	    entry.setEntryId(rs.getInt("entry_id"));
    	    entry.setSlotId(rs.getInt("slot_id"));
    	    entry.setTeacherId(rs.getInt("teacher_id"));
    	    entry.setSubjectId(rs.getInt("subject_id"));
    	    entry.setBatchId(rs.getInt("batch_id"));
    	    entry.setRoomId(rs.getInt("room_id"));
    	    entry.setCreatedAt(rs.getTimestamp("created_at"));
    	    
    	    // Set slot details
    	    TimetableSlot slot = new TimetableSlot();
    	    slot.setDayOfWeek(rs.getString("day_of_week"));
    	    slot.setStartTime(rs.getTime("start_time"));
    	    slot.setEndTime(rs.getTime("end_time"));
    	    entry.setSlot(slot);
    	    
    	    // Set teacher details
    	    Teacher teacher = new Teacher();
    	    teacher.setTeacherName(rs.getString("teacher_name"));
    	    entry.setTeacher(teacher);
    	    
    	    // Set subject details
    	    Subject subject = new Subject();
    	    subject.setSubjectName(rs.getString("subject_name"));
    	    subject.setSubjectCode(rs.getString("subject_code"));
    	    entry.setSubject(subject);
    	    
    	    // Set batch details
    	    Batch batch = new Batch();
    	    batch.setBatchName(rs.getString("batch_name"));
    	    entry.setBatch(batch);
    	    
    	    // Set classroom details
    	    Classroom classroom = new Classroom();
    	    classroom.setRoomNumber(rs.getString("room_number"));
    	    classroom.setBuilding(rs.getString("building"));
    	    entry.setClassroom(classroom);
    	    
    	    return entry;
    	}	
}