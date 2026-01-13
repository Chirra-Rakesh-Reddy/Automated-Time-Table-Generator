package com.timetable.dao;

import com.timetable.model.Subject;
import com.timetable.model.Teacher;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    public boolean addTeacher(Teacher teacher) {
        String sql = "INSERT INTO teachers (teacher_name, dept_id, user_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, teacher.getTeacherName());
            stmt.setInt(2, teacher.getDeptId());
            stmt.setInt(3, teacher.getUserId());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        teacher.setTeacherId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT t.*, d.dept_name FROM teachers t JOIN departments d ON t.dept_id = d.dept_id ORDER BY t.teacher_name";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(rs.getInt("teacher_id"));
                teacher.setTeacherName(rs.getString("teacher_name"));
                teacher.setDeptId(rs.getInt("dept_id"));
                teacher.setUserId(rs.getInt("user_id"));
                teacher.setCreatedAt(rs.getTimestamp("created_at"));
                teachers.add(teacher);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return teachers;
    }
    
    public Teacher getTeacherById(int teacherId) {
        String sql = "SELECT t.*, d.dept_name FROM teachers t JOIN departments d ON t.dept_id = d.dept_id WHERE t.teacher_id = ?";
        Teacher teacher = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, teacherId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    teacher = new Teacher();
                    teacher.setTeacherId(rs.getInt("teacher_id"));
                    teacher.setTeacherName(rs.getString("teacher_name"));
                    teacher.setDeptId(rs.getInt("dept_id"));
                    teacher.setUserId(rs.getInt("user_id"));
                    teacher.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return teacher;
    }
    
    public boolean updateTeacher(Teacher teacher) {
        String sql = "UPDATE teachers SET teacher_name = ?, dept_id = ? WHERE teacher_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, teacher.getTeacherName());
            stmt.setInt(2, teacher.getDeptId());
            stmt.setInt(3, teacher.getTeacherId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteTeacher(int teacherId) {
        String sql = "DELETE FROM teachers WHERE teacher_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, teacherId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Teacher> getTeachersByDepartment(int deptId) {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teachers WHERE dept_id = ? ORDER BY teacher_name";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, deptId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setTeacherId(rs.getInt("teacher_id"));
                    teacher.setTeacherName(rs.getString("teacher_name"));
                    teacher.setDeptId(rs.getInt("dept_id"));
                    teacher.setUserId(rs.getInt("user_id"));
                    teacher.setCreatedAt(rs.getTimestamp("created_at"));
                    teachers.add(teacher);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return teachers;
    }
    
    public boolean assignSubjectToTeacher(int teacherId, int subjectId) {
        String sql = "INSERT INTO teacher_subjects (teacher_id, subject_id) VALUES (?, ?)";
        
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
    
    public boolean removeSubjectFromTeacher(int teacherId, int subjectId) {
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
    
 // In TeacherDAO.java
    public Teacher getTeacherByUserId(int userId) {
        // Implement the logic to get a teacher by user ID
        // For example using JDBC:
        String sql = "SELECT * FROM teachers WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(rs.getInt("teacher_id"));
                teacher.setUserId(rs.getInt("user_id"));
                // Set other fields as needed
                return teacher;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Subject> getSubjectsByTeacher(int teacherId) {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT s.* FROM subjects s " +
                     "JOIN teacher_subjects ts ON s.subject_id = ts.subject_id " +
                     "WHERE ts.teacher_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setDeptId(rs.getInt("dept_id"));
                subject.setHoursPerWeek(rs.getInt("hours_per_week"));
                // Set other fields as needed
                subjects.add(subject);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return subjects;
    }

}