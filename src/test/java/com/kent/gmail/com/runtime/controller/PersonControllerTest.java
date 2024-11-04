package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.AppInit;
import com.kent.gmail.com.runtime.model.Person;
import com.kent.gmail.com.runtime.request.LoginRequest;
import com.kent.gmail.com.runtime.request.PersonCreate;
import com.kent.gmail.com.runtime.request.PersonFilter;
import com.kent.gmail.com.runtime.request.PersonUpdate;
import com.kent.gmail.com.runtime.response.PaginationResponse;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.*;
import org.testcontainers.containers.PostgreSQLContainer;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AppInit.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(
    replace = AutoConfigureTestDatabase.Replace.NONE) // deactivate the default behaviour
public class PersonControllerTest {

  private Person testPerson;

  @Autowired private TestRestTemplate restTemplate;

  private static final PostgreSQLContainer postgresqlContainer =
      new PostgreSQLContainer("postgres:15")
          .withDatabaseName("flexicore-test")
          .withUsername("flexicore")
          .withPassword("flexicore");

  static {
    postgresqlContainer.start();
  }

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgresqlContainer::getUsername);
    registry.add("spring.datasource.password", postgresqlContainer::getPassword);
  }

  @BeforeAll
  public void init() {
    ResponseEntity<Object> authenticationResponse =
        this.restTemplate.postForEntity(
            "/login",
            new LoginRequest().setUsername("admin@flexicore.com").setPassword("admin"),
            Object.class);

    String authenticationKey =
        authenticationResponse.getHeaders().get(HttpHeaders.AUTHORIZATION).stream()
            .findFirst()
            .orElse(null);
    restTemplate
        .getRestTemplate()
        .setInterceptors(
            Collections.singletonList(
                (request, body, execution) -> {
                  request.getHeaders().add("Authorization", "Bearer " + authenticationKey);
                  return execution.execute(request, body);
                }));
  }

  @Test
  @Order(1)
  public void testPersonCreate() {
    PersonCreate request = new PersonCreate();

    request.setBirthdate(OffsetDateTime.now());
    request.setSocialSecurityNumber("test-string");
    request.setPhoneNumber("test-string");
    request.setEmail("test-string");
    request.setAddress("test-string");

    ResponseEntity<Person> response =
        this.restTemplate.postForEntity("/Person/createPerson", request, Person.class);

    Assertions.assertEquals(200, response.getStatusCodeValue());
    testPerson = response.getBody();
    assertPerson(request, testPerson);
  }

  @Test
  @Order(2)
  public void testListAllPersons() {
    PersonFilter request = new PersonFilter();
    ParameterizedTypeReference<PaginationResponse<Person>> t =
        new ParameterizedTypeReference<>() {};

    ResponseEntity<PaginationResponse<Person>> response =
        this.restTemplate.exchange(
            "/Person/getAllPersons", HttpMethod.POST, new HttpEntity<>(request), t);

    Assertions.assertEquals(200, response.getStatusCodeValue());
    PaginationResponse<Person> body = response.getBody();
    Assertions.assertNotNull(body);
    List<Person> Persons = body.getList();
    Assertions.assertNotEquals(0, Persons.size());
    Assertions.assertTrue(Persons.stream().anyMatch(f -> f.getId().equals(testPerson.getId())));
  }

  public void assertPerson(PersonCreate request, Person testPerson) {
    Assertions.assertNotNull(testPerson);
    if (request.getBirthdate() != null) {
      Assertions.assertEquals(
          request.getBirthdate().atZoneSameInstant(ZoneId.systemDefault()),
          testPerson.getBirthdate().atZoneSameInstant(ZoneId.systemDefault()));
    }
    if (request.getSocialSecurityNumber() != null) {
      Assertions.assertEquals(
          request.getSocialSecurityNumber(), testPerson.getSocialSecurityNumber());
    }
    if (request.getPhoneNumber() != null) {
      Assertions.assertEquals(request.getPhoneNumber(), testPerson.getPhoneNumber());
    }
    if (request.getEmail() != null) {
      Assertions.assertEquals(request.getEmail(), testPerson.getEmail());
    }
    if (request.getAddress() != null) {
      Assertions.assertEquals(request.getAddress(), testPerson.getAddress());
    }
  }

  @Test
  @Order(3)
  public void testPersonUpdate() {
    PersonUpdate request = new PersonUpdate().setId(testPerson.getId());

    ResponseEntity<Person> response =
        this.restTemplate.exchange(
            "/Person/updatePerson", HttpMethod.PUT, new HttpEntity<>(request), Person.class);

    Assertions.assertEquals(200, response.getStatusCodeValue());
    testPerson = response.getBody();
    assertPerson(request, testPerson);
  }
}
