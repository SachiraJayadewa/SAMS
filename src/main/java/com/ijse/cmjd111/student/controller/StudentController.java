package com.ijse.cmjd111.student.controller;

import com.ijse.cmjd111.student.dto.StudentDTO;
import com.ijse.cmjd111.student.model.Student;
import com.ijse.cmjd111.student.service.StudentService;
import com.ijse.cmjd111.student.service.StudentServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class StudentController {

    @FXML private TextField nameField;
    @FXML private TextField regField;
    @FXML private TextField courseField;
    @FXML private TextField contactField;

    @FXML private TableView<StudentDTO> studentTable;
    @FXML private TableColumn<StudentDTO, String> indexCol;
    @FXML private TableColumn<StudentDTO, String> nameCol;
    @FXML private TableColumn<StudentDTO, String> regCol;
    @FXML private TableColumn<StudentDTO, String> courseCol;
    @FXML private TableColumn<StudentDTO, String> contactCol;

    private final StudentService service = new StudentServiceImpl();

    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        regCol.setCellValueFactory(cellData -> cellData.getValue().regNumberProperty());
        courseCol.setCellValueFactory(cellData -> cellData.getValue().courseProperty());
        contactCol.setCellValueFactory(cellData -> cellData.getValue().contactProperty());

        // Autofill fields when a row is selected
        studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nameField.setText(newSelection.getName());
                regField.setText(newSelection.getRegNumber());
                courseField.setText(newSelection.getCourse());
                contactField.setText(newSelection.getContact());
            }
        });
        //index column
        indexCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1)); // display 1-based index
                }
            }
        });


        loadStudents();
    }

    private void loadStudents() {
        List<Student> students = service.getAllStudents();
        List<StudentDTO> dtoList = new ArrayList<>();

        for (Student s : students) {
            dtoList.add(new StudentDTO(
                    s.getStudent_id(),
                    s.getName(),
                    s.getReg_number(),
                    s.getCourse(),
                    s.getContact()
            ));
        }

        ObservableList<StudentDTO> observableList = FXCollections.observableArrayList(dtoList);
        studentTable.setItems(observableList);
    }

    @FXML
    private void addStudent() {
        String name = nameField.getText();
        String reg = regField.getText();
        String course = courseField.getText();
        String contact = contactField.getText();

        if (name.isEmpty() || reg.isEmpty() || course.isEmpty() || contact.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Missing Fields");
            alert.setContentText("Please fill out all fields before adding a student.");
            alert.showAndWait();
            return;
        }

        try {
            Student s = new Student(name, reg, course, contact);
            service.addStudent(s);
            loadStudents();
            clearFields();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Student added successfully!");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Add Failed");
            alert.setContentText("Failed to add student. Please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    private void updateStudent() {
        StudentDTO dto = studentTable.getSelectionModel().getSelectedItem();
        if (dto == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a student to update.");
            alert.showAndWait();
            return;
        }

        try {
            Student s = new Student(dto.getStudent_id(),
                    nameField.getText(),
                    regField.getText(),
                    courseField.getText(),
                    contactField.getText());
            service.updateStudent(s);
            loadStudents();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Student updated successfully!");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Update Failed");
            alert.setContentText("Failed to update student. Please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteStudent() {
        StudentDTO dto = studentTable.getSelectionModel().getSelectedItem();
        if (dto == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a student to delete.");
            alert.showAndWait();
            return;
        }

        try {
            service.deleteStudent(dto.getStudent_id());
            loadStudents();
            clearFields();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Student deleted successfully!");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Delete Failed");
            alert.setContentText("Failed to delete student. Please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    private void clearFields() {
        nameField.clear();
        regField.clear();
        courseField.clear();
        contactField.clear();
    }
}
