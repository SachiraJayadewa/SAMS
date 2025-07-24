package com.ijse.cmjd111.student.model;

public class Course {
    private int course_id;
    private String course_name;
    private String description;

    public Course() {}

    public Course(int course_id, String course_name, String description) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.description = description;
    }

    public Course(String course_name, String description) {
        this.course_name = course_name;
        this.description = description;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

