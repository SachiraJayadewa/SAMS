package com.ijse.cmjd111.student.controller;

import com.ijse.cmjd111.student.dto.CourseDTO;
import com.ijse.cmjd111.student.model.Course;
import com.ijse.cmjd111.student.service.CourseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class CourseController {

    @FXML private TextField courseNameField;
    @FXML private TextField descriptionField;

    @FXML private TableView<CourseDTO> courseTable;
    @FXML private TableColumn<CourseDTO, String> courseNameCol;
    @FXML private TableColumn<CourseDTO, String> descriptionCol;

    private final CourseService service = new CourseService();

    @FXML
    public void initialize() {
        courseNameCol.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
        descriptionCol.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        courseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                courseNameField.setText(newSelection.getCourse_name());
                descriptionField.setText(newSelection.getDescription());
            }
        });

        loadCourses();
    }

    private void loadCourses() {
        List<Course> courses = service.getAllCourses();
        List<CourseDTO> dtoList = new ArrayList<>();

        for (Course c : courses) {
            dtoList.add(new CourseDTO(
                    c.getCourse_id(),
                    c.getCourse_name(),
                    c.getDescription()
            ));
        }

        ObservableList<CourseDTO> observableList = FXCollections.observableArrayList(dtoList);
        courseTable.setItems(observableList);
    }

    @FXML
    private void addCourse() {
        String name = courseNameField.getText();
        String desc = descriptionField.getText();

        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Missing Course Name");
            alert.setContentText("Please enter a course name.");
            alert.showAndWait();
            return;
        }

        Course c = new Course(name, desc);
        service.addCourse(c);
        loadCourses();
        clearFields();
    }

    @FXML
    private void updateCourse() {
        CourseDTO dto = courseTable.getSelectionModel().getSelectedItem();
        if (dto != null) {
            Course c = new Course(dto.getCourse_id(), courseNameField.getText(), descriptionField.getText());
            service.updateCourse(c);
            loadCourses();
        }
    }

    @FXML
    private void deleteCourse() {
        CourseDTO dto = courseTable.getSelectionModel().getSelectedItem();
        if (dto != null) {
            service.deleteCourse(dto.getCourse_id());
            loadCourses();
            clearFields();
        }
    }

    @FXML
    private void clearFields() {
        courseNameField.clear();
        descriptionField.clear();
    }
}

