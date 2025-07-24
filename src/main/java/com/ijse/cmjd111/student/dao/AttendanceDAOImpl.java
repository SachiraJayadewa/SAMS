package com.ijse.cmjd111.student.dao;

import com.ijse.cmjd111.student.model.Attendance;
import com.ijse.cmjd111.student.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAOImpl implements AttendanceDAO {

    @Override
    public void markAttendance(Attendance attendance) {
        String sql = "INSERT INTO attendance (student_id, schedule_id, status, marked_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, attendance.getStudentId());
            stmt.setInt(2, attendance.getScheduleId());
            stmt.setString(3, attendance.getStatus());
            stmt.setDate(4, Date.valueOf(attendance.getMarkedDate()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Attendance> getAttendanceBySchedule(int scheduleId) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE schedule_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, scheduleId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Attendance att = new Attendance();
                att.setAttendanceId(rs.getInt("attendance_id"));
                att.setStudentId(rs.getInt("student_id"));
                att.setScheduleId(rs.getInt("schedule_id"));
                att.setStatus(rs.getString("status"));
                att.setMarkedDate(rs.getDate("marked_date").toLocalDate());
                list.add(att);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Attendance> getAttendanceByStudent(int studentId) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Attendance att = new Attendance();
                att.setAttendanceId(rs.getInt("attendance_id"));
                att.setStudentId(rs.getInt("student_id"));
                att.setScheduleId(rs.getInt("schedule_id"));
                att.setStatus(rs.getString("status"));
                att.setMarkedDate(rs.getDate("marked_date").toLocalDate());
                list.add(att);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void deleteAttendance(int attendanceId) {
        String sql = "DELETE FROM attendance WHERE attendance_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, attendanceId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
