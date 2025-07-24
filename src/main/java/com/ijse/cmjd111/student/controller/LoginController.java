package com.ijse.cmjd111.student.controller;

import com.ijse.cmjd111.student.model.User;
import com.ijse.cmjd111.student.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    private final UserService userService = new UserService();

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = userService.validateLogin(username, password);

        if (user != null) {
            messageLabel.setText("Welcome, " + user.getRole());

            try {
                String fxmlFile;
                switch (user.getRole()) {
                    case "Admin":
                        fxmlFile = "/com/ijse/cmjd111/student/admin.fxml";
                        break;
                    case "Lecturer":
                        fxmlFile = "/com/ijse/cmjd111/student/lecturer.fxml";
                        break;
                    case "Student":
                        fxmlFile = "/com/ijse/cmjd111/student/student.fxml";
                        break;
                    default:
                        messageLabel.setText("Unknown role.");
                        return;
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = loader.load();

                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle(user.getRole() + " Dashboard");
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                messageLabel.setText("Failed to load dashboard.");
            }

        } else {
            messageLabel.setText("Invalid username or password.");
        }
    }
}
