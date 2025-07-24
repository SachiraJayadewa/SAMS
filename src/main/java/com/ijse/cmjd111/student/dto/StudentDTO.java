package com.ijse.cmjd111.student.dto;

import javafx.beans.property.*;

public class StudentDTO {
    private final SimpleIntegerProperty student_id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty regNumber;
    private final SimpleStringProperty course;
    private final SimpleStringProperty contact;

    public StudentDTO(int student_id, String name, String regNumber, String course, String contact) {
        this.student_id = new SimpleIntegerProperty(student_id);
        this.name = new SimpleStringProperty(name);
        this.regNumber = new SimpleStringProperty(regNumber);
        this.course = new SimpleStringProperty(course);
        this.contact = new SimpleStringProperty(contact);
    }

    // --- Getters
    public int getStudent_id() {
        return student_id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getRegNumber() {
        return regNumber.get();
    }

    public String getCourse() {
        return course.get();
    }

    public String getContact() {
        return contact.get();
    }

    // --- Setters
    public void setStudent_id(int id) {
        this.student_id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setRegNumber(String regNumber) {
        this.regNumber.set(regNumber);
    }

    public void setCourse(String course) {
        this.course.set(course);
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    // --- JavaFX Properties (required for TableView)
    public IntegerProperty student_idProperty() {
        return student_id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty regNumberProperty() {
        return regNumber;
    }

    public StringProperty courseProperty() {
        return course;
    }

    public StringProperty contactProperty() {
        return contact;
    }
}




