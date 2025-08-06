package com.ijse.cmjd111.student.controller;

import com.ijse.cmjd111.student.model.Attendance;
import com.ijse.cmjd111.student.service.AttendanceService;
import com.ijse.cmjd111.student.service.AttendanceServiceImpl;
import com.ijse.cmjd111.student.util.LoggedInUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.List;

public class StudentAttendanceController {

    @FXML
    private TableView<Attendance> attendanceTable;

    @FXML
    private TableColumn<Attendance, String> colDate;

    @FXML
    private TableColumn<Attendance, String> colCourse; // Optional, needs join logic

    @FXML
    private TableColumn<Attendance, String> colStatus;

    private final AttendanceService attendanceService = new AttendanceServiceImpl();

    @FXML
    public void initialize() {
        colDate.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMarkedDate().toString()));

        // 🔔 This will need enhancement to show course name using JOIN if needed
        colCourse.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty("Schedule #" + cellData.getValue().getScheduleId()));

        colStatus.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus()));

        loadStudentAttendance();
    }

    private void loadStudentAttendance() {
        int studentId = LoggedInUser.getInstance().getStudentId(); // Implement your own user/session manager
        List<Attendance> records = attendanceService.getAttendanceByStudent(studentId);
        ObservableList<Attendance> observableList = FXCollections.observableArrayList(records);
        attendanceTable.setItems(observableList);
    }

    @FXML
    private void onBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/ijse/cmjd111/student/studentDashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

