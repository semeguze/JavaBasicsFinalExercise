package com.globant.presentation;

import com.globant.presentation.commons.*;
import com.globant.data.entities.ClassUniversity;
import com.globant.data.entities.Student;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Details Classes Controller. This view show the classes details
 */
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


    @FXML
    private Label nameStudent;
    @FXML
    private Label idStudent;
    @FXML
    private Label ageStudent;

    @FXML
    private TableView<ClassUniversity> classesTable;
    @FXML
    private TableColumn<ClassUniversity, String> colNameClass;
    @FXML
    private TableColumn<ClassUniversity, String> colNameClassroom;
    @FXML
    private TableColumn<ClassUniversity, String> colNameClassTeacher;

    private UtilsUniversity utils = new UtilsUniversity();

    /**
     * This class initialize the FMXLDetailClassesViewController class
     * @param location default location
     * @param resources default resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * This class allow to set data to the students table
     * @param classSelected ClassUniversity to set items
     */
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

    /**
     * This class allow to set data to the classes table
     * @param student Student to set items
     * @param classes List of ClassUniversity to been shown
     */
    @FXML
    public void setDataClassesStudents(final Student student, final List<ClassUniversity> classes) {

        log.info("Filling classes related to : {} ", student.getName().getValue());

        nameStudent.setText(student.getName().getValue());
        idStudent.setText(student.getId().getValue());
        ageStudent.setText(Integer.toString(student.getAge().getValue()) + " years old");

        colNameClass.setCellValueFactory(cell -> cell.getValue().getName());
        colNameClassroom.setCellValueFactory(cell -> cell.getValue().getClassroom());
        colNameClassTeacher.setCellValueFactory(cell -> cell.getValue().getTeacher().getName());

        classesTable.setItems(FXCollections.observableArrayList(classes));

    }

    /**
     * Manage the invocation to close the window
     * @param event default action event
     */
    @FXML
    protected void handleCloseButtonAction(ActionEvent event) {
        utils.closeWindow(event);
    }

}
