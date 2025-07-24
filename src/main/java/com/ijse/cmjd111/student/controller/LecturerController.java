package com.ijse.cmjd111.student.controller;

import com.ijse.cmjd111.student.dto.LecturerDTO;
import com.ijse.cmjd111.student.model.Lecturer;
import com.ijse.cmjd111.student.service.LecturerService;
import com.ijse.cmjd111.student.service.LecturerServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class LecturerController {

    @FXML private TextField nameField;
    @FXML private TextField departmentField;  // Changed from empIdField to departmentField
    @FXML private TextField emailField;
    @FXML private TextField contactField;

    @FXML private TableView<LecturerDTO> lecturerTable;
    @FXML private TableColumn<LecturerDTO, String> nameCol;
    @FXML private TableColumn<LecturerDTO, String> departmentCol;  // Changed from empIdCol
    @FXML private TableColumn<LecturerDTO, String> emailCol;
    @FXML private TableColumn<LecturerDTO, String> contactCol;

    private final LecturerService service = new LecturerServiceImpl();

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        departmentCol.setCellValueFactory(cellData -> cellData.getValue().departmentProperty()); // changed
        emailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        contactCol.setCellValueFactory(cellData -> cellData.getValue().contactProperty());

        // Auto-fill fields on row select
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
        String name = nameField.getText();
        String department = departmentField.getText();
        String email = emailField.getText();
        String contact = contactField.getText();

        if (name.isEmpty() || department.isEmpty() || email.isEmpty() || contact.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Missing Fields");
            alert.setContentText("Please fill all fields before adding a lecturer.");
            alert.showAndWait();
            return;
        }

        Lecturer lecturer = new Lecturer(0, name, department, contact, email); // use 0 or default id for new
        service.addLecturer(lecturer);
        loadLecturers();
        clearFields();
    }

    @FXML
    private void updateLecturer() {
        LecturerDTO dto = lecturerTable.getSelectionModel().getSelectedItem();
        if (dto != null) {
            Lecturer lecturer = new Lecturer(dto.getLecturerId(),
                    nameField.getText(),
                    departmentField.getText(),
                    contactField.getText(),
                    emailField.getText());
            service.updateLecturer(lecturer);
            loadLecturers();
            clearFields();
        }
    }

    @FXML
    private void deleteLecturer() {
        LecturerDTO dto = lecturerTable.getSelectionModel().getSelectedItem();
        if (dto != null) {
            service.deleteLecturer(dto.getLecturerId());
            loadLecturers();
            clearFields();
        }
    }

    @FXML
    private void clearFields() {
        nameField.clear();
        departmentField.clear();
        emailField.clear();
        contactField.clear();
    }
}


