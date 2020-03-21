package com.globant.presentation;

import com.globant.data.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
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

    @FXML
    private TableView<Student> studentsTable;

    @FXML
    private TableView<ClassUniversity> classesTable;
    @FXML
    private TableColumn<ClassUniversity, String> colNameClass;
    @FXML
    private TableColumn<ClassUniversity, String> colNameClassroom;
    @FXML
    private TableColumn<ClassUniversity, String> colNameClassTeacher;

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


    private void addButtonToTable() {

        TableColumn<ClassUniversity, Void> colBtn = new TableColumn<>("View");

        Callback<TableColumn<ClassUniversity, Void>, TableCell<ClassUniversity, Void>> cellFactory = new Callback<TableColumn<ClassUniversity, Void>, TableCell<ClassUniversity, Void>>() {
            @Override
            public TableCell<ClassUniversity, Void> call(final TableColumn<ClassUniversity, Void> param) {
                return new TableCell<ClassUniversity, Void>() {
                    private final Button btn = new Button("Details");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            ClassUniversity data = getTableView().getItems().get(getIndex());
                            try {
                                showDetailsClasses(data);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        colBtn.setCellFactory(cellFactory);
        classesTable.getColumns().add(colBtn);

    }

    @FXML
    public void showDetailsClasses(ClassUniversity theClass) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/classDetails.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.web("#000000",0.85));
        FMXLDetailClassesViewController fmxlDetailClassesViewController = loader.getController();
        fmxlDetailClassesViewController.setData(theClass);
        Utils.enableMoveWindow(root, stage);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTeachersData();
        initializeStudentsData();
        University university = initializeClassesData();
        addButtonToTable();
    }

    private University initializeClassesData() {

        colNameClass.setCellValueFactory(cell -> cell.getValue().getName());
        colNameClassroom.setCellValueFactory(cell -> cell.getValue().getClassroom());
        colNameClassTeacher.setCellValueFactory(cell -> cell.getValue().getTeacher().getName());

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

        classesTable.setItems(listClasses);

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
