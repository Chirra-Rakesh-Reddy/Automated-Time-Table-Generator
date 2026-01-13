package com.timetable.dao;

import com.timetable.model.Classroom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassroomDAO {
    public boolean addClassroom(Classroom classroom) {
        String sql = "INSERT INTO classrooms (room_number, building, capacity) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, classroom.getRoomNumber());
            stmt.setString(2, classroom.getBuilding());
            stmt.setInt(3, classroom.getCapacity());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        classroom.setRoomId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Classroom> getAllClassrooms() {
        List<Classroom> classrooms = new ArrayList<>();
        String sql = "SELECT * FROM classrooms ORDER BY building, room_number";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Classroom classroom = new Classroom();
                classroom.setRoomId(rs.getInt("room_id"));
                classroom.setRoomNumber(rs.getString("room_number"));
                classroom.setBuilding(rs.getString("building"));
                classroom.setCapacity(rs.getInt("capacity"));
                classroom.setCreatedAt(rs.getTimestamp("created_at"));
                classrooms.add(classroom);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classrooms;
    }
    
    public Classroom getClassroomById(int roomId) {
        String sql = "SELECT * FROM classrooms WHERE room_id = ?";
        Classroom classroom = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, roomId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    classroom = new Classroom();
                    classroom.setRoomId(rs.getInt("room_id"));
                    classroom.setRoomNumber(rs.getString("room_number"));
                    classroom.setBuilding(rs.getString("building"));
                    classroom.setCapacity(rs.getInt("capacity"));
                    classroom.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classroom;
    }
    
    public boolean updateClassroom(Classroom classroom) {
        String sql = "UPDATE classrooms SET room_number = ?, building = ?, capacity = ? WHERE room_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            
            stmt.setString(1, classroom.getRoomNumber());
            stmt.setString(2, classroom.getBuilding());
            stmt.setInt(3, classroom.getCapacity());
            stmt.setInt(4, classroom.getRoomId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteClassroom(int roomId) {
        String sql = "DELETE FROM classrooms WHERE room_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, roomId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}