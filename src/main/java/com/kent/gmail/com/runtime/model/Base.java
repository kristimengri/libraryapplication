package com.kent.gmail.com.runtime.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.OffsetDateTime;

@MappedSuperclass
public class Base {

  @Id private String id;

  private String name;

  private String description;

  private OffsetDateTime dateCreated;

  private OffsetDateTime dateUpdated;

  private boolean softDelete;

  public Base() {}

  /**
   * @return id
   */
  @Id
  public String getId() {
    return this.id;
  }

  /**
   * @param id to set
   * @return Base
   */
  public <T extends Base> T setId(String id) {
    this.id = id;
    return (T) this;
  }

  /**
   * @return name
   */
  public String getName() {
    return this.name;
  }

  /**
   * @param name to set
   * @return Base
   */
  public <T extends Base> T setName(String name) {
    this.name = name;
    return (T) this;
  }

  /**
   * @return description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * @param description to set
   * @return Base
   */
  public <T extends Base> T setDescription(String description) {
    this.description = description;
    return (T) this;
  }

  /**
   * @return dateCreated
   */
  public OffsetDateTime getDateCreated() {
    return this.dateCreated;
  }

  /**
   * @param dateCreated to set
   * @return Base
   */
  public <T extends Base> T setDateCreated(OffsetDateTime dateCreated) {
    this.dateCreated = dateCreated;
    return (T) this;
  }

  /**
   * @return dateUpdated
   */
  public OffsetDateTime getDateUpdated() {
    return this.dateUpdated;
  }

  /**
   * @param dateUpdated to set
   * @return Base
   */
  public <T extends Base> T setDateUpdated(OffsetDateTime dateUpdated) {
    this.dateUpdated = dateUpdated;
    return (T) this;
  }

  /**
   * @return softDelete
   */
  public boolean isSoftDelete() {
    return this.softDelete;
  }

  /**
   * @param softDelete to set
   * @return Base
   */
  public <T extends Base> T setSoftDelete(boolean softDelete) {
    this.softDelete = softDelete;
    return (T) this;
  }
}
