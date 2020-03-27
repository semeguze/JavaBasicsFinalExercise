package com.globant.logic;

import com.globant.data.entities.*;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
public class MainViewController {

    /**
     * Set the initial data for Teachers
     * @return the list of Teachers filled
     */
    public ObservableList<Teacher> setInitialDataTeacher() {
        ObservableList<Teacher> listTeachers = FXCollections.observableArrayList();
        listTeachers.add(new TeacherFullTime("Felipe Jimenez", 6));
        listTeachers.add(new TeacherFullTime("Sebastian Mesa", 3));
        listTeachers.add(new TeacherPartTime("Alejandra Acevedo", 10));
        listTeachers.add(new TeacherPartTime("Carol Bohorquez", 12));
        return listTeachers;
    }

    /**
     * Set the initial data for Students
     * @return the list of Students filled
     */
    public ObservableList<Student> setInitialDataStudents() {
        ObservableList<Student> listStudents = FXCollections.observableArrayList();
        listStudents.add(new Student("0001", "Juan Perez", 18));
        listStudents.add(new Student("0002", "Adriana Montoya", 19));
        listStudents.add(new Student("0003", "Sandra Gonzales", 18));
        listStudents.add(new Student("0004", "Jose Santos", 22));
        listStudents.add(new Student("0005", "Cristina Jimenez", 21));
        listStudents.add(new Student("0006", "Rosa Smith", 20));
        return listStudents;
    }

    /**
     * Set the initial data for Students
     * @param listTeachers receive the current list of teachers
     * @param listStudents receive the current list of students
     * @return the list of Classes filled
     */
    public ObservableList<ClassUniversity> setInitialDataClasses(ObservableList<Teacher> listTeachers, ObservableList<Student> listStudents) {
        ObservableList<ClassUniversity> listClasses = FXCollections.observableArrayList();
        listClasses.add(new ClassUniversity("OOP", "Training room 1", listTeachers.get(0), pickNRandom(listStudents)));
        listClasses.add(new ClassUniversity("Math", "Building 2 - 202", listTeachers.get(1), pickNRandom(listStudents)));
        listClasses.add(new ClassUniversity("Music", "Complex #64", listTeachers.get(2), pickNRandom(listStudents)));
        listClasses.add(new ClassUniversity("Operating Systems", "Picasso 1234 - 101", listTeachers.get(3), pickNRandom(listStudents)));
        return listClasses;
    }

    /**
     * Allows so select random elements inside a list
     * @param listStudents receive the current list of students
     * @return a list with random elements selected
     */
    public static ObservableList<Student> pickNRandom(ObservableList<Student> listStudents) {
        ObservableList<Student> studentClass = FXCollections.observableArrayList();
        Collections.shuffle(listStudents);
        int low = 1;
        int high = listStudents.size();
        int random = new Random().nextInt(high - low) + low;
        for (int i = 0; i < random; i++) studentClass.add(listStudents.get(i));
        return studentClass;
    }

    /**
     * Retrieve the classes that are related to a specific Student
     * @param studentSelected the Student to get the data
     * @param listClasses current list of ClassUniversity
     * @return a list of ClassUniversity for a specific Student
     */
    public List<ClassUniversity> retrieveClassesRelatedToStudent(Student studentSelected, ObservableList<ClassUniversity> listClasses) {
        List<ClassUniversity> classes = new ArrayList<>();
        for (ClassUniversity classUniversity : listClasses)
            for (Student student : classUniversity.getStudents())
                if (student.getId().getValue().equals(studentSelected.getId().getValue())) classes.add(classUniversity);
        return classes;
    }

    /**
     * Init the combo data
     * @param comboAge maps the options for age
     * @param comboTeacher maps the options for teacher
     * @param comboClass maps the options for class
     * @param listTeachers contains the actual list of teachers
     * @param listClasses contains the actual list of classes
     */
    public void initCombo(JFXComboBox<String> comboAge, JFXComboBox<String> comboTeacher, JFXComboBox<String> comboClass, ObservableList<Teacher> listTeachers, ObservableList<ClassUniversity> listClasses) {
        List<String> ageList = new ArrayList<>();
        List<String> nameTeacher = new ArrayList<>();
        List<String> nameClasses = new ArrayList<>();
        for (int i = 1; i <= 90; i++) ageList.add(Integer.toString(i));
        comboAge.setItems(FXCollections.observableArrayList(ageList));
        for (Teacher listTeacher : listTeachers) nameTeacher.add(listTeacher.getName().getValue());
        comboTeacher.setItems(FXCollections.observableArrayList(nameTeacher));
        for (ClassUniversity listClass : listClasses) nameClasses.add(listClass.getName().getValue());
        comboClass.setItems(FXCollections.observableArrayList(nameClasses));

    }


}
