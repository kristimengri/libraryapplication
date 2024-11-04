package com.kent.gmail.com.runtime.controller;

import com.kent.gmail.com.runtime.model.Book;
import com.kent.gmail.com.runtime.request.BookCreate;
import com.kent.gmail.com.runtime.request.BookFilter;
import com.kent.gmail.com.runtime.request.BookUpdate;
import com.kent.gmail.com.runtime.response.PaginationResponse;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import com.kent.gmail.com.runtime.service.BookService;
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
@RequestMapping("Book")
@Tag(name = "Book")
public class BookController {

  @Autowired private BookService bookService;

  @DeleteMapping("{id}")
  @Operation(summary = "deleteBook", description = "Deletes Book")
  public Book deleteBook(@PathVariable("id") String id, Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return bookService.deleteBook(id, securityContext);
  }

  @PostMapping("getAllBooks")
  @Operation(summary = "getAllBooks", description = "lists Books")
  public PaginationResponse<Book> getAllBooks(
      @Valid @RequestBody BookFilter bookFilter, Authentication authentication) {
    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return bookService.getAllBooks(bookFilter, securityContext);
  }

  @PutMapping("updateBook")
  @Operation(summary = "updateBook", description = "Updates Book")
  public Book updateBook(
      @Validated(Update.class) @RequestBody BookUpdate bookUpdate, Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return bookService.updateBook(bookUpdate, securityContext);
  }

  @PostMapping("createBook")
  @Operation(summary = "createBook", description = "Creates Book")
  public Book createBook(
      @Validated(Create.class) @RequestBody BookCreate bookCreate, Authentication authentication) {

    UserSecurityContext securityContext = (UserSecurityContext) authentication.getPrincipal();

    return bookService.createBook(bookCreate, securityContext);
  }
}
