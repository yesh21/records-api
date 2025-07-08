package com.recordsapi.service;

import com.recordsapi.model.Student;
import com.recordsapi.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    private StudentService studentService;
    private StudentRepository mockRepo;

    @BeforeEach
    void setUp() {
        mockRepo = Mockito.mock(StudentRepository.class);
        studentService = new StudentService(mockRepo);
    }

    @Test
    void getCreateStudent_ShouldAddAndReturnStudent() {
        Student savedStudent = new Student(1L, "Banty", 10, "C");
        when(mockRepo.save(any(Student.class))).thenReturn(savedStudent);
        when(mockRepo.findAll()).thenReturn(List.of(savedStudent));
        Student student = studentService.createStudent("Banty", 10, "C");
        assertThat(student.getId()).isEqualTo(1L);
        assertThat(student.getName()).isEqualTo("Banty");
        assertThat(student.getAge()).isEqualTo(10);
        assertThat(student.getGrade()).isEqualTo("C");
        List<Student> allStudents = studentService.getAllStudents();
        assertThat(allStudents).containsExactly(student);
    }

    @Test
    void getStudentById_ShouldReturnCorrectStudent() {
        Student savedStudent = new Student(1L, "Anand", 32, "C");
        when(mockRepo.save(any(Student.class))).thenReturn(savedStudent);
        when(mockRepo.findById(1L)).thenReturn(Optional.of(savedStudent));
        Student student = studentService.createStudent("Anand", 32, "C");
        Student found = studentService.getStudentById(1L);
        assertThat(found).isEqualTo(student);
    }

    @Test
    void getStudentById_ShouldReturnNullIfNotFound() {
        Student savedStudent = new Student(1L, "Anand", 32, "C");
        when(mockRepo.save(any(Student.class))).thenReturn(savedStudent);
        when(mockRepo.findById(1L)).thenReturn(Optional.of(savedStudent));
        Student student = studentService.getStudentById(3333L);
        assertThat(student).isNull();
    }

    @Test
    void getAllStudents_ShouldReturnAllCreatedStudents() {
        Student savedStudent1 = new Student(1L, "Banty", 10, "C");
        Student savedStudent2 = new Student(2L, "Anand", 32, "E");
        when(mockRepo.save(any(Student.class)))
                .thenReturn(savedStudent1)
                .thenReturn(savedStudent2);
        when(mockRepo.findAll()).thenReturn(List.of(savedStudent1, savedStudent2));
        Student student1 = studentService.createStudent("Banty", 10, "C");
        Student student2 = studentService.createStudent("Anand", 32, "E");
        List<Student> students = studentService.getAllStudents();
        assertThat(students).containsExactly(student1, student2);
    }
}

