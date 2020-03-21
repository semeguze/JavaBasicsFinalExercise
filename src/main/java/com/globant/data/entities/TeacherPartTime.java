package com.globant.data.entities;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class TeacherPartTime extends Teacher {

    private static final String TEACHER_TYPE = "Part-time";

    public TeacherPartTime(String name, int activeHours) {
        super(name);
        this.setActiveHours(new SimpleIntegerProperty(activeHours));
        this.setType(new SimpleStringProperty(TEACHER_TYPE));
        this.setSalary(calculateSalary(activeHours));
    }

    public SimpleDoubleProperty calculateSalary(int activeHours) {
        return new SimpleDoubleProperty(20.0 * activeHours);
    }

    @Override
    public void printDetails() {
        log.info(" * Name : {}, Active Hours : {}, Salary : {}", getName(), getActiveHours(), getSalary());
    }

}
