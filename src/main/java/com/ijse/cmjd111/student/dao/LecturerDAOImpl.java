package com.ijse.cmjd111.student.dao;

import com.ijse.cmjd111.student.dao.LecturerDAO;
import com.ijse.cmjd111.student.model.Lecturer;
import com.ijse.cmjd111.student.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LecturerDAOImpl implements LecturerDAO {

    @Override
    public void saveLecturer(Lecturer lecturer) {
        String sql = "INSERT INTO Lecturer(name, department, contact, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lecturer.getName());
            stmt.setString(2, lecturer.getDepartment());
            stmt.setString(3, lecturer.getContact());
            stmt.setString(4, lecturer.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();
        String sql = "SELECT * FROM Lecturer";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Lecturer lecturer = new Lecturer();
                lecturer.setLecturerId(rs.getInt("lecturer_id"));
                lecturer.setName(rs.getString("name"));
                lecturer.setDepartment(rs.getString("department"));
                lecturer.setContact(rs.getString("contact"));
                lecturer.setEmail(rs.getString("email"));
                lecturers.add(lecturer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecturers;
    }

    @Override
    public void updateLecturer(Lecturer lecturer) {
        String sql = "UPDATE Lecturer SET name=?, department=?, contact=?, email=? WHERE lecturer_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lecturer.getName());
            stmt.setString(2, lecturer.getDepartment());
            stmt.setString(3, lecturer.getContact());
            stmt.setString(4, lecturer.getEmail());
            stmt.setInt(5, lecturer.getLecturerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLecturer(int id) {
        String sql = "DELETE FROM Lecturer WHERE lecturer_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
