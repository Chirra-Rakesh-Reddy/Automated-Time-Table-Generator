package com.timetable.dao;

import com.timetable.model.TeacherSubject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherSubjectDAO {
    public boolean addTeacherSubject(TeacherSubject teacherSubject) {
        String sql = "INSERT INTO teacher_subjects (teacher_id, subject_id) VALUES (?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, teacherSubject.getTeacherId());
            stmt.setInt(2, teacherSubject.getSubjectId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean removeTeacherSubject(int teacherId, int subjectId) {
        String sql = "DELETE FROM teacher_subjects WHERE teacher_id = ? AND subject_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, teacherId);
            stmt.setInt(2, subjectId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<TeacherSubject> getAllTeacherSubjects() {
        List<TeacherSubject> teacherSubjects = new ArrayList<>();
        String sql = "SELECT * FROM teacher_subjects";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                TeacherSubject ts = new TeacherSubject();
                ts.setTeacherId(rs.getInt("teacher_id"));
                ts.setSubjectId(rs.getInt("subject_id"));
                teacherSubjects.add(ts);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return teacherSubjects;
    }
}