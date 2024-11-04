package com.kent.gmail.com.runtime.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class AuthorToBook extends Base {

  @ManyToOne(targetEntity = Author.class)
  private Author author;

  @ManyToOne(targetEntity = Book.class)
  private Book book;

  private boolean mainAuthor;

  public AuthorToBook() {}

  /**
   * @return author
   */
  @ManyToOne(targetEntity = Author.class)
  public Author getAuthor() {
    return this.author;
  }

  /**
   * @param author to set
   * @return AuthorToBook
   */
  public <T extends AuthorToBook> T setAuthor(Author author) {
    this.author = author;
    return (T) this;
  }

  /**
   * @return book
   */
  @ManyToOne(targetEntity = Book.class)
  public Book getBook() {
    return this.book;
  }

  /**
   * @param book to set
   * @return AuthorToBook
   */
  public <T extends AuthorToBook> T setBook(Book book) {
    this.book = book;
    return (T) this;
  }

  /**
   * @return mainAuthor
   */
  public boolean isMainAuthor() {
    return this.mainAuthor;
  }

  /**
   * @param mainAuthor to set
   * @return AuthorToBook
   */
  public <T extends AuthorToBook> T setMainAuthor(boolean mainAuthor) {
    this.mainAuthor = mainAuthor;
    return (T) this;
  }
}
