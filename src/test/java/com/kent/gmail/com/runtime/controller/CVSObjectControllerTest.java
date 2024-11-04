package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.AppInit;
import com.kent.gmail.com.runtime.model.CVSObject;
import com.kent.gmail.com.runtime.request.CVSObjectCreate;
import com.kent.gmail.com.runtime.request.CVSObjectFilter;
import com.kent.gmail.com.runtime.request.CVSObjectUpdate;
import com.kent.gmail.com.runtime.request.LoginRequest;
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
public class CVSObjectControllerTest {

  private CVSObject testCVSObject;

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
  public void testCVSObjectCreate() {
    CVSObjectCreate request = new CVSObjectCreate();

    request.setBucketId("test-string");
    request.setObjectId("test-string");
    request.setUploadDate(OffsetDateTime.now());

    ResponseEntity<CVSObject> response =
        this.restTemplate.postForEntity("/CVSObject/createCVSObject", request, CVSObject.class);

    Assertions.assertEquals(200, response.getStatusCodeValue());
    testCVSObject = response.getBody();
    assertCVSObject(request, testCVSObject);
  }

  @Test
  @Order(2)
  public void testListAllCVSObjects() {
    CVSObjectFilter request = new CVSObjectFilter();
    ParameterizedTypeReference<PaginationResponse<CVSObject>> t =
        new ParameterizedTypeReference<>() {};

    ResponseEntity<PaginationResponse<CVSObject>> response =
        this.restTemplate.exchange(
            "/CVSObject/getAllCVSObjects", HttpMethod.POST, new HttpEntity<>(request), t);

    Assertions.assertEquals(200, response.getStatusCodeValue());
    PaginationResponse<CVSObject> body = response.getBody();
    Assertions.assertNotNull(body);
    List<CVSObject> CVSObjects = body.getList();
    Assertions.assertNotEquals(0, CVSObjects.size());
    Assertions.assertTrue(
        CVSObjects.stream().anyMatch(f -> f.getId().equals(testCVSObject.getId())));
  }

  public void assertCVSObject(CVSObjectCreate request, CVSObject testCVSObject) {
    Assertions.assertNotNull(testCVSObject);
    if (request.getBucketId() != null) {
      Assertions.assertEquals(request.getBucketId(), testCVSObject.getBucketId());
    }
    if (request.getObjectId() != null) {
      Assertions.assertEquals(request.getObjectId(), testCVSObject.getObjectId());
    }
    if (request.getUploadDate() != null) {
      Assertions.assertEquals(
          request.getUploadDate().atZoneSameInstant(ZoneId.systemDefault()),
          testCVSObject.getUploadDate().atZoneSameInstant(ZoneId.systemDefault()));
    }
  }

  @Test
  @Order(3)
  public void testCVSObjectUpdate() {
    CVSObjectUpdate request = new CVSObjectUpdate().setId(testCVSObject.getId());

    ResponseEntity<CVSObject> response =
        this.restTemplate.exchange(
            "/CVSObject/updateCVSObject",
            HttpMethod.PUT,
            new HttpEntity<>(request),
            CVSObject.class);

    Assertions.assertEquals(200, response.getStatusCodeValue());
    testCVSObject = response.getBody();
    assertCVSObject(request, testCVSObject);
  }
}