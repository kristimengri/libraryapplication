package com.kent.gmail.com.runtime.model;

import jakarta.persistence.Entity;

@Entity
public class BookInstance extends Base {

  private String serialNumber;

  private boolean blocked;

  public BookInstance() {}

  /**
   * @return serialNumber
   */
  public String getSerialNumber() {
    return this.serialNumber;
  }

  /**
   * @param serialNumber to set
   * @return BookInstance
   */
  public <T extends BookInstance> T setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
    return (T) this;
  }

  /**
   * @return blocked
   */
  public boolean isBlocked() {
    return this.blocked;
  }

  /**
   * @param blocked to set
   * @return BookInstance
   */
  public <T extends BookInstance> T setBlocked(boolean blocked) {
    this.blocked = blocked;
    return (T) this;
  }
}
