package com.recordsapi.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentEntityTest {

    @Test
    void getAllArgsConstructorAndGetters_ShouldInitializeAndReturnAllFields() {
        StudentEntity student = new StudentEntity(1L, "Rockstar", 20, "A");
        assertThat(student.getId()).isEqualTo(1L);
        assertThat(student.getName()).isEqualTo("Rockstar");
        assertThat(student.getAge()).isEqualTo(20);
        assertThat(student.getGrade()).isEqualTo("A");
    }
}

