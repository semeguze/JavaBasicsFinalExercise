package com.globant.presentation;

import com.globant.data.entities.*;
import com.globant.data.entities.ClassUniversity;
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
import java.util.ArrayList;
import java.util.List;
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

    private ObservableList<Teacher> listTeachers = FXCollections.observableArrayList();
    private ObservableList<Student> listStudents = FXCollections.observableArrayList();
    private ObservableList<ClassUniversity> listClasses = FXCollections.observableArrayList();

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
        initializeTeachersData();
        initializeStudentsData();
        University university = initializeClassesData();
    }

    private University initializeClassesData() {

        log.info("Creating basic classes");
        ObservableList<Student> studentClass1 = FXCollections.observableArrayList();
        studentClass1.add(listStudents.get(0));
        listClasses.add(new ClassUniversity("OOP", "Training room 1", listTeachers.get(2), studentClass1));
        ObservableList<Student> studentClass2 = FXCollections.observableArrayList();
        studentClass2.add(listStudents.get(0));
        studentClass2.add(listStudents.get(2));
        studentClass2.add(listStudents.get(3));
        listClasses.add(new ClassUniversity("Math", "Building 2 - 202", listTeachers.get(3), studentClass2));
        List<Student> studentClass3 = new ArrayList<>();
        studentClass3.add(listStudents.get(5));
        listClasses.add(new ClassUniversity("Music", "Complex #64", listTeachers.get(2), studentClass3));
        List<Student> studentClass4 = new ArrayList<>();
        studentClass4.add(listStudents.get(1));
        studentClass4.add(listStudents.get(3));
        listClasses.add(new ClassUniversity("Operating Systems", "Picasso 1234 - 101", listTeachers.get(0), studentClass4));
        return new University("Test University", listTeachers, listStudents, listClasses);

    }

    private void initializeStudentsData() {

        log.info("Creating basic students");

        listStudents.add(new Student("0001", "Juan Perez", 18));
        listStudents.add(new Student("0002", "Adriana Montoya", 19));
        listStudents.add(new Student("0003", "Sandra Gonzales", 18));
        listStudents.add(new Student("0004", "Jose Santos", 22));
        listStudents.add(new Student("0005", "Cristina Jimenez", 21));
        listStudents.add(new Student("0006", "Rosa Smith", 20));

    }

    private void initializeTeachersData() {

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
