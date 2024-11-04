package com.kent.gmail.com.runtime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Genre extends Base {

  @OneToMany(targetEntity = BookToGenre.class)
  @JsonIgnore
  private List<BookToGenre> genreBookToGenres;

  public Genre() {}

  /**
   * @return genreBookToGenres
   */
  @OneToMany(targetEntity = BookToGenre.class)
  @JsonIgnore
  public List<BookToGenre> getGenreBookToGenres() {
    return this.genreBookToGenres;
  }

  /**
   * @param genreBookToGenres to set
   * @return Genre
   */
  public <T extends Genre> T setGenreBookToGenres(List<BookToGenre> genreBookToGenres) {
    this.genreBookToGenres = genreBookToGenres;
    return (T) this;
  }
}
