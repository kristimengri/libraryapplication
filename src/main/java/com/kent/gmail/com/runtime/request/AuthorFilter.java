package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.AuthorToBook;
import com.kent.gmail.com.runtime.validation.IdValid;
import java.util.List;
import java.util.Set;

/** Object Used to List Author */
@IdValid.List({
  @IdValid(
      field = "authorAuthorToBooksIds",
      fieldType = AuthorToBook.class,
      targetField = "authorAuthorToBookses",
      groups = {})
})
public class AuthorFilter extends BaseFilter {

  private Set<String> authorAuthorToBooksIds;

  @JsonIgnore private List<AuthorToBook> authorAuthorToBookses;

  public AuthorFilter() {}

  /**
   * @return authorAuthorToBooksIds
   */
  public Set<String> getAuthorAuthorToBooksIds() {
    return this.authorAuthorToBooksIds;
  }

  /**
   * @param authorAuthorToBooksIds to set
   * @return AuthorFilter
   */
  public <T extends AuthorFilter> T setAuthorAuthorToBooksIds(Set<String> authorAuthorToBooksIds) {
    this.authorAuthorToBooksIds = authorAuthorToBooksIds;
    return (T) this;
  }

  /**
   * @return authorAuthorToBookses
   */
  @JsonIgnore
  public List<AuthorToBook> getAuthorAuthorToBookses() {
    return this.authorAuthorToBookses;
  }

  /**
   * @param authorAuthorToBookses to set
   * @return AuthorFilter
   */
  public <T extends AuthorFilter> T setAuthorAuthorToBookses(
      List<AuthorToBook> authorAuthorToBookses) {
    this.authorAuthorToBookses = authorAuthorToBookses;
    return (T) this;
  }
}
