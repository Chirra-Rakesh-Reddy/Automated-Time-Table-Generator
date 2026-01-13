package com.timetable.dao;

import com.timetable.model.Subject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    public boolean addSubject(Subject subject) {
        String sql = "INSERT INTO subjects (subject_name, subject_code, dept_id, hours_per_week) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, subject.getSubjectName());
            stmt.setString(2, subject.getSubjectCode());
            stmt.setInt(3, subject.getDeptId());
            stmt.setInt(4, subject.getHoursPerWeek());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        subject.setSubjectId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT s.*, d.dept_name FROM subjects s JOIN departments d ON s.dept_id = d.dept_id ORDER BY s.subject_name";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setSubjectCode(rs.getString("subject_code"));
                subject.setDeptId(rs.getInt("dept_id"));
                subject.setHoursPerWeek(rs.getInt("hours_per_week"));
                subject.setCreatedAt(rs.getTimestamp("created_at"));
                subjects.add(subject);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return subjects;
    }
    
    public Subject getSubjectById(int subjectId) {
        String sql = "SELECT s.*, d.dept_name FROM subjects s JOIN departments d ON s.dept_id = d.dept_id WHERE s.subject_id = ?";
        Subject subject = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, subjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectName(rs.getString("subject_name"));
                    subject.setSubjectCode(rs.getString("subject_code"));
                    subject.setDeptId(rs.getInt("dept_id"));
                    subject.setHoursPerWeek(rs.getInt("hours_per_week"));
                    subject.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return subject;
    }
    
    public boolean updateSubject(Subject subject) {
        String sql = "UPDATE subjects SET subject_name = ?, subject_code = ?, dept_id = ?, hours_per_week = ? WHERE subject_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, subject.getSubjectName());
            stmt.setString(2, subject.getSubjectCode());
            stmt.setInt(3, subject.getDeptId());
            stmt.setInt(4, subject.getHoursPerWeek());
            stmt.setInt(5, subject.getSubjectId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteSubject(int subjectId) {
        String sql = "DELETE FROM subjects WHERE subject_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, subjectId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Subject> getSubjectsByDepartment(int deptId) {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subjects WHERE dept_id = ? ORDER BY subject_name";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, deptId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectName(rs.getString("subject_name"));
                    subject.setSubjectCode(rs.getString("subject_code"));
                    subject.setDeptId(rs.getInt("dept_id"));
                    subject.setHoursPerWeek(rs.getInt("hours_per_week"));
                    subject.setCreatedAt(rs.getTimestamp("created_at"));
                    subjects.add(subject);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return subjects;
    }
    
    public List<Subject> getSubjectsByTeacher(int teacherId) {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT s.* FROM subjects s JOIN teacher_subjects ts ON s.subject_id = ts.subject_id WHERE ts.teacher_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, teacherId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectName(rs.getString("subject_name"));
                    subject.setSubjectCode(rs.getString("subject_code"));
                    subject.setDeptId(rs.getInt("dept_id"));
                    subject.setHoursPerWeek(rs.getInt("hours_per_week"));
                    subject.setCreatedAt(rs.getTimestamp("created_at"));
                    subjects.add(subject);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return subjects;
    }
}