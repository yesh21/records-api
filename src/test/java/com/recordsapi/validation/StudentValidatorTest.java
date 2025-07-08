package com.recordsapi.validation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentValidatorTest {

    @Test
    void ValidInput_ShouldNotThrowException() {
        assertDoesNotThrow(() ->
                StudentValidator.validateStudentInput("John", 18, "A")
        );
    }

    @Test
    void NullName_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput(null, 18, "A")
        );
        assertEquals("Name must not be empty", ex.getMessage());
    }

    @Test
    void EmptyName_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput("   ", 18, "A")
        );
        assertEquals("Name must not be empty", ex.getMessage());
    }

    @Test
    void NullAge_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput("John", null, "A")
        );
        assertEquals("Age must be greater than 0", ex.getMessage());
    }

    @Test
    void NegativeAge_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput("John", -1, "A")
        );
        assertEquals("Age must be greater than 0", ex.getMessage());
    }

    @Test
    void ZeroAge_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput("John", 0, "A")
        );
        assertEquals("Age must be greater than 0", ex.getMessage());
    }

    @Test
    void NullGrade_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput("John", 18, null)
        );
        assertEquals("Grade must not be empty", ex.getMessage());
    }

    @Test
    void EmptyGrade_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                StudentValidator.validateStudentInput("John", 18, "   ")
        );
        assertEquals("Grade must not be empty", ex.getMessage());
    }
}
