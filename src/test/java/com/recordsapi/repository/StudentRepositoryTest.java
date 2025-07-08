package com.recordsapi.repository;

import com.recordsapi.model.StudentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void getStudentById_ShouldReturnStudent() {
        StudentEntity student = new StudentEntity(null, "Stewie", 10, "S");
        StudentEntity savedStudent = studentRepository.save(student);
        Optional<StudentEntity> found = studentRepository.findById(savedStudent.getId());
        assertTrue(found.isPresent());
        assertEquals("Stewie", found.get().getName());
    }

    @Test
    void getStudentById_ShouldReturnNullAfterDeletion() {
        StudentEntity student = new StudentEntity(null, "Meg", 18, "B");
        StudentEntity savedStudent = studentRepository.save(student);
        studentRepository.deleteById(savedStudent.getId());
        Optional<StudentEntity> found = studentRepository.findById(savedStudent.getId());
        assertFalse(found.isPresent());
    }

    @Test
    void getAllStudents_ShouldReturnAllStudents() {
        studentRepository.save(new StudentEntity(null, "Brian", 10, "S"));
        studentRepository.save(new StudentEntity(null, "Peter", 40, "F"));
        assertEquals(2, studentRepository.findAll().size());
    }
}
