package com.globant.presentation;

import com.globant.data.entities.ClassUniversity;
import com.globant.data.entities.Student;
import com.globant.data.entities.Teacher;
import com.globant.data.entities.University;
import com.globant.logic.MainViewController;
import com.globant.logic.setup.MyProperties;
import com.globant.presentation.commons.UtilsUniversity;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Main View Controller. This view manage all the panes
 */
@Slf4j
public class FMXLMainViewController implements Initializable {

    @FXML
    private Pane teachersPane;
    @FXML
    private Pane studentsPane;
    @FXML
    private Pane classesPane;

    @FXML
    private Label labelPartTime;
    @FXML
    private Label labelFullTime;

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
    private TableColumn<Student, String> colIdStudent;
    @FXML
    private TableColumn<Student, String> colNameStudent;
    @FXML
    private TableColumn<Student, Integer> colAgeStudent;

    @FXML
    private TableView<ClassUniversity> classesTable;
    @FXML
    private TableColumn<ClassUniversity, String> colNameClass;
    @FXML
    private TableColumn<ClassUniversity, String> colNameClassroom;
    @FXML
    private TableColumn<ClassUniversity, String> colNameClassTeacher;

    @FXML
    private JFXTextField textFieldID;
    @FXML
    private JFXTextField textFieldName;
    @FXML
    private JFXTextField textFieldNameClass;
    @FXML
    private JFXTextField textFieldNameClassroom;

    @FXML
    private JFXComboBox<String> comboAge = new JFXComboBox<>();
    @FXML
    private JFXComboBox<String> comboTeacher = new JFXComboBox<>();
    @FXML
    private JFXComboBox<String> comboClass = new JFXComboBox<>();
    @FXML
    private CheckComboBox<String> checkComboClasses;

    private MainViewController mainViewController = new MainViewController();
    private UtilsUniversity utils = new UtilsUniversity();
    private ObservableList<Teacher> listTeachers = FXCollections.observableArrayList();
    private ObservableList<Student> listStudents = FXCollections.observableArrayList();
    private ObservableList<ClassUniversity> listClasses = FXCollections.observableArrayList();
    private ObservableList<String> studentsSelectedToCreateClass = FXCollections.observableArrayList();
    private Alert alert = new Alert(Alert.AlertType.CONFIRMATION, null, ButtonType.OK);

    /**
     * This class initialize the FMXLMainViewController class
     * @param location default location
     * @param resources default resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Initializing Main View");
        initializeTeachersData();
        initializeStudentsData();
        University university = initializeClassesData();
        addButtonToTableClasses();
        addButtonToTableStudents();
        mainViewController.initCombo(comboAge, comboTeacher,comboClass, listTeachers, listClasses);
        initMultiCheckCombo();
        log.info("[OK] - Initializing Main View completed. University : {} has been created", university.getName());
    }

    /**
     * Manage the invocation to close the window
     * @param event default action event
     */
    @FXML
    protected void handleCloseButtonAction(ActionEvent event) {
        utils.closeWindow(event);
    }

    /**
     * Manage the logic to show the Teachers pane
     * @param event default action event
     */
    @FXML
    protected void handleTeachersButtonAction(ActionEvent event) {
        log.info("Teacher button clicked. Showing Teacher pane");
        teachersTable.setItems(listTeachers);
        teachersPane.setVisible(true);
        studentsPane.setVisible(false);
        classesPane.setVisible(false);
    }

    /**
     * Manage the logic to show the Students pane
     * @param event default action event
     */
    @FXML
    protected void handleStudentsButtonAction(ActionEvent event) {
        log.info("Student button clicked. Showing Student pane");
        studentsTable.setItems(listStudents);
        clearStudentsFields();
        teachersPane.setVisible(false);
        studentsPane.setVisible(true);
        classesPane.setVisible(false);
    }

    /**
     * Manage the logic to show the Classes pane
     * @param event default action event
     */
    @FXML
    protected void handleClassesButtonAction(ActionEvent event) {
        log.info("Classes button clicked. Showing Classes pane");
        classesTable.setItems(listClasses);
        clearClassesFields();
        teachersPane.setVisible(false);
        studentsPane.setVisible(false);
        classesPane.setVisible(true);
    }

