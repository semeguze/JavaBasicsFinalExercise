package com.globant.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Student {
    private String id;
    private String name;
    private int age;

    public void printDetails() {
        log.info(" * Id : {}, Name : {}, Age : {}", getId(), getName(), getAge());
    }
}
