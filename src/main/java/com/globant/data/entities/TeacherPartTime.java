package com.globant.data.entities;

import com.globant.logic.setup.MyProperties;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static com.globant.data.enums.TypeTeacher.PART_TIME;

@Slf4j
@Getter
@Setter
public class TeacherPartTime extends Teacher {

    private String teacherType = PART_TIME.getValue();
    private double baseSalary;

    public TeacherPartTime(String name, int activeHours) {
        super(name);
        this.setActiveHours(new SimpleIntegerProperty(activeHours));
        this.setType(new SimpleStringProperty(teacherType));
        this.baseSalary = Double.parseDouble(new MyProperties().getProperties().getProperty("baseSalary.partTime.value"));
        this.setSalaryPerMonth(calculateSalary(baseSalary, activeHours));
    }

    public SimpleDoubleProperty calculateSalary(double baseSalary, int activeHours) {
        return new SimpleDoubleProperty(4 * (baseSalary * activeHours));
    }

    @Override
    public void printDetails() {
        log.info(" * Name : {}, Active Hours : {}, Salary : {}", getName(), getActiveHours(), getSalaryPerMonth());
    }

}
