package com.timetable.dao;

import com.timetable.model.TimetableSlot;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimetableSlotDAO {
    public boolean addTimetableSlot(TimetableSlot slot) {
        String sql = "INSERT INTO timetable_slots (day_of_week, start_time, end_time) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, slot.getDayOfWeek());
            stmt.setTime(2, slot.getStartTime());
            stmt.setTime(3, slot.getEndTime());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        slot.setSlotId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<TimetableSlot> getAllSlots() {
        List<TimetableSlot> slots = new ArrayList<>();
        String sql = "SELECT * FROM timetable_slots ORDER BY day_of_week, start_time";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                TimetableSlot slot = new TimetableSlot();
                slot.setSlotId(rs.getInt("slot_id"));
                slot.setDayOfWeek(rs.getString("day_of_week"));
                slot.setStartTime(rs.getTime("start_time"));
                slot.setEndTime(rs.getTime("end_time"));
                slot.setCreatedAt(rs.getTimestamp("created_at"));
                slots.add(slot);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return slots;
    }
    
    public TimetableSlot getSlotById(int slotId) {
        String sql = "SELECT * FROM timetable_slots WHERE slot_id = ?";
        TimetableSlot slot = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, slotId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    slot = new TimetableSlot();
                    slot.setSlotId(rs.getInt("slot_id"));
                    slot.setDayOfWeek(rs.getString("day_of_week"));
                    slot.setStartTime(rs.getTime("start_time"));
                    slot.setEndTime(rs.getTime("end_time"));
                    slot.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return slot;
    }
    
    public boolean updateTimetableSlot(TimetableSlot slot) {
        String sql = "UPDATE timetable_slots SET day_of_week = ?, start_time = ?, end_time = ? WHERE slot_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, slot.getDayOfWeek());
            stmt.setTime(2, slot.getStartTime());
            stmt.setTime(3, slot.getEndTime());
            stmt.setInt(4, slot.getSlotId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteTimetableSlot(int slotId) {
        String sql = "DELETE FROM timetable_slots WHERE slot_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, slotId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}