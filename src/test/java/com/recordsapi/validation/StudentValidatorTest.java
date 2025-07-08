package com.recordsapi.validation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentValidatorTest {

    @Test
    void testValidInput() {
        assertDoesNotThrow(() ->
                StudentValidator.validateStudentInput("John", 18, "A")
        );
    }

    @Test
    void testNullName() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput(null, 18, "A")
        );
        assertEquals("Name must not be empty", ex.getMessage());
    }

    @Test
    void testEmptyName() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput("   ", 18, "A")
        );
        assertEquals("Name must not be empty", ex.getMessage());
    }

    @Test
    void testNullAge() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput("John", null, "A")
        );
        assertEquals("Age must be greater than 0", ex.getMessage());
    }

    @Test
    void testNegativeAge() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput("John", -1, "A")
        );
        assertEquals("Age must be greater than 0", ex.getMessage());
    }

    @Test
    void testZeroAge() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput("John", 0, "A")
        );
        assertEquals("Age must be greater than 0", ex.getMessage());
    }

    @Test
    void testNullGrade() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput("John", 18, null)
        );
        assertEquals("Grade must not be empty", ex.getMessage());
    }

    @Test
    void testEmptyGrade() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput("John", 18, "   ")
        );
        assertEquals("Grade must not be empty", ex.getMessage());
    }
}
