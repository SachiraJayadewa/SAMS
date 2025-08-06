package com.ijse.cmjd111.student.controller;

import com.ijse.cmjd111.student.util.SceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StudentDashboardController {

    @FXML
    private void onViewAttendance(ActionEvent event) {
        SceneNavigator.switchScene("/com/ijse/cmjd111/student/studentAttendance.fxml", event, "Student Attendance");
    }

    @FXML
    private void onViewSchedule(ActionEvent event) {
        SceneNavigator.switchScene("/com/sams/view/student_schedule.fxml", event, "Class Schedule");
    }

    @FXML
    private void onLogout(ActionEvent event) {
        SceneNavigator.switchScene("/com/ijse/cmjd111/student/login.fxml", event, "Login");
    }
}


