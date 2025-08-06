package com.ijse.cmjd111.student.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;


public class AdminController {

    private void loadFXML(String fxmlPath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Admin - " + fxmlPath.substring(fxmlPath.lastIndexOf("/") + 1));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleManageCourses(ActionEvent event) {
        loadFXML("/com/ijse/cmjd111/student/course.fxml", event);
    }

    public void handleManageLecturers(ActionEvent event) {
        loadFXML("/com/ijse/cmjd111/student/lecturer.fxml", event);
    }

    public void handleManageStudents(ActionEvent event) {
        loadFXML("/com/ijse/cmjd111/student/student.fxml", event);
    }

    public void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ijse/cmjd111/student/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleManageClasses(ActionEvent event) {loadFXML("/com/ijse/cmjd111/student/classschedule.fxml", event);
    }
}
