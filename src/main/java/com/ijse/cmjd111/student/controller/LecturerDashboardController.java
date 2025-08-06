package com.ijse.cmjd111.student.controller;

import com.ijse.cmjd111.student.util.SceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LecturerDashboardController {

    @FXML
    public void handleMarkAttendance(ActionEvent event) {
        SceneNavigator.switchScene("/com/ijse/cmjd111/student/attendance.fxml", event, "Mark Attendance");
    }

    @FXML
    public void handleViewAttendance(ActionEvent event) {
        SceneNavigator.switchScene("/com/ijse/cmjd111/student/attendance.fxml", event, "View Attendance");
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        SceneNavigator.switchScene("/com/ijse/cmjd111/student/login.fxml", event, "Login");
    }
}

