package com.globant.data.entities;

import com.globant.logic.setup.MyProperties;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static com.globant.data.enums.TypeTeacher.FULL_TIME;

/**
 * Entity that represents one specialization of Teacher, as a Full Time Teacher
 */
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
        this.setSalaryPerMonth(calculateSalary(baseSalary, experienceYears));
    }

    /**
     * Calculate the salary for a Full Time Teacher
     * @param baseSalary base salary from full time teacher
     * @param experienceYears the years experience of a full time teacher
     * @return the calculated salary
     */
    public SimpleDoubleProperty calculateSalary(double baseSalary, int experienceYears) {
        double percentage = Double.parseDouble(new MyProperties().getProperties().getProperty("baseSalary.fullTime..percentage"));
        return new SimpleDoubleProperty(baseSalary * (percentage * experienceYears));
    }
}
