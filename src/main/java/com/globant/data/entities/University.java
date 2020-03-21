package com.globant.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class University {
    private String name;
    private List<Teacher> teachers;
    private List<Student> students;
    private List<ClassUniversity> classesUniversity;
}
