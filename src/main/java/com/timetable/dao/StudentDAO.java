package com.timetable.dao;

import com.timetable.model.Student;
import java.sql.*;

public class StudentDAO {
    public Student getStudentByUserId(int userId) throws SQLException {
        String sql = "SELECT s.*, b.batch_name FROM students s " +
                     "JOIN batches b ON s.batch_id = b.batch_id " +
                     "WHERE s.user_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setBatchId(rs.getInt("batch_id"));
                student.setBatchName(rs.getString("batch_name"));
                student.setUserId(rs.getInt("user_id"));
                return student;
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
}