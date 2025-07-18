package com.recordsapi.validation;

import com.recordsapi.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentValidatorTest {

    @Test
    void getValidateStudentInputValidInput_ShouldNotThrowException() {
        assertDoesNotThrow(() ->
                StudentValidator.validateStudentInput("John", 18, "A")
        );
    }

    @Test
    void getValidateStudentInputNullName_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(InvalidInputException.class, () ->
                StudentValidator.validateStudentInput(null, 18, "A")
        );
        assertEquals("Name must not be empty", ex.getMessage());
    }

    @Test
    void getValidateStudentInputEmptyName_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(InvalidInputException.class, () ->
                StudentValidator.validateStudentInput("   ", 18, "A")
        );
        assertEquals("Name must not be empty", ex.getMessage());
    }

    @Test
    void getValidateStudentInputNullAge_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(InvalidInputException.class, () ->
                StudentValidator.validateStudentInput("John", null, "A")
        );
        assertEquals("Age must be greater than 0", ex.getMessage());
    }

    @Test
    void getValidateStudentInputNegativeAge_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(InvalidInputException.class, () ->
                StudentValidator.validateStudentInput("John", -1, "A")
        );
        assertEquals("Age must be greater than 0", ex.getMessage());
    }

    @Test
    void getValidateStudentInputZeroAge_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(InvalidInputException.class, () ->
                StudentValidator.validateStudentInput("John", 0, "A")
        );
        assertEquals("Age must be greater than 0", ex.getMessage());
    }

    @Test
    void getValidateStudentInputNullGrade_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(InvalidInputException.class, () ->
                StudentValidator.validateStudentInput("John", 18, null)
        );
        assertEquals("Grade must not be empty", ex.getMessage());
    }

    @Test
    void getValidateStudentInputEmptyGrade_ShouldThrowIllegalArgumentException() {
        Exception ex = assertThrows(InvalidInputException.class, () ->
                StudentValidator.validateStudentInput("John", 18, "   ")
        );
        assertEquals("Grade must not be empty", ex.getMessage());
    }
}
