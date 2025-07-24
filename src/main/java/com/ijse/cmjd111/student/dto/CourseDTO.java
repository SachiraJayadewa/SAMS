package com.ijse.cmjd111.student.dto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CourseDTO {
    private final SimpleIntegerProperty course_id;
    private final SimpleStringProperty course_name;
    private final SimpleStringProperty description;

    public CourseDTO(int course_id, String course_name, String description) {
        this.course_id = new SimpleIntegerProperty(course_id);
        this.course_name = new SimpleStringProperty(course_name);
        this.description = new SimpleStringProperty(description);
    }

    public int getCourse_id() {
        return course_id.get();
    }

    public void setCourse_id(int id) {
        this.course_id.set(id);
    }

    public String getCourse_name() {
        return course_name.get();
    }

    public void setCourse_name(String name) {
        this.course_name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String desc) {
        this.description.set(desc);
    }

    // For TableView cell value factory binding
    public SimpleIntegerProperty courseIdProperty() {
        return course_id;
    }

    public SimpleStringProperty courseNameProperty() {
        return course_name;
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }
}
