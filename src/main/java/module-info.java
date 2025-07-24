module com.ijse.cmjd111.student {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.ijse.cmjd111.student to javafx.fxml;
    opens com.ijse.cmjd111.student.controller to javafx.fxml;

    exports com.ijse.cmjd111.student;
}