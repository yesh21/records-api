package com.recordsapi.service;

import com.recordsapi.model.StudentEntity;
import com.recordsapi.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCreateStudent_ShouldAddAndReturnStudent() {
        StudentEntity savedStudent = new StudentEntity(1L, "Banty", 10, "C");
        when(studentRepository.save(any(StudentEntity.class))).thenReturn(savedStudent);
        when(studentRepository.findAll()).thenReturn(List.of(savedStudent));
        StudentEntity student = studentService.createStudent("Banty", 10, "C");
        assertThat(student.getId()).isEqualTo(1L);
        assertThat(student.getName()).isEqualTo("Banty");
        assertThat(student.getAge()).isEqualTo(10);
        assertThat(student.getGrade()).isEqualTo("C");
        List<StudentEntity> allStudents = studentService.getAllStudents();
        assertThat(allStudents).containsExactly(student);
    }

    @Test
    void getStudentById_ShouldReturnCorrectStudent() {
        StudentEntity savedStudent = new StudentEntity(1L, "Anand", 32, "C");
        when(studentRepository.save(any(StudentEntity.class))).thenReturn(savedStudent);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(savedStudent));
        StudentEntity student = studentService.createStudent("Anand", 32, "C");
        StudentEntity found = studentService.getStudentById(1L);
        assertThat(found).isEqualTo(student);
    }

    @Test
    void getStudentById_ShouldReturnNullIfNotFound() {
        StudentEntity savedStudent = new StudentEntity(1L, "Anand", 32, "C");
        when(studentRepository.save(any(StudentEntity.class))).thenReturn(savedStudent);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(savedStudent));
        StudentEntity student = studentService.getStudentById(3333L);
        assertThat(student).isNull();
    }

    @Test
    void getAllStudents_ShouldReturnAllCreatedStudents() {
        StudentEntity savedStudent1 = new StudentEntity(1L, "Banty", 10, "C");
        StudentEntity savedStudent2 = new StudentEntity(2L, "Anand", 32, "E");
        when(studentRepository.save(any(StudentEntity.class)))
                .thenReturn(savedStudent1)
                .thenReturn(savedStudent2);
        when(studentRepository.findAll()).thenReturn(List.of(savedStudent1, savedStudent2));
        StudentEntity student1 = studentService.createStudent("Banty", 10, "C");
        StudentEntity student2 = studentService.createStudent("Anand", 32, "E");
        List<StudentEntity> students = studentService.getAllStudents();
        assertThat(students).containsExactly(student1, student2);
    }
}

