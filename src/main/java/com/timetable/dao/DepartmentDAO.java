package com.timetable.dao;

import com.timetable.model.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    public boolean addDepartment(Department department) {
        String sql = "INSERT INTO departments (dept_name) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, department.getDeptName());
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        department.setDeptId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments ORDER BY dept_name";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Department dept = new Department();
                dept.setDeptId(rs.getInt("dept_id"));
                dept.setDeptName(rs.getString("dept_name"));
                dept.setCreatedAt(rs.getTimestamp("created_at"));
                departments.add(dept);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return departments;
    }
    
    public Department getDepartmentById(int deptId) {
        String sql = "SELECT * FROM departments WHERE dept_id = ?";
        Department dept = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, deptId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dept = new Department();
                    dept.setDeptId(rs.getInt("dept_id"));
                    dept.setDeptName(rs.getString("dept_name"));
                    dept.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dept;
    }
    
    public boolean updateDepartment(Department department) {
        String sql = "UPDATE departments SET dept_name = ? WHERE dept_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, department.getDeptName());
            stmt.setInt(2, department.getDeptId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteDepartment(int deptId) {
        String sql = "DELETE FROM departments WHERE dept_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, deptId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}