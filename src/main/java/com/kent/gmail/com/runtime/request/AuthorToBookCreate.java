package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.Author;
import com.kent.gmail.com.runtime.model.Book;
import com.kent.gmail.com.runtime.validation.Create;
import com.kent.gmail.com.runtime.validation.IdValid;
import com.kent.gmail.com.runtime.validation.Update;

/** Object Used to Create AuthorToBook */
@IdValid.List({
  @IdValid(
      field = "authorId",
      fieldType = Author.class,
      targetField = "author",
      groups = {Update.class, Create.class}),
  @IdValid(
      field = "bookId",
      fieldType = Book.class,
      targetField = "book",
      groups = {Update.class, Create.class})
})
public class AuthorToBookCreate extends BaseCreate {

  @JsonIgnore private Author author;

  private String authorId;

  @JsonIgnore private Book book;

  private String bookId;

  private Boolean mainAuthor;

  public AuthorToBookCreate() {}

  /**
   * @return author
   */
  @JsonIgnore
  public Author getAuthor() {
    return this.author;
  }

  /**
   * @param author to set
   * @return AuthorToBookCreate
   */
  public <T extends AuthorToBookCreate> T setAuthor(Author author) {
    this.author = author;
    return (T) this;
  }

  /**
   * @return authorId
   */
  public String getAuthorId() {
    return this.authorId;
  }

  /**
   * @param authorId to set
   * @return AuthorToBookCreate
   */
  public <T extends AuthorToBookCreate> T setAuthorId(String authorId) {
    this.authorId = authorId;
    return (T) this;
  }

  /**
   * @return book
   */
  @JsonIgnore
  public Book getBook() {
    return this.book;
  }

  /**
   * @param book to set
   * @return AuthorToBookCreate
   */
  public <T extends AuthorToBookCreate> T setBook(Book book) {
    this.book = book;
    return (T) this;
  }

  /**
   * @return bookId
   */
  public String getBookId() {
    return this.bookId;
  }

  /**
   * @param bookId to set
   * @return AuthorToBookCreate
   */
  public <T extends AuthorToBookCreate> T setBookId(String bookId) {
    this.bookId = bookId;
    return (T) this;
  }

  /**
   * @return mainAuthor
   */
  public Boolean getMainAuthor() {
    return this.mainAuthor;
  }

  /**
   * @param mainAuthor to set
   * @return AuthorToBookCreate
   */
  public <T extends AuthorToBookCreate> T setMainAuthor(Boolean mainAuthor) {
    this.mainAuthor = mainAuthor;
    return (T) this;
  }
}
