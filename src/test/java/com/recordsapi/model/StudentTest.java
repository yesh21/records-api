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

    @Test
    void testEqualsAndHashCode() {
        Student student1 = new Student("1", "Anand", 20, "A");
        Student student2 = new Student("1", "Anand", 20, "A");
        Student student3 = new Student("2", "Banty", 18, "B");

        assertThat(student1).isEqualTo(student2);
        assertThat(student1).hasSameHashCodeAs(student2);
        assertThat(student1).isNotEqualTo(student3);
    }

    @Test
    void testToString() {
        Student student = new Student("1", "David", 23, "A");
        String toString = student.toString();
        assertThat(toString).contains("David").contains("23").contains("A");
    }
}

