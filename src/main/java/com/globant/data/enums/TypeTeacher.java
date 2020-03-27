package com.globant.data.enums;

import lombok.Getter;

/**
 * Enum with the types of teachers
 */
@Getter
public enum TypeTeacher {

    FULL_TIME("Full Time", "Active"),
    PART_TIME("Part Time", "Active");

    private String value;
    private String status;

    TypeTeacher(String value, String status) {
        this.value = value;
        this.status = status;
    }

}
