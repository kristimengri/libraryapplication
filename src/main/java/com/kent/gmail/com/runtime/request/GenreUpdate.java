package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.Genre;
import com.kent.gmail.com.runtime.validation.IdValid;
import com.kent.gmail.com.runtime.validation.Update;

/** Object Used to Update Genre */
@IdValid.List({
  @IdValid(
      field = "id",
      fieldType = Genre.class,
      targetField = "genre",
      groups = {Update.class})
})
public class GenreUpdate extends GenreCreate {

  @JsonIgnore private Genre genre;

  private String id;

  public GenreUpdate() {}

  /**
   * @return genre
   */
  @JsonIgnore
  public Genre getGenre() {
    return this.genre;
  }

  /**
   * @param genre to set
   * @return GenreUpdate
   */
  public <T extends GenreUpdate> T setGenre(Genre genre) {
    this.genre = genre;
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
   * @return GenreUpdate
   */
  public <T extends GenreUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }
}
