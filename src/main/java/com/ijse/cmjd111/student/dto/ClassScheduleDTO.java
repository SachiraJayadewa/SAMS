package com.ijse.cmjd111.student.dto;

import javafx.beans.property.*;

import java.time.LocalDate;

public class ClassScheduleDTO {
    private final SimpleIntegerProperty scheduleId;
    private final SimpleIntegerProperty courseId;
    private final SimpleIntegerProperty lecturerId;
    private final SimpleStringProperty subject;
    private final ObjectProperty<LocalDate> date;

    public ClassScheduleDTO(int scheduleId, int courseId, int lecturerId, String subject, LocalDate date) {
        this.scheduleId = new SimpleIntegerProperty(scheduleId);
        this.courseId = new SimpleIntegerProperty(courseId);
        this.lecturerId = new SimpleIntegerProperty(lecturerId);
        this.subject = new SimpleStringProperty(subject);
        this.date = new SimpleObjectProperty<>(date);
    }

    public int getScheduleId() {
        return scheduleId.get();
    }

    public SimpleIntegerProperty scheduleIdProperty() {
        return scheduleId;
    }

    public int getCourseId() {
        return courseId.get();
    }

    public SimpleIntegerProperty courseIdProperty() {
        return courseId;
    }

    public int getLecturerId() {
        return lecturerId.get();
    }

    public SimpleIntegerProperty lecturerIdProperty() {
        return lecturerId;
    }

    public String getSubject() {
        return subject.get();
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }
}

