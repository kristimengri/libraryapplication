package com.kent.gmail.com.runtime.request;

import java.time.OffsetDateTime;

/** Object Used to Create Person */
public class PersonCreate extends BaseCreate {

  private String address;

  private OffsetDateTime birthdate;

  private String email;

  private String phoneNumber;

  private String socialSecurityNumber;

  public PersonCreate() {}

  /**
   * @return address
   */
  public String getAddress() {
    return this.address;
  }

  /**
   * @param address to set
   * @return PersonCreate
   */
  public <T extends PersonCreate> T setAddress(String address) {
    this.address = address;
    return (T) this;
  }

  /**
   * @return birthdate
   */
  public OffsetDateTime getBirthdate() {
    return this.birthdate;
  }

  /**
   * @param birthdate to set
   * @return PersonCreate
   */
  public <T extends PersonCreate> T setBirthdate(OffsetDateTime birthdate) {
    this.birthdate = birthdate;
    return (T) this;
  }

  /**
   * @return email
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * @param email to set
   * @return PersonCreate
   */
  public <T extends PersonCreate> T setEmail(String email) {
    this.email = email;
    return (T) this;
  }

  /**
   * @return phoneNumber
   */
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * @param phoneNumber to set
   * @return PersonCreate
   */
  public <T extends PersonCreate> T setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return (T) this;
  }

  /**
   * @return socialSecurityNumber
   */
  public String getSocialSecurityNumber() {
    return this.socialSecurityNumber;
  }

  /**
   * @param socialSecurityNumber to set
   * @return PersonCreate
   */
  public <T extends PersonCreate> T setSocialSecurityNumber(String socialSecurityNumber) {
    this.socialSecurityNumber = socialSecurityNumber;
    return (T) this;
  }
}
