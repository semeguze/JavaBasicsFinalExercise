package com.globant.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class Class {
    private String name;
    private String classroom;
    private Teacher teacher;
    private List<Student> students;

    public void printDetails() {
        log.info(" -> Name : {}", getName());
        log.info("    Classroom : {}", getClassroom());
        log.info("    Teacher : ");
        getTeacher().printDetails();
        log.info("    Students : ");
        for (Student student:getStudents()) student.printDetails();
    }
}
