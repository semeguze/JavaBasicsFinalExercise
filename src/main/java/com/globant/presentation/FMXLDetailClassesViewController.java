package com.globant.presentation;

import com.globant.data.entities.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class FMXLDetailClassesViewController implements Initializable {

    @FXML
    private Label nameClass;
    @FXML
    private Label placeClass;
    @FXML
    private Label nameTeacher;
    @FXML
    private Label typeTeacher;
    @FXML
    private TableView<Student> tableStudentsClass;
    @FXML
    private TableColumn<Student, String> colIdStudent;
    @FXML
    private TableColumn<Student, String> colNameStudent;
    @FXML
    private TableColumn<Student, Integer> colAgeStudent;

    private Utils utils = new Utils();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    @FXML
    public void setData(final ClassUniversity classSelected) {

        log.info("Filling data related to the class selected : {} ", classSelected.getName().getValue());

        nameClass.setText(classSelected.getName().getValue());
        placeClass.setText(classSelected.getClassroom().getValue());
        nameTeacher.setText(classSelected.getTeacher().getName().getValue());
        typeTeacher.setText(classSelected.getTeacher().getType().getValue());

        colIdStudent.setCellValueFactory(cell -> cell.getValue().getId());
        colNameStudent.setCellValueFactory(cell -> cell.getValue().getName());
        colAgeStudent.setCellValueFactory(cell -> cell.getValue().getAge().asObject());

        tableStudentsClass.setItems(FXCollections.observableArrayList(classSelected.getStudents()));

    }


    @FXML
    protected void handleCloseButtonAction(ActionEvent event) {
        utils.closeWindow(event);
    }

}
