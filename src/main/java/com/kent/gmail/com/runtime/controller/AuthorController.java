package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.model.Author;
import com.kent.gmail.com.runtime.request.AuthorCreate;
import com.kent.gmail.com.runtime.request.AuthorFilter;
import com.kent.gmail.com.runtime.request.AuthorUpdate;
import com.kent.gmail.com.runtime.response.PaginationResponse;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import com.kent.gmail.com.runtime.service.AuthorService;
import com.kent.gmail.com.runtime.validation.Create;
import com.kent.gmail.com.runtime.validation.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("Author")
@Tag(name = "Author")
public class AuthorController {

  @Autowired private AuthorService authorService;

  @DeleteMapping("{id}")
  @Operation(summary = "deleteAuthor", description = "Deletes Author")
  public Author deleteAuthor(@PathVariable("id") String id, Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return authorService.deleteAuthor(id, securityContext);
  }

  @PostMapping("getAllAuthors")
  @Operation(summary = "getAllAuthors", description = "lists Authors")
  public PaginationResponse<Author> getAllAuthors(
      @Valid @RequestBody AuthorFilter authorFilter, Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return authorService.getAllAuthors(authorFilter, securityContext);
  }

  @PutMapping("updateAuthor")
  @Operation(summary = "updateAuthor", description = "Updates Author")
  public Author updateAuthor(
      @Validated(Update.class) @RequestBody AuthorUpdate authorUpdate,
      Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return authorService.updateAuthor(authorUpdate, securityContext);
  }

  @PostMapping("createAuthor")
  @Operation(summary = "createAuthor", description = "Creates Author")
  public Author createAuthor(
      @Validated(Create.class) @RequestBody AuthorCreate authorCreate,
      Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return authorService.createAuthor(authorCreate, securityContext);
  }
}
