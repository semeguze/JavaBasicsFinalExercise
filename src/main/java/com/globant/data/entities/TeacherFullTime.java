package com.globant.data.entities;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class TeacherFullTime extends Teacher {

    private static final  String TEACHER_TYPE = "Full-time";

    public TeacherFullTime(String name, int experienceYears) {
        super(name);
        this.setExperienceYears(new SimpleIntegerProperty(experienceYears));
        this.setType(new SimpleStringProperty(TEACHER_TYPE));
        this.setSalary(calculateSalary(experienceYears));
    }

    public SimpleDoubleProperty calculateSalary(int experienceYears) {
        return new SimpleDoubleProperty(100.0 * experienceYears);
    }

    @Override
    public void printDetails() {
        log.info(" * Name : {}, Experience years : {}, Salary : {}", getName(), getExperienceYears(), getSalary());
    }
}
