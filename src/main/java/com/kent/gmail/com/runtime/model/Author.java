package com.kent.gmail.com.runtime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Author extends Base {

  @OneToMany(targetEntity = AuthorToBook.class)
  @JsonIgnore
  private List<AuthorToBook> authorAuthorToBooks;

  public Author() {}

  /**
   * @return authorAuthorToBooks
   */
  @OneToMany(targetEntity = AuthorToBook.class)
  @JsonIgnore
  public List<AuthorToBook> getAuthorAuthorToBooks() {
    return this.authorAuthorToBooks;
  }

  /**
   * @param authorAuthorToBooks to set
   * @return Author
   */
  public <T extends Author> T setAuthorAuthorToBooks(List<AuthorToBook> authorAuthorToBooks) {
    this.authorAuthorToBooks = authorAuthorToBooks;
    return (T) this;
  }
}
