package com.ijse.cmjd111.student.controller;

import com.ijse.cmjd111.student.model.Attendance;
import com.ijse.cmjd111.student.service.AttendanceService;
import com.ijse.cmjd111.student.service.AttendanceServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AttendanceController {

    @FXML private TextField studentIdField;
    @FXML private TextField scheduleIdField;
    @FXML private DatePicker datePicker;
    @FXML private ChoiceBox<String> statusChoice;

    @FXML private TableView<Attendance> attendanceTable;
    @FXML private TableColumn<Attendance, Integer> idCol;
    @FXML private TableColumn<Attendance, Integer> studentIdCol;
    @FXML private TableColumn<Attendance, Integer> scheduleIdCol;
    @FXML private TableColumn<Attendance, String> statusCol;
    @FXML private TableColumn<Attendance, LocalDate> dateCol;

    @FXML private TextField reportScheduleIdField;
    @FXML private Label presentCountLabel;
    @FXML private Label absentCountLabel;

    private final AttendanceService attendanceService = new AttendanceServiceImpl();

    @FXML
    public void initialize() {
        statusChoice.setItems(FXCollections.observableArrayList("Present", "Absent"));
        statusChoice.setValue("Present");

        idCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getAttendanceId()).asObject());
        studentIdCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getStudentId()).asObject());
        scheduleIdCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getScheduleId()).asObject());
        statusCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus()));
        dateCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getMarkedDate()));

        loadAttendanceData();
    }

    @FXML
    private void markAttendance() {
        try {
            int studentId = Integer.parseInt(studentIdField.getText());
            int scheduleId = Integer.parseInt(scheduleIdField.getText());
            LocalDate date = datePicker.getValue();
            String status = statusChoice.getValue();

            if (date == null || status == null) {
                showAlert("Please fill all fields.");
                return;
            }

            Attendance attendance = new Attendance(0, studentId, scheduleId, status, date);
            attendanceService.markAttendance(attendance);
            loadAttendanceData();
            clearFields();

        } catch (NumberFormatException e) {
            showAlert("Student ID and Schedule ID must be numbers.");
        } catch (IllegalArgumentException e) {
            showAlert(e.getMessage());
        }
    }

    private void loadAttendanceData() {
        // For demo: load by schedule id 1 (you can enhance to filter dynamically)
        List<Attendance> attendanceList = attendanceService.getAttendanceBySchedule(1);
        ObservableList<Attendance> observableList = FXCollections.observableArrayList(attendanceList);
        attendanceTable.setItems(observableList);
    }

    @FXML
    private void clearFields() {
        studentIdField.clear();
        scheduleIdField.clear();
        datePicker.setValue(null);
        statusChoice.setValue("Present");
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void showAttendanceReport() {
        try {
            int scheduleId = Integer.parseInt(reportScheduleIdField.getText());
            Map<String, Integer> summary = attendanceService.getAttendanceSummaryBySchedule(scheduleId);

            int presentCount = summary.getOrDefault("Present", 0);
            int absentCount = summary.getOrDefault("Absent", 0);

            presentCountLabel.setText("Present: " + presentCount);
            absentCountLabel.setText("Absent: " + absentCount);

        } catch (NumberFormatException e) {
            showAlert("Schedule ID must be a number.");
        }
    }

    @FXML
    private void handleBackButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ijse/cmjd111/student/lecturerdashboard.fxml"));
            Parent root = loader.load();
            // Get the current stage from any node in the scene graph
            Stage stage = (Stage) studentIdField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Lecturer Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Failed to load Lecturer Dashboard.");
        }
    }

}
