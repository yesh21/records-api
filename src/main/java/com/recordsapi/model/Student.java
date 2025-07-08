package com.recordsapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
@Setter
public class Student {
    private String id;
    private String name;
    private int age;
    private String grade;
}