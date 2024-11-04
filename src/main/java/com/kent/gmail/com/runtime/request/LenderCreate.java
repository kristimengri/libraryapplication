package com.kent.gmail.com.runtime.request;

/** Object Used to Create Lender */
public class LenderCreate extends PersonCreate {

  private Boolean blocked;

  public LenderCreate() {}

  /**
   * @return blocked
   */
  public Boolean getBlocked() {
    return this.blocked;
  }

  /**
   * @param blocked to set
   * @return LenderCreate
   */
  public <T extends LenderCreate> T setBlocked(Boolean blocked) {
    this.blocked = blocked;
    return (T) this;
  }
}
