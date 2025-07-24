package com.ijse.cmjd111.student.model;

public class Lecturer {
    private int lecturerId;
    private String name;
    private String department;
    private String contact;
    private String email; // new field

    // Constructors
    public Lecturer() {}

    public Lecturer(int lecturerId, String name, String department, String contact, String email) {
        this.lecturerId = lecturerId;
        this.name = name;
        this.department = department;
        this.contact = contact;
        this.email = email;
    }

    // Getters and Setters
    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}



