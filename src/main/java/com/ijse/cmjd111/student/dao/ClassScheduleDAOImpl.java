package com.ijse.cmjd111.student.dao;

import com.ijse.cmjd111.student.model.ClassSchedule;
import com.ijse.cmjd111.student.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassScheduleDAOImpl implements ClassScheduleDAO {

    @Override
    public void saveClassSchedule(ClassSchedule schedule) {
        String sql = "INSERT INTO class_schedule(subject, course_id, lecturer_id, scheduled_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, schedule.getSubject());
            stmt.setInt(2, schedule.getCourseId());
            stmt.setInt(3, schedule.getLecturerId());
            stmt.setDate(4, Date.valueOf(schedule.getDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ClassSchedule> getAllSchedules() {
        List<ClassSchedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM class_schedule";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ClassSchedule schedule = new ClassSchedule();
                schedule.setScheduleId(rs.getInt("schedule_id"));
                schedule.setSubject(rs.getString("subject"));
                schedule.setCourseId(rs.getInt("course_id"));
                schedule.setLecturerId(rs.getInt("lecturer_id"));
                schedule.setDate(rs.getDate("scheduled_date").toLocalDate());
                schedules.add(schedule);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    @Override
    public void updateClassSchedule(ClassSchedule schedule) {
        String sql = "UPDATE class_schedule SET subject=?, course_id=?, lecturer_id=?, scheduled_date=? WHERE schedule_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, schedule.getSubject());
            stmt.setInt(2, schedule.getCourseId());
            stmt.setInt(3, schedule.getLecturerId());
            stmt.setDate(4, Date.valueOf(schedule.getDate()));
            stmt.setInt(5, schedule.getScheduleId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClassSchedule(int id) {
        String sql = "DELETE FROM class_schedule WHERE schedule_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
