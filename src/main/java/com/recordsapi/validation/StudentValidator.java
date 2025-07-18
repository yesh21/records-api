package com.recordsapi.validation;

import com.recordsapi.exception.InvalidInputException;

public class StudentValidator {
    public static void validateStudentInput(String name, Integer age, String grade) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Name must not be empty");
        }
        if (age == null || age <= 0) {
            throw new InvalidInputException("Age must be greater than 0");
        }
        if (grade == null || grade.trim().isEmpty()) {
            throw new InvalidInputException("Grade must not be empty");
        }
    }
}
