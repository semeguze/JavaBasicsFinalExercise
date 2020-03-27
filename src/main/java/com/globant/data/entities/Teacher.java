package com.globant.data.entities;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity that represents a Teacher
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class Teacher {

    private SimpleStringProperty name;
    private SimpleDoubleProperty salaryPerMonth;
    private SimpleIntegerProperty experienceYears = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty activeHours = new SimpleIntegerProperty(0);
    private SimpleStringProperty type;

    public Teacher(String name) {
        this.name = new SimpleStringProperty(name);
    }

    /**
     * Abstract methos to calculate salary per month implemented by each child
     * @param baseSalary base salary from each kind of teacher
     * @param multiplier base multiplier from each kind of teacher
     * @return the value of the salary per month
     */
    public abstract SimpleDoubleProperty calculateSalary(double baseSalary, int multiplier);

}