    /**
     * Manage the logic to initialize the Teachers data
     */
    private void initializeTeachersData() {
        log.info("Creating initial teachers");
        labelFullTime.setText("$" + new MyProperties().getProperties().getProperty("baseSalary.fullTime.value"));
        labelPartTime.setText("$" + new MyProperties().getProperties().getProperty("baseSalary.partTime.value"));
        colNameTeacher.setCellValueFactory(cell -> cell.getValue().getName());
        colSalaryTeacher.setCellValueFactory(cell -> cell.getValue().getSalaryPerMonth().asObject());
        colExpTeacher.setCellValueFactory(cell -> cell.getValue().getExperienceYears().asObject());
        colHrsTeacher.setCellValueFactory(cell -> cell.getValue().getActiveHours().asObject());
        colTypeTeacher.setCellValueFactory(cell -> cell.getValue().getType());
        listTeachers = mainViewController.setInitialDataTeacher();
        teachersTable.setItems(listTeachers);
    }

    /**
     * Manage the logic to initialize the Students data
     */
    private void initializeStudentsData() {
        log.info("Creating initial students");
        colIdStudent.setCellValueFactory(cell -> cell.getValue().getId());
        colNameStudent.setCellValueFactory(cell -> cell.getValue().getName());
        colAgeStudent.setCellValueFactory(cell -> cell.getValue().getAge().asObject());
        listStudents = mainViewController.setInitialDataStudents();
        studentsTable.setItems(FXCollections.observableArrayList(listStudents));
    }

    /**
     * Manage the logic to initialize the Classes data
     */
    private University initializeClassesData() {
        log.info("Creating initial classes");
        colNameClass.setCellValueFactory(cell -> cell.getValue().getName());
        colNameClassroom.setCellValueFactory(cell -> cell.getValue().getClassroom());
        colNameClassTeacher.setCellValueFactory(cell -> cell.getValue().getTeacher().getName());
        listClasses = mainViewController.setInitialDataClasses(listTeachers, listStudents);
        classesTable.setItems(listClasses);
        return new University("Test University", listTeachers, listStudents, listClasses);
    }

