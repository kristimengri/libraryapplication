package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.BookInstance;
import com.kent.gmail.com.runtime.validation.IdValid;
import com.kent.gmail.com.runtime.validation.Update;

/** Object Used to Update BookInstance */
@IdValid.List({
  @IdValid(
      field = "id",
      fieldType = BookInstance.class,
      targetField = "bookInstance",
      groups = {Update.class})
})
public class BookInstanceUpdate extends BookInstanceCreate {

  @JsonIgnore private BookInstance bookInstance;

  private String id;

  public BookInstanceUpdate() {}

  /**
   * @return bookInstance
   */
  @JsonIgnore
  public BookInstance getBookInstance() {
    return this.bookInstance;
  }

  /**
   * @param bookInstance to set
   * @return BookInstanceUpdate
   */
  public <T extends BookInstanceUpdate> T setBookInstance(BookInstance bookInstance) {
    this.bookInstance = bookInstance;
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
   * @return BookInstanceUpdate
   */
  public <T extends BookInstanceUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }
}
