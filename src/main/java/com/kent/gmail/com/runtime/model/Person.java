package com.kent.gmail.com.runtime.model;

import jakarta.persistence.MappedSuperclass;
import java.time.OffsetDateTime;

@MappedSuperclass
public class Person extends Base {

  private String email;

  private String address;

  private String phoneNumber;

  private OffsetDateTime birthdate;

  private String socialSecurityNumber;

  public Person() {}

  /**
   * @return email
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * @param email to set
   * @return Person
   */
  public <T extends Person> T setEmail(String email) {
    this.email = email;
    return (T) this;
  }

  /**
   * @return address
   */
  public String getAddress() {
    return this.address;
  }

  /**
   * @param address to set
   * @return Person
   */
  public <T extends Person> T setAddress(String address) {
    this.address = address;
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
   * @return Person
   */
  public <T extends Person> T setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
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
   * @return Person
   */
  public <T extends Person> T setBirthdate(OffsetDateTime birthdate) {
    this.birthdate = birthdate;
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
   * @return Person
   */
  public <T extends Person> T setSocialSecurityNumber(String socialSecurityNumber) {
    this.socialSecurityNumber = socialSecurityNumber;
    return (T) this;
  }
}
