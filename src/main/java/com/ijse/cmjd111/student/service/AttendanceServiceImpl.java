package com.ijse.cmjd111.student.service;

import com.ijse.cmjd111.student.dao.AttendanceDAO;
import com.ijse.cmjd111.student.dao.AttendanceDAOImpl;
import com.ijse.cmjd111.student.model.Attendance;
import com.ijse.cmjd111.student.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceDAO attendanceDAO;

    public AttendanceServiceImpl() {
        this.attendanceDAO = new AttendanceDAOImpl();
    }

    @Override
    public void markAttendance(Attendance attendance) {
        if (!isStudentExists(attendance.getStudentId())) {
            throw new IllegalArgumentException("Student ID " + attendance.getStudentId() + " does not exist.");
        }

        if (!isScheduleExists(attendance.getScheduleId())) {
            throw new IllegalArgumentException("Schedule ID " + attendance.getScheduleId() + " does not exist.");
        }

        attendanceDAO.markAttendance(attendance);
    }

    @Override
    public List<Attendance> getAttendanceBySchedule(int scheduleId) {
        return attendanceDAO.getAttendanceBySchedule(scheduleId);
    }

    @Override
    public List<Attendance> getAttendanceByStudent(int studentId) {
        return attendanceDAO.getAttendanceByStudent(studentId);
    }

    @Override
    public void deleteAttendance(int attendanceId) {
        attendanceDAO.deleteAttendance(attendanceId);
    }

    @Override
    public Map<String, Integer> getAttendanceSummaryBySchedule(int scheduleId) {
        return attendanceDAO.getAttendanceSummaryBySchedule(scheduleId);
    }

    // Helper methods to validate foreign keys
    private boolean isStudentExists(int studentId) {
        String sql = "SELECT student_id FROM student WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isScheduleExists(int scheduleId) {
        String sql = "SELECT schedule_id FROM class_schedule WHERE schedule_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, scheduleId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
