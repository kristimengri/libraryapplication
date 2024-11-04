package com.kent.gmail.com.runtime.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class CVSObject {

  @Id private String id;

  private String bucketId;

  private String objectId;

  private OffsetDateTime uploadDate;

  public CVSObject() {}

  /**
   * @return id
   */
  @Id
  public String getId() {
    return this.id;
  }

  /**
   * @param id to set
   * @return CVSObject
   */
  public <T extends CVSObject> T setId(String id) {
    this.id = id;
    return (T) this;
  }

  /**
   * @return bucketId
   */
  public String getBucketId() {
    return this.bucketId;
  }

  /**
   * @param bucketId to set
   * @return CVSObject
   */
  public <T extends CVSObject> T setBucketId(String bucketId) {
    this.bucketId = bucketId;
    return (T) this;
  }

  /**
   * @return objectId
   */
  public String getObjectId() {
    return this.objectId;
  }

  /**
   * @param objectId to set
   * @return CVSObject
   */
  public <T extends CVSObject> T setObjectId(String objectId) {
    this.objectId = objectId;
    return (T) this;
  }

  /**
   * @return uploadDate
   */
  public OffsetDateTime getUploadDate() {
    return this.uploadDate;
  }

  /**
   * @param uploadDate to set
   * @return CVSObject
   */
  public <T extends CVSObject> T setUploadDate(OffsetDateTime uploadDate) {
    this.uploadDate = uploadDate;
    return (T) this;
  }
}
