package com.kent.gmail.com.runtime.request;

import java.util.Set;

/** Object Used to List BookInstance */
public class BookInstanceFilter extends BaseFilter {

  private Set<Boolean> blocked;

  private Set<String> serialNumber;

  private String serialNumberLike;

  public BookInstanceFilter() {}

  /**
   * @return blocked
   */
  public Set<Boolean> getBlocked() {
    return this.blocked;
  }

  /**
   * @param blocked to set
   * @return BookInstanceFilter
   */
  public <T extends BookInstanceFilter> T setBlocked(Set<Boolean> blocked) {
    this.blocked = blocked;
    return (T) this;
  }

  /**
   * @return serialNumber
   */
  public Set<String> getSerialNumber() {
    return this.serialNumber;
  }

  /**
   * @param serialNumber to set
   * @return BookInstanceFilter
   */
  public <T extends BookInstanceFilter> T setSerialNumber(Set<String> serialNumber) {
    this.serialNumber = serialNumber;
    return (T) this;
  }

  /**
   * @return serialNumberLike
   */
  public String getSerialNumberLike() {
    return this.serialNumberLike;
  }

  /**
   * @param serialNumberLike to set
   * @return BookInstanceFilter
   */
  public <T extends BookInstanceFilter> T setSerialNumberLike(String serialNumberLike) {
    this.serialNumberLike = serialNumberLike;
    return (T) this;
  }
}
