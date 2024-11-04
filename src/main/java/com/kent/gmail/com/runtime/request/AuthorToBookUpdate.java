package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.AuthorToBook;
import com.kent.gmail.com.runtime.validation.IdValid;
import com.kent.gmail.com.runtime.validation.Update;

/** Object Used to Update AuthorToBook */
@IdValid.List({
  @IdValid(
      field = "id",
      fieldType = AuthorToBook.class,
      targetField = "authorToBook",
      groups = {Update.class})
})
public class AuthorToBookUpdate extends AuthorToBookCreate {

  @JsonIgnore private AuthorToBook authorToBook;

  private String id;

  public AuthorToBookUpdate() {}

  /**
   * @return authorToBook
   */
  @JsonIgnore
  public AuthorToBook getAuthorToBook() {
    return this.authorToBook;
  }

  /**
   * @param authorToBook to set
   * @return AuthorToBookUpdate
   */
  public <T extends AuthorToBookUpdate> T setAuthorToBook(AuthorToBook authorToBook) {
    this.authorToBook = authorToBook;
    return (T) this;
  }

  /**
   * @return id
   */
  public String getId() {
    return this.id;
  }

  /**
   * @param id to set
   * @return AuthorToBookUpdate
   */
  public <T extends AuthorToBookUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }
}
