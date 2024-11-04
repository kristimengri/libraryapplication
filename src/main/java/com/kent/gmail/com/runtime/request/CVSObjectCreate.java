package com.kent.gmail.com.runtime.request;

import java.time.OffsetDateTime;

/** Object Used to Create CVSObject */
public class CVSObjectCreate {

  private String bucketId;

  private String objectId;

  private OffsetDateTime uploadDate;

  public CVSObjectCreate() {}

  /**
   * @return bucketId
   */
  public String getBucketId() {
    return this.bucketId;
  }

  /**
   * @param bucketId to set
   * @return CVSObjectCreate
   */
  public <T extends CVSObjectCreate> T setBucketId(String bucketId) {
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
   * @return CVSObjectCreate
   */
  public <T extends CVSObjectCreate> T setObjectId(String objectId) {
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
   * @return CVSObjectCreate
   */
  public <T extends CVSObjectCreate> T setUploadDate(OffsetDateTime uploadDate) {
    this.uploadDate = uploadDate;
    return (T) this;
  }
}
