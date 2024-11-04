package com.kent.gmail.com.runtime.request;

import java.time.OffsetDateTime;
import java.util.Set;

/** Object Used to List Person */
public class PersonFilter extends BaseFilter {

  private Set<String> address;

  private String addressLike;

  private OffsetDateTime birthdateEnd;

  private OffsetDateTime birthdateStart;

  private Set<String> email;

  private String emailLike;

  private Set<String> phoneNumber;

  private String phoneNumberLike;

  private Set<String> socialSecurityNumber;

  private String socialSecurityNumberLike;

  public PersonFilter() {}

  /**
   * @return address
   */
  public Set<String> getAddress() {
    return this.address;
  }

  /**
   * @param address to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setAddress(Set<String> address) {
    this.address = address;
    return (T) this;
  }

  /**
   * @return addressLike
   */
  public String getAddressLike() {
    return this.addressLike;
  }

  /**
   * @param addressLike to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setAddressLike(String addressLike) {
    this.addressLike = addressLike;
    return (T) this;
  }

  /**
   * @return birthdateEnd
   */
  public OffsetDateTime getBirthdateEnd() {
    return this.birthdateEnd;
  }

  /**
   * @param birthdateEnd to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setBirthdateEnd(OffsetDateTime birthdateEnd) {
    this.birthdateEnd = birthdateEnd;
    return (T) this;
  }

  /**
   * @return birthdateStart
   */
  public OffsetDateTime getBirthdateStart() {
    return this.birthdateStart;
  }

  /**
   * @param birthdateStart to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setBirthdateStart(OffsetDateTime birthdateStart) {
    this.birthdateStart = birthdateStart;
    return (T) this;
  }

  /**
   * @return email
   */
  public Set<String> getEmail() {
    return this.email;
  }

  /**
   * @param email to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setEmail(Set<String> email) {
    this.email = email;
    return (T) this;
  }

  /**
   * @return emailLike
   */
  public String getEmailLike() {
    return this.emailLike;
  }

  /**
   * @param emailLike to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setEmailLike(String emailLike) {
    this.emailLike = emailLike;
    return (T) this;
  }

  /**
   * @return phoneNumber
   */
  public Set<String> getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * @param phoneNumber to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setPhoneNumber(Set<String> phoneNumber) {
    this.phoneNumber = phoneNumber;
    return (T) this;
  }

  /**
   * @return phoneNumberLike
   */
  public String getPhoneNumberLike() {
    return this.phoneNumberLike;
  }

  /**
   * @param phoneNumberLike to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setPhoneNumberLike(String phoneNumberLike) {
    this.phoneNumberLike = phoneNumberLike;
    return (T) this;
  }

  /**
   * @return socialSecurityNumber
   */
  public Set<String> getSocialSecurityNumber() {
    return this.socialSecurityNumber;
  }

  /**
   * @param socialSecurityNumber to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setSocialSecurityNumber(Set<String> socialSecurityNumber) {
    this.socialSecurityNumber = socialSecurityNumber;
    return (T) this;
  }

  /**
   * @return socialSecurityNumberLike
   */
  public String getSocialSecurityNumberLike() {
    return this.socialSecurityNumberLike;
  }

  /**
   * @param socialSecurityNumberLike to set
   * @return PersonFilter
   */
  public <T extends PersonFilter> T setSocialSecurityNumberLike(String socialSecurityNumberLike) {
    this.socialSecurityNumberLike = socialSecurityNumberLike;
    return (T) this;
  }
}
