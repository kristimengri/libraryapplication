package com.kent.gmail.com.runtime.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kent.gmail.com.runtime.model.Lender;
import com.kent.gmail.com.runtime.validation.IdValid;
import com.kent.gmail.com.runtime.validation.Update;

/** Object Used to Update Lender */
@IdValid.List({
  @IdValid(
      field = "id",
      fieldType = Lender.class,
      targetField = "lender",
      groups = {Update.class})
})
public class LenderUpdate extends LenderCreate {

  private String id;

  @JsonIgnore private Lender lender;

  public LenderUpdate() {}

  /**
   * @return id
   */
  public String getId() {
    return this.id;
  }

  /**
   * @param id to set
   * @return LenderUpdate
   */
  public <T extends LenderUpdate> T setId(String id) {
    this.id = id;
    return (T) this;
  }

  /**
   * @return lender
   */
  @JsonIgnore
  public Lender getLender() {
    return this.lender;
  }

  /**
   * @param lender to set
   * @return LenderUpdate
   */
  public <T extends LenderUpdate> T setLender(Lender lender) {
    this.lender = lender;
    return (T) this;
  }
}
