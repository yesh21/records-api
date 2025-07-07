package com.recordsapi.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentInputTest {

    @Test
    void testSettersAndGetters() {
        StudentInput input = new StudentInput();
        input.setName("Chintu");
        input.setAge(22);
        input.setGrade("A");

        assertThat(input.getName()).isEqualTo("Chintu");
        assertThat(input.getAge()).isEqualTo(22);
        assertThat(input.getGrade()).isEqualTo("A");
    }
}

