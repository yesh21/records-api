package com.recordsapi.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        Student student = new Student("1", "Rockstar", 20, "A");
        assertThat(student.getId()).isEqualTo("1");
        assertThat(student.getName()).isEqualTo("Rockstar");
        assertThat(student.getAge()).isEqualTo(20);
        assertThat(student.getGrade()).isEqualTo("A");
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        Student student = new Student();
        student.setId("2");
        student.setName("Banty");
        student.setAge(18);
        student.setGrade("B");

        assertThat(student.getId()).isEqualTo("2");
        assertThat(student.getName()).isEqualTo("Banty");
        assertThat(student.getAge()).isEqualTo(18);
        assertThat(student.getGrade()).isEqualTo("B");
    }
}

