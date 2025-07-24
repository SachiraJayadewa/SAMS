package com.ijse.cmjd111.student.dto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LecturerDTO {
    private final SimpleIntegerProperty lecturerId;
    private final SimpleStringProperty name;
    private final SimpleStringProperty department;
    private final SimpleStringProperty contact;
    private final SimpleStringProperty email;

    public LecturerDTO(int lecturerId, String name, String department, String contact, String email) {
        this.lecturerId = new SimpleIntegerProperty(lecturerId);
        this.name = new SimpleStringProperty(name);
        this.department = new SimpleStringProperty(department);
        this.contact = new SimpleStringProperty(contact);
        this.email = new SimpleStringProperty(email);
    }

    public int getLecturerId() {
        return lecturerId.get();
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId.set(lecturerId);
    }

    public SimpleIntegerProperty lecturerIdProperty() {
        return lecturerId;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public SimpleStringProperty departmentProperty() {
        return department;
    }

    public String getContact() {
        return contact.get();
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    public SimpleStringProperty contactProperty() {
        return contact;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }
}
