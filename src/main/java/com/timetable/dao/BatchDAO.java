package com.timetable.dao;

import com.timetable.model.Batch;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BatchDAO {
    public boolean addBatch(Batch batch) {
        String sql = "INSERT INTO batches (batch_name, dept_id, academic_year) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, batch.getBatchName());
            stmt.setInt(2, batch.getDeptId());
            stmt.setString(3, batch.getAcademicYear());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        batch.setBatchId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Batch> getAllBatches() {
        List<Batch> batches = new ArrayList<>();
        String sql = "SELECT b.*, d.dept_name FROM batches b JOIN departments d ON b.dept_id = d.dept_id ORDER BY b.batch_name";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Batch batch = new Batch();
                batch.setBatchId(rs.getInt("batch_id"));
                batch.setBatchName(rs.getString("batch_name"));
                batch.setDeptId(rs.getInt("dept_id"));
                batch.setAcademicYear(rs.getString("academic_year"));
                batch.setCreatedAt(rs.getTimestamp("created_at"));
                batches.add(batch);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return batches;
    }
    
    public Batch getBatchById(int batchId) {
        String sql = "SELECT b.*, d.dept_name FROM batches b JOIN departments d ON b.dept_id = d.dept_id WHERE b.batch_id = ?";
        Batch batch = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, batchId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    batch = new Batch();
                    batch.setBatchId(rs.getInt("batch_id"));
                    batch.setBatchName(rs.getString("batch_name"));
                    batch.setDeptId(rs.getInt("dept_id"));
                    batch.setAcademicYear(rs.getString("academic_year"));
                    batch.setCreatedAt(rs.getTimestamp("created_at"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return batch;
    }
    
    public boolean updateBatch(Batch batch) {
        String sql = "UPDATE batches SET batch_name = ?, dept_id = ?, academic_year = ? WHERE batch_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, batch.getBatchName());
            stmt.setInt(2, batch.getDeptId());
            stmt.setString(3, batch.getAcademicYear());
            stmt.setInt(4, batch.getBatchId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteBatch(int batchId) {
        String sql = "DELETE FROM batches WHERE batch_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, batchId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Batch> getBatchesByDepartment(int deptId) {
        List<Batch> batches = new ArrayList<>();
        String sql = "SELECT * FROM batches WHERE dept_id = ? ORDER BY batch_name";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, deptId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Batch batch = new Batch();
                    batch.setBatchId(rs.getInt("batch_id"));
                    batch.setBatchName(rs.getString("batch_name"));
                    batch.setDeptId(rs.getInt("dept_id"));
                    batch.setAcademicYear(rs.getString("academic_year"));
                    batch.setCreatedAt(rs.getTimestamp("created_at"));
                    batches.add(batch);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return batches;
    }
}