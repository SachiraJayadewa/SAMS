package com.ijse.cmjd111.student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 250);
        stage.setTitle("SAMS - Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void startstudent(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("student.fxml"));
        primaryStage.setTitle("Student Management");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }
}
