package com.globant.presentation;

import com.globant.data.entities.ClassUniversity;
import com.globant.data.entities.Student;
import com.globant.data.entities.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Getter
@Setter
@Slf4j
public class FMXLAddStudentViewController implements Initializable {


    @FXML
    private JFXTextField textFieldID;
    @FXML
    private JFXTextField textFieldName;
    @FXML
    private JFXTextField textFieldAge;
    @FXML
    private JFXButton closeButton;
    @FXML
    private JFXButton addButton;

    private List<Student> actualStudents;
    private Utils utils = new Utils();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void setData(final List<Student> studentList) {
        this.actualStudents = studentList;
    }

    @FXML
    protected void addStudent(ActionEvent event) {

        if (checkFields()) {
            Student newStudent = new Student(
                    new SimpleStringProperty(textFieldID.getText()),
                    new SimpleStringProperty(textFieldName.getText()),
                    new SimpleIntegerProperty(Integer.parseInt(textFieldAge.getText())));

            actualStudents.add(newStudent);

        }

    }

    private boolean checkFields() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, null, ButtonType.OK);

        if (textFieldID.getText() == null || textFieldID.getText().trim().isEmpty()) {
            log.warn("ID EMPTY");
            alert.setContentText("The ID field is empty or invalid");
            alert.showAndWait();
            return false;
        }

        if (textFieldName.getText() == null || textFieldName.getText().trim().isEmpty()) {
            log.warn("NAME EMPTY");
            alert.setContentText("The name field is empty or invalid");
            alert.showAndWait();
            return false;
        }

        if (textFieldAge.getText() == null || textFieldAge.getText().trim().isEmpty()) {
            log.warn("AGE EMPTY");
            alert.setContentText("The age field is empty or invalid");
            alert.showAndWait();
            return false;
        }

        try {
            Integer.parseInt(textFieldAge.getText());
            if (actualStudents.stream().anyMatch(o -> o.getId().getValue().equals(textFieldID.getText()))) {
                log.warn("ID ALREADY EXISTS");
                alert.setContentText("Some student already have the ID : " + textFieldID.getText());
                alert.showAndWait();
                return false;
            }
        } catch (Exception e) {
            log.warn("WRONG INPUT");
            alert.setContentText("The AGE field is not a number");
            alert.showAndWait();
            return false;
        }

        return true;
    }


    @FXML
    protected void handleCloseButtonAction(ActionEvent event) {
        utils.closeWindow(event);
    }

}
