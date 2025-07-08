package com.recordsapi.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentTest {

    @Test
    void allArgsConstructorAndGetters_ShouldInitializeAndReturnAllFields() {
        Student student = new Student("1", "Rockstar", 20, "A");
        assertThat(student.getId()).isEqualTo("1");
        assertThat(student.getName()).isEqualTo("Rockstar");
        assertThat(student.getAge()).isEqualTo(20);
        assertThat(student.getGrade()).isEqualTo("A");
    }
}

