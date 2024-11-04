package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.CVSObject;
import com.kent.gmail.com.runtime.validation.IdValid;
import com.kent.gmail.com.runtime.validation.Update;

/** Object Used to Update CVSObject */
@IdValid.List({
  @IdValid(
      field = "id",
      fieldType = CVSObject.class,
      targetField = "cVSObject",
      groups = {Update.class})
})
public class CVSObjectUpdate extends CVSObjectCreate {

  @JsonIgnore private CVSObject cVSObject;

  private String id;

  public CVSObjectUpdate() {}

  /**
   * @return cVSObject
   */
  @JsonIgnore
  public CVSObject getCVSObject() {
    return this.cVSObject;
  }

  /**
   * @param cVSObject to set
   * @return CVSObjectUpdate
   */
  public <T extends CVSObjectUpdate> T setCVSObject(CVSObject cVSObject) {
    this.cVSObject = cVSObject;
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
   * @return CVSObjectUpdate
   */
  public <T extends CVSObjectUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }
}
