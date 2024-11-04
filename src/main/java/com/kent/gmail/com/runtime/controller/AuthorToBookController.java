package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.model.AuthorToBook;
import com.kent.gmail.com.runtime.request.AuthorToBookCreate;
import com.kent.gmail.com.runtime.request.AuthorToBookFilter;
import com.kent.gmail.com.runtime.request.AuthorToBookUpdate;
import com.kent.gmail.com.runtime.response.PaginationResponse;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import com.kent.gmail.com.runtime.service.AuthorToBookService;
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
@RequestMapping("AuthorToBook")
@Tag(name = "AuthorToBook")
public class AuthorToBookController {

  @Autowired private AuthorToBookService authorToBookService;

  @DeleteMapping("{id}")
  @Operation(summary = "deleteAuthorToBook", description = "Deletes AuthorToBook")
  public AuthorToBook deleteAuthorToBook(
      @PathVariable("id") String id, Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return authorToBookService.deleteAuthorToBook(id, securityContext);
  }

  @PostMapping("getAllAuthorToBooks")
  @Operation(summary = "getAllAuthorToBooks", description = "lists AuthorToBooks")
  public PaginationResponse<AuthorToBook> getAllAuthorToBooks(
      @Valid @RequestBody AuthorToBookFilter authorToBookFilter, Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return authorToBookService.getAllAuthorToBooks(authorToBookFilter, securityContext);
  }

  @PutMapping("updateAuthorToBook")
  @Operation(summary = "updateAuthorToBook", description = "Updates AuthorToBook")
  public AuthorToBook updateAuthorToBook(
      @Validated(Update.class) @RequestBody AuthorToBookUpdate authorToBookUpdate,
      Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return authorToBookService.updateAuthorToBook(authorToBookUpdate, securityContext);
  }

  @PostMapping("createAuthorToBook")
  @Operation(summary = "createAuthorToBook", description = "Creates AuthorToBook")
  public AuthorToBook createAuthorToBook(
      @Validated(Create.class) @RequestBody AuthorToBookCreate authorToBookCreate,
      Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return authorToBookService.createAuthorToBook(authorToBookCreate, securityContext);
  }
}
