package com.ijse.cmjd111.student.controller;

import com.ijse.cmjd111.student.model.Attendance;
import com.ijse.cmjd111.student.service.AttendanceService;
import com.ijse.cmjd111.student.service.AttendanceServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;

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

    private final AttendanceService attendanceService = new AttendanceServiceImpl();

    @FXML
    public void initialize() {
        statusChoice.setItems(FXCollections.observableArrayList("Present", "Absent"));
        statusChoice.setValue("Present");

        idCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getAttendanceId()).asObject());
        studentIdCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getStudentId()).asObject());
        scheduleIdCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getScheduleId()).asObject());
        statusCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus()));
        dateCol.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getMarkedDate()));  // Fixed getter

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
            showAlert(e.getMessage());  // Show validation errors like "Student ID does not exist"
        }
    }


    private void loadAttendanceData() {
        List<Attendance> attendanceList = attendanceService.getAttendanceBySchedule(1); // load for default schedule or all
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
}
