package com.recordsapi.graphql;

import com.recordsapi.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentDataFetcherIntegrationTest {

    private HttpGraphQlTester graphQlTester;

    @LocalServerPort
    int port;

    private String createdStudentId;

    @BeforeEach
    void setUp() {
        WebTestClient client = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port + "/graphql")
                .build();
        graphQlTester = HttpGraphQlTester.create(client);

        // language=GraphQL
        String createMutation = """
            mutation {
                createStudent(name: "Rockstar", age: 20, grade: "S") {
                    id
                    name
                    age
                    grade
                }
            }
            """;

        Student created = graphQlTester.document(createMutation)
                .execute()
                .path("createStudent")
                .entity(Student.class)
                .get();

        createdStudentId = created.getId();
    }

    @Test
    void allStudentsQuery_ReturnsListOfStudents() {
        // language=GraphQL
        String query = """
            query {
                allStudents {
                    id
                    name
                    age
                    grade
                }
            }
            """;

        graphQlTester.document(query)
                .execute()
                .path("allStudents")
                .entityList(Student.class)
                .satisfies(students -> {
                    assertThat(students).isNotEmpty();
                });
    }

    @Test
    void studentByIdQuery_ReturnsStudent() {
        // language=GraphQL
        String query = String.format("""
            query {
                studentById(id: "%s") {
                    id
                    name
                    age
                    grade
                }
            }
            """, createdStudentId);

        graphQlTester.document(query)
                .execute()
                .path("studentById")
                .entity(Student.class)
                .satisfies(student -> {
                    assertThat(student.getId()).isEqualTo(createdStudentId);
                    assertThat(student.getName()).isEqualTo("Rockstar");
                    assertThat(student.getAge()).isEqualTo(20);
                    assertThat(student.getGrade()).isEqualTo("S");
                });
    }

    @Test
    void createStudentMutation_CreatesAndReturnsStudent() {
        // language=GraphQL
        String mutation = """
            mutation {
                createStudent(name: "Rockstar", age: 20, grade: "S") {
                    id
                    name
                    age
                    grade
                }
            }
            """;

        graphQlTester.document(mutation)
                .execute()
                .path("createStudent")
                .entity(Student.class)
                .satisfies(student -> {
                    assertThat(student.getName()).isEqualTo("Rockstar");
                    assertThat(student.getAge()).isEqualTo(20);
                    assertThat(student.getGrade()).isEqualTo("S");
                });
    }

}
