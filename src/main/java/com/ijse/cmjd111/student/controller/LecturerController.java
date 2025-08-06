package com.ijse.cmjd111.student.controller;

import com.ijse.cmjd111.student.dto.LecturerDTO;
import com.ijse.cmjd111.student.model.Lecturer;
import com.ijse.cmjd111.student.service.LecturerService;
import com.ijse.cmjd111.student.service.LecturerServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



import java.util.ArrayList;
import java.util.List;

public class LecturerController {

    @FXML private TextField nameField;
    @FXML private TextField departmentField;
    @FXML private TextField emailField;
    @FXML private TextField contactField;

    @FXML private TableView<LecturerDTO> lecturerTable;
    @FXML private TableColumn<LecturerDTO, String> nameCol;
    @FXML private TableColumn<LecturerDTO, String> departmentCol;
    @FXML private TableColumn<LecturerDTO, String> emailCol;
    @FXML private TableColumn<LecturerDTO, String> contactCol;

    private final LecturerService service = new LecturerServiceImpl();

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        departmentCol.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
        emailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        contactCol.setCellValueFactory(cellData -> cellData.getValue().contactProperty());

        lecturerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                nameField.setText(newSel.getName());
                departmentField.setText(newSel.getDepartment());
                emailField.setText(newSel.getEmail());
                contactField.setText(newSel.getContact());
            }
        });

        loadLecturers();
    }

    private void loadLecturers() {
        List<Lecturer> lecturers = service.getAllLecturers();
        List<LecturerDTO> dtoList = new ArrayList<>();

        for (Lecturer l : lecturers) {
            dtoList.add(new LecturerDTO(
                    l.getLecturerId(),
                    l.getName(),
                    l.getDepartment(),
                    l.getEmail(),
                    l.getContact()
            ));
        }

        ObservableList<LecturerDTO> observableList = FXCollections.observableArrayList(dtoList);
        lecturerTable.setItems(observableList);
    }

    @FXML
    private void addLecturer() {
        String name = nameField.getText().trim();
        String department = departmentField.getText().trim();
        String email = emailField.getText().trim();
        String contact = contactField.getText().trim();

        if (name.isEmpty() || department.isEmpty() || email.isEmpty() || contact.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Missing Fields", "Please fill all fields before adding a lecturer.");
            return;
        }

        Lecturer lecturer = new Lecturer(0, name, department, contact, email);
        service.addLecturer(lecturer);
        loadLecturers();
        clearFields();
    }

    @FXML
    private void updateLecturer() {
        LecturerDTO dto = lecturerTable.getSelectionModel().getSelectedItem();
        if (dto != null) {
            Lecturer lecturer = new Lecturer(dto.getLecturerId(),
                    nameField.getText().trim(),
                    departmentField.getText().trim(),
                    contactField.getText().trim(),
                    emailField.getText().trim());
            service.updateLecturer(lecturer);
            loadLecturers();
            clearFields();
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a lecturer to update.");
        }
    }

    @FXML
    private void deleteLecturer() {
        LecturerDTO dto = lecturerTable.getSelectionModel().getSelectedItem();
        if (dto != null) {
            service.deleteLecturer(dto.getLecturerId());
            loadLecturers();
            clearFields();
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a lecturer to delete.");
        }
    }

    @FXML
    private void clearFields() {
        nameField.clear();
        departmentField.clear();
        emailField.clear();
        contactField.clear();
        lecturerTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleBackToAdmin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/ijse/cmjd111/student/admin.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Admin Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Unable to load Admin Dashboard.");
        }
    }
}
