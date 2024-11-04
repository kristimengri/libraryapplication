package com.kent.gmail.com.runtime.request;

import java.util.Set;

/** Object Used to List Lender */
public class LenderFilter extends PersonFilter {

  private Set<Boolean> blocked;

  public LenderFilter() {}

  /**
   * @return blocked
   */
  public Set<Boolean> getBlocked() {
    return this.blocked;
  }

  /**
   * @param blocked to set
   * @return LenderFilter
   */
  public <T extends LenderFilter> T setBlocked(Set<Boolean> blocked) {
    this.blocked = blocked;
    return (T) this;
  }
}