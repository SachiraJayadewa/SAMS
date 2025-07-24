package com.ijse.cmjd111.student.model;

import java.time.LocalDate;

public class ClassSchedule {
    private int scheduleId;
    private String subject;
    private int courseId;
    private int lecturerId;
    private LocalDate date;

    public ClassSchedule() {}

    public ClassSchedule(int scheduleId, String subject, int courseId, int lecturerId, LocalDate date) {
        this.scheduleId = scheduleId;
        this.subject = subject;
        this.courseId = courseId;
        this.lecturerId = lecturerId;
        this.date = date;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