    /**
     * Manage the logic to add a custom button to the Classes table
     */
    private void addButtonToTableClasses() {
        log.info("Adding custom button to classes table");
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
                                log.error("Error adding button con Classes University");
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

    /**
     * Manage the logic to add a custom button to the Students table
     */
    private void addButtonToTableStudents() {
        log.info("Adding custom button to students table");
        TableColumn<Student, Void> colBtn = new TableColumn<>("View");
        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> cellFactory = new Callback<TableColumn<Student, Void>, TableCell<Student, Void>>() {
            @Override
            public TableCell<Student, Void> call(final TableColumn<Student, Void> param) {
                return new TableCell<Student, Void>() {
                    private final Button btn = new Button("Classes");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Student data = getTableView().getItems().get(getIndex());
                            try {
                                showMatchClasses(data);
                            } catch (Exception e) {
                                log.error("Error adding button to table Students");
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
        studentsTable.getColumns().add(colBtn);

    }

    /**
     * Manage the call to a new Controller with the Classes related to student
     * @param student object Student to show related classes
     * @throws Exception handle the exception
     */
    @FXML
    public void showMatchClasses(Student student) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/classesStudent.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.web("#000000", 0.85));
        FMXLDetailClassesViewController fmxlDetailClassesViewController = loader.getController();
        fmxlDetailClassesViewController.setDataClassesStudents(student, mainViewController.retrieveClassesRelatedToStudent(student, listClasses));
        utils.enableMoveWindow(root, stage);
        stage.show();
    }

    /**
     * Manage the call to a new Controller with the data of a class
     * @param theClass object ClassUniversity with all the data of the class
     * @throws Exception handle the exception
     */
    @FXML
    public void showDetailsClasses(ClassUniversity theClass) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/classDetails.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.web("#000000", 0.85));
        FMXLDetailClassesViewController fmxlDetailClassesViewController = loader.getController();
        fmxlDetailClassesViewController.setData(theClass);
        utils.enableMoveWindow(root, stage);
        stage.show();

    }

    /**
     * Manage the logic to add a new student
     * @param event default action event
     */
    @FXML
    protected void addStudent(ActionEvent event) {

        if (checkFieldsStudentCreation()) {
            Student newStudent = new Student(
                    new SimpleStringProperty(textFieldID.getText()),
                    new SimpleStringProperty(textFieldName.getText()),
                    new SimpleIntegerProperty(Integer.parseInt(comboAge.getValue())));
            listStudents.add(newStudent);
            for (ClassUniversity classUniversity : listClasses){
                if (comboClass.getValue().equals(classUniversity.getName().getValue())){
                    classUniversity.getStudents().add(newStudent);
                }
            }
            studentsTable.setItems(listStudents);
            clearStudentsFields();
        }
    }

    /**
     * Manage the logic to add a new class
     * @param event default action event
     */
    @FXML
    protected void addClass(ActionEvent event) {

        if (checkFieldsClassCreation()) {

            Teacher teacherNewClass = null;
            List<Student> studentsNewClass = new ArrayList<>();

            for (Teacher teacher : listTeachers) {
                if (teacher.getName().getValue().equals(comboTeacher.getValue())) teacherNewClass = teacher;
            }

            for (String selection : studentsSelectedToCreateClass) {
                String[] parts = selection.split(" ->");
                for (Student student : listStudents) {
                    if (student.getId().getValue().equals(parts[0])) studentsNewClass.add(student);
                }
            }

            ClassUniversity newClassUniversity = new ClassUniversity(
                    new SimpleStringProperty(textFieldNameClass.getText()),
                    new SimpleStringProperty(textFieldNameClassroom.getText()),
                    teacherNewClass, studentsNewClass
            );
            listClasses.add(newClassUniversity);
            classesTable.setItems(listClasses);
            studentsSelectedToCreateClass.clear();
            clearClassesFields();
        }
    }

    /**
     * Allow to validate if the fields are correct for Student creation
     * @return boolean result if the fields are correct
     */
    private boolean checkFieldsStudentCreation() {

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
        if (comboAge.getValue() == null || comboAge.getValue().trim().isEmpty()) {
            log.warn("AGE EMPTY");
            alert.setContentText("The age field is empty or invalid");
            alert.showAndWait();
            return false;
        }
        if (comboClass.getValue() == null || comboClass.getValue().trim().isEmpty()) {
            log.warn("CLASS EMPTY");
            alert.setContentText("The class field is empty or invalid");
            alert.showAndWait();
            return false;
        }
        if (listStudents.stream().anyMatch(o -> o.getId().getValue().equals(textFieldID.getText()))) {
            log.warn("ID ALREADY EXISTS");
            alert.setContentText("Some student already have the ID : " + textFieldID.getText());
            alert.showAndWait();
            return false;
        }
        return true;
    }

    /**
     * Allows to clear Student fields
     */
    private void clearStudentsFields() {
        textFieldID.setText(null);
        textFieldName.setText(null);
        comboAge.getSelectionModel().clearSelection();
        comboClass.getSelectionModel().clearSelection();
        mainViewController.initCombo(comboAge, comboTeacher,comboClass, listTeachers, listClasses);
    }

    /**
     * Allows to clear Class fields
     */
    private void clearClassesFields() {
        textFieldNameClass.setText(null);
        textFieldNameClassroom.setText(null);
        comboTeacher.getSelectionModel().clearSelection();
        checkComboClasses.getCheckModel().clearChecks();
        initMultiCheckCombo();
    }

    /**
     * Allow to validate if the fields are correct for Class creation
     * @return boolean result if the fields are correct
     */
    private boolean checkFieldsClassCreation() {

        if (textFieldNameClass.getText() == null || textFieldNameClass.getText().trim().isEmpty()) {
            log.warn("NAME CLASS EMPTY");
            alert.setContentText("The 'name class' field is empty or invalid");
            alert.showAndWait();
            return false;
        }
        if (textFieldNameClassroom.getText() == null || textFieldNameClassroom.getText().trim().isEmpty()) {
            log.warn("NAME CLASSROOM EMPTY");
            alert.setContentText("The 'name classroom' field is empty or invalid");
            alert.showAndWait();
            return false;
        }
        if (comboTeacher.getValue() == null || comboTeacher.getValue().trim().isEmpty()) {
            log.warn("TEACHER EMPTY");
            alert.setContentText("The 'teacher' field is empty or invalid");
            alert.showAndWait();
            return false;
        }
        if (studentsSelectedToCreateClass == null || studentsSelectedToCreateClass.isEmpty()) {
            log.warn("STUDENTS EMPTY");
            alert.setContentText("Select at least one student");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    /**
     * Manage the logic to initialize the multi check combo
     */
    private void initMultiCheckCombo() {
        checkComboClasses.getItems().clear();
        final ObservableMap<String, String> studentsToBeSelected = FXCollections.observableHashMap();

        for (Student listStudent : listStudents) {
            String code = listStudent.getId().getValue();
            String name = listStudent.getName().getValue();
            studentsToBeSelected.put(code, code + " -> " + name);
        }

        checkComboClasses.getItems().addAll(studentsToBeSelected.values());
        checkComboClasses.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) c -> {
            studentsSelectedToCreateClass.clear();
            studentsSelectedToCreateClass.addAll(checkComboClasses.getCheckModel().getCheckedItems());
            if (checkComboClasses.getCheckModel().getCheckedItems().isEmpty()) {
                studentsSelectedToCreateClass = FXCollections.observableArrayList();
            }
        });
    }

}
