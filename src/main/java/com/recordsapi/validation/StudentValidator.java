package com.recordsapi.validation;

public class StudentValidator {
    public static void validateStudentInput(String name, Integer age, String grade) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        if (age == null || age <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }
        if (grade == null || grade.trim().isEmpty()) {
            throw new IllegalArgumentException("Grade must not be empty");
        }
    }
}
