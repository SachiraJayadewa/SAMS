package com.ijse.cmjd111.student.controller;

import com.ijse.cmjd111.student.model.ClassSchedule;
import com.ijse.cmjd111.student.service.ClassScheduleService;
import com.ijse.cmjd111.student.service.ClassScheduleServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;

public class ClassScheduleController {

    @FXML private TextField subjectField;
    @FXML private TextField courseIdField;
    @FXML private TextField lecturerIdField;
    @FXML private DatePicker datePicker;

    @FXML private TableView<ClassSchedule> scheduleTable;
    @FXML private TableColumn<ClassSchedule, String> subjectCol;
    @FXML private TableColumn<ClassSchedule, Integer> courseIdCol;
    @FXML private TableColumn<ClassSchedule, Integer> lecturerIdCol;
    @FXML private TableColumn<ClassSchedule, LocalDate> dateCol;

    private final ClassScheduleService service = new ClassScheduleServiceImpl();

    @FXML
    public void initialize() {
        subjectCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSubject()));
        courseIdCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCourseId()).asObject());
        lecturerIdCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getLecturerId()).asObject());
        dateCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getDate()));

        loadSchedules();

        scheduleTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                subjectField.setText(newSelection.getSubject());
                courseIdField.setText(String.valueOf(newSelection.getCourseId()));
                lecturerIdField.setText(String.valueOf(newSelection.getLecturerId()));
                datePicker.setValue(newSelection.getDate());
            }
        });
    }

    @FXML
    private void addSchedule() {
        String subject = subjectField.getText().trim();
        String courseIdText = courseIdField.getText().trim();
        String lecturerIdText = lecturerIdField.getText().trim();
        LocalDate date = datePicker.getValue();

        // Input validation
        if (subject.isEmpty() || courseIdText.isEmpty() || lecturerIdText.isEmpty() || date == null) {
            showAlert("Please fill in all fields.");
            return;
        }

        try {
            int courseId = Integer.parseInt(courseIdText);
            int lecturerId = Integer.parseInt(lecturerIdText);

            ClassSchedule schedule = new ClassSchedule(0, subject, courseId, lecturerId, date);
            service.addSchedule(schedule);
            loadSchedules();
            clearFields();

        } catch (NumberFormatException e) {
            showAlert("Course ID and Lecturer ID must be valid numbers.");
        }
    }


    @FXML
    private void updateSchedule() {
        ClassSchedule selected = scheduleTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String subject = subjectField.getText().trim();
            String courseIdText = courseIdField.getText().trim();
            String lecturerIdText = lecturerIdField.getText().trim();
            LocalDate date = datePicker.getValue();

            if (subject.isEmpty() || courseIdText.isEmpty() || lecturerIdText.isEmpty() || date == null) {
                showAlert("Please fill in all fields.");
                return;
            }

            try {
                selected.setSubject(subject);
                selected.setCourseId(Integer.parseInt(courseIdText));
                selected.setLecturerId(Integer.parseInt(lecturerIdText));
                selected.setDate(date);

                service.updateSchedule(selected);
                loadSchedules();
                clearFields();
            } catch (NumberFormatException e) {
                showAlert("Course ID and Lecturer ID must be valid numbers.");
            }
        }
    }


    @FXML
    private void deleteSchedule() {
        ClassSchedule selected = scheduleTable.getSelectionModel().getSelectedItem();
        if(selected != null) {
            service.deleteSchedule(selected.getScheduleId());
            loadSchedules();
            clearFields();
        }
    }

    private void loadSchedules() {
        List<ClassSchedule> schedules = service.getAllSchedules();
        ObservableList<ClassSchedule> observableList = FXCollections.observableArrayList(schedules);
        scheduleTable.setItems(observableList);
    }

    @FXML
    private void clearFields() {
        subjectField.clear();
        courseIdField.clear();
        lecturerIdField.clear();
        datePicker.setValue(null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

