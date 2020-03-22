package com.globant.data.entities;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static com.globant.data.entities.TypeTeacher.FULL_TIME;

@Slf4j
@Getter
@Setter
public class TeacherFullTime extends Teacher {

    private String teacherType = FULL_TIME.getValue();
    private double baseSalary;

    public TeacherFullTime(String name, int experienceYears) {
        super(name);
        this.setExperienceYears(new SimpleIntegerProperty(experienceYears));
        this.setType(new SimpleStringProperty(teacherType));
        this.baseSalary = Double.parseDouble(new MyProperties().getProperties().getProperty("baseSalary.fullTime.value"));
        this.setSalary(calculateSalary(baseSalary, experienceYears));
    }

    public SimpleDoubleProperty calculateSalary(double baseSalary, int experienceYears) {
        double percentage = Double.parseDouble(new MyProperties().getProperties().getProperty("baseSalary.fullTime..percentage"));
        return new SimpleDoubleProperty(baseSalary * (percentage * experienceYears));
    }

    @Override
    public void printDetails() {
        log.info(" * Name : {}, Experience years : {}, Salary : {}", getName(), getExperienceYears(), getSalary());
    }
}
