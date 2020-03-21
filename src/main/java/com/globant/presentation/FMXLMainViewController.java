package com.globant.presentation;

import com.globant.data.entities.Teacher;
import com.globant.data.entities.TeacherFullTime;
import com.globant.data.entities.TeacherPartTime;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class FMXLMainViewController implements Initializable {

    @FXML
    private Pane teachersPane;
    @FXML
    private Pane studentsPane;
    @FXML
    private Pane classesPane;
    @FXML
    private JFXButton teachersButton;
    @FXML
    private JFXButton studentsButton;
    @FXML
    private JFXButton classesButton;
    @FXML
    private TableView<Teacher> teachersTable;
    @FXML
    private TableColumn<Teacher, String> colNameTeacher;
    @FXML
    private TableColumn<Teacher, Double> colSalaryTeacher;
    @FXML
    private TableColumn<Teacher, Integer> colExpTeacher;
    @FXML
    private TableColumn<Teacher, Integer> colHrsTeacher;
    @FXML
    private TableColumn<Teacher, String> colTypeTeacher;

    private ObservableList listTeachers = FXCollections.observableArrayList();

    @FXML
    protected void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void handleTeachersButtonAction(ActionEvent event) {
        teachersPane.setVisible(true);
        studentsPane.setVisible(false);
        classesPane.setVisible(false);
    }

    @FXML
    protected void handleStudentsButtonAction(ActionEvent event) {
        teachersPane.setVisible(false);
        studentsPane.setVisible(true);
        classesPane.setVisible(false);
    }


    @FXML
    protected void handleClassesButtonAction(ActionEvent event) {
        teachersPane.setVisible(false);
        studentsPane.setVisible(false);
        classesPane.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTeacherTable();
    }

    private void initializeTeacherTable() {

        colNameTeacher.setCellValueFactory(cell -> cell.getValue().getName());
        colSalaryTeacher.setCellValueFactory(cell -> cell.getValue().getSalary().asObject());
        colExpTeacher.setCellValueFactory(cell -> cell.getValue().getExperienceYears().asObject());
        colHrsTeacher.setCellValueFactory(cell -> cell.getValue().getActiveHours().asObject());
        colTypeTeacher.setCellValueFactory(cell -> cell.getValue().getType());

        log.info("Creating basic teachers");

        listTeachers.add(new TeacherFullTime("Alejandra Acevedo", 2));
        listTeachers.add(new TeacherFullTime("Sebastian Mesa", 3));
        listTeachers.add(new TeacherPartTime("Santiago Vallejo", 10));
        listTeachers.add(new TeacherPartTime("Fernanda Arana", 12));

        teachersTable.setItems(listTeachers);

    }

}
