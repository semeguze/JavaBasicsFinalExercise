package com.globant.data.entities;

import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Entity that represents an University Class
 */
@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class ClassUniversity {
    private SimpleStringProperty name;
    private SimpleStringProperty classroom;
    private Teacher teacher;
    private List<Student> students;

    public ClassUniversity(String name, String classroom, Teacher teacher, List<Student> students) {
        this.name = new SimpleStringProperty(name);
        this.classroom = new SimpleStringProperty(classroom);
        this.teacher = teacher;
        this.students = students;
    }
}
