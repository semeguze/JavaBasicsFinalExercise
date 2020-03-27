package com.globant.data.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Entity that represents a Student
 */
@Slf4j
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Student {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty age;

    public Student(String id, String name, int age) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
    }
}
