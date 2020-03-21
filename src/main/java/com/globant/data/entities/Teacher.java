package com.globant.data.entities;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Teacher {

    private SimpleStringProperty name;
    private SimpleDoubleProperty salary;
    private SimpleIntegerProperty experienceYears = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty activeHours = new SimpleIntegerProperty(0);
    private SimpleStringProperty type;

    public Teacher(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public abstract SimpleDoubleProperty calculateSalary(int multiplier);

    public abstract void printDetails();

}
