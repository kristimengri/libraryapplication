package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.AppInit;
import com.kent.gmail.com.runtime.model.Author;
import com.kent.gmail.com.runtime.model.AuthorToBook;
import com.kent.gmail.com.runtime.model.Book;
import com.kent.gmail.com.runtime.request.AuthorToBookCreate;
import com.kent.gmail.com.runtime.request.AuthorToBookFilter;
import com.kent.gmail.com.runtime.request.AuthorToBookUpdate;
import com.kent.gmail.com.runtime.request.LoginRequest;
import com.kent.gmail.com.runtime.response.PaginationResponse;
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
public class AuthorToBookControllerTest {

  private AuthorToBook testAuthorToBook;

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

  @Autowired private Author author;
  @Autowired private Book book;

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
  public void testAuthorToBookCreate() {
    AuthorToBookCreate request = new AuthorToBookCreate();

    request.setAuthorId(this.author.getId());
    request.setMainAuthor(true);
    request.setBookId(this.book.getId());

    ResponseEntity<AuthorToBook> response =
        this.restTemplate.postForEntity(
            "/AuthorToBook/createAuthorToBook", request, AuthorToBook.class);

    Assertions.assertEquals(200, response.getStatusCodeValue());
    testAuthorToBook = response.getBody();
    assertAuthorToBook(request, testAuthorToBook);
  }

  @Test
  @Order(2)
  public void testListAllAuthorToBooks() {
    AuthorToBookFilter request = new AuthorToBookFilter();
    ParameterizedTypeReference<PaginationResponse<AuthorToBook>> t =
        new ParameterizedTypeReference<>() {};

    ResponseEntity<PaginationResponse<AuthorToBook>> response =
        this.restTemplate.exchange(
            "/AuthorToBook/getAllAuthorToBooks", HttpMethod.POST, new HttpEntity<>(request), t);

    Assertions.assertEquals(200, response.getStatusCodeValue());
    PaginationResponse<AuthorToBook> body = response.getBody();
    Assertions.assertNotNull(body);
    List<AuthorToBook> AuthorToBooks = body.getList();
    Assertions.assertNotEquals(0, AuthorToBooks.size());
    Assertions.assertTrue(
        AuthorToBooks.stream().anyMatch(f -> f.getId().equals(testAuthorToBook.getId())));
  }

  public void assertAuthorToBook(AuthorToBookCreate request, AuthorToBook testAuthorToBook) {
    Assertions.assertNotNull(testAuthorToBook);
    if (request.getAuthorId() != null) {
      Assertions.assertNotNull(testAuthorToBook.getAuthor());
      Assertions.assertEquals(request.getAuthorId(), testAuthorToBook.getAuthor().getId());
    }
    if (request.getMainAuthor() != null) {
      Assertions.assertEquals(request.getMainAuthor(), testAuthorToBook.isMainAuthor());
    }
    if (request.getBookId() != null) {
      Assertions.assertNotNull(testAuthorToBook.getBook());
      Assertions.assertEquals(request.getBookId(), testAuthorToBook.getBook().getId());
    }
  }

  @Test
  @Order(3)
  public void testAuthorToBookUpdate() {
    AuthorToBookUpdate request = new AuthorToBookUpdate().setId(testAuthorToBook.getId());

    ResponseEntity<AuthorToBook> response =
        this.restTemplate.exchange(
            "/AuthorToBook/updateAuthorToBook",
            HttpMethod.PUT,
            new HttpEntity<>(request),
            AuthorToBook.class);

    Assertions.assertEquals(200, response.getStatusCodeValue());
    testAuthorToBook = response.getBody();
    assertAuthorToBook(request, testAuthorToBook);
  }
}
