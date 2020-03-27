package com.globant.logic;

import com.globant.data.entities.*;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MainViewController {

    public ObservableList<Teacher> setInitialDataTeacher() {
        ObservableList<Teacher> listTeachers = FXCollections.observableArrayList();
        listTeachers.add(new TeacherFullTime("Alejandra Acevedo", 2));
        listTeachers.add(new TeacherFullTime("Sebastian Mesa", 3));
        listTeachers.add(new TeacherPartTime("Santiago Vallejo", 10));
        listTeachers.add(new TeacherPartTime("Fernanda Arana", 12));
        return listTeachers;
    }

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

    public ObservableList<ClassUniversity> setInitialDataClasses(ObservableList<Teacher> listTeachers, ObservableList<Student> listStudents) {
        ObservableList<ClassUniversity> listClasses = FXCollections.observableArrayList();
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
        return listClasses;
    }

    public List<ClassUniversity> retrieveClassesRelatedToStudent(Student studentSelected, ObservableList<ClassUniversity> listClasses) {
        List<ClassUniversity> classes = new ArrayList<>();
        for (ClassUniversity classUniversity : listClasses)
            for (Student student : classUniversity.getStudents())
                if (student.getId().getValue().equals(studentSelected.getId().getValue())) classes.add(classUniversity);
        return classes;
    }

    public void initCombo(JFXComboBox<String> comboAge, JFXComboBox<String> comboTeacher, ObservableList<Teacher> listTeachers) {
        List<String> ageList = new ArrayList<>();
        List<String> nameTeacher = new ArrayList<>();
        for (int i = 1; i <= 90; i++) ageList.add(Integer.toString(i));
        comboAge.setItems(FXCollections.observableArrayList(ageList));
        for (Teacher listTeacher : listTeachers) nameTeacher.add(listTeacher.getName().getValue());
        comboTeacher.setItems(FXCollections.observableArrayList(nameTeacher));
    }


}
