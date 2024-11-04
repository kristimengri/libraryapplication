package com.kent.gmail.com.runtime.request;

/** Object Used to Create BookInstance */
public class BookInstanceCreate extends BaseCreate {

  private Boolean blocked;

  private String serialNumber;

  public BookInstanceCreate() {}

  /**
   * @return blocked
   */
  public Boolean getBlocked() {
    return this.blocked;
  }

  /**
   * @param blocked to set
   * @return BookInstanceCreate
   */
  public <T extends BookInstanceCreate> T setBlocked(Boolean blocked) {
    this.blocked = blocked;
    return (T) this;
  }

  /**
   * @return serialNumber
   */
  public String getSerialNumber() {
    return this.serialNumber;
  }

  /**
   * @param serialNumber to set
   * @return BookInstanceCreate
   */
  public <T extends BookInstanceCreate> T setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
    return (T) this;
  }
}
