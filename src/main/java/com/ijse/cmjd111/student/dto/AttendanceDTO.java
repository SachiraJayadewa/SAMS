package com.ijse.cmjd111.student.dto;

import java.time.LocalDate;

public class AttendanceDTO {
    private int attendanceId;
    private int studentId;
    private int scheduleId;
    private String status;
    private LocalDate markedDate;

    public AttendanceDTO() {}

    public AttendanceDTO(int attendanceId, int studentId, int scheduleId, String status, LocalDate markedDate) {
        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.scheduleId = scheduleId;
        this.status = status;
        this.markedDate = markedDate;
    }

    // Getters and Setters
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getMarkedDate() {
        return markedDate;
    }

    public void setMarkedDate(LocalDate markedDate) {
        this.markedDate = markedDate;
    }
}

