package com.kent.gmail.com.runtime;

/** */
public class Create {

  private String bucketId;

  private String objectId;

  public Create() {}

  /**
   * @return bucketId
   */
  public String getBucketId() {
    return this.bucketId;
  }

  /**
   * @param bucketId to set
   * @return Create
   */
  public <T extends Create> T setBucketId(String bucketId) {
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
   * @return Create
   */
  public <T extends Create> T setObjectId(String objectId) {
    this.objectId = objectId;
    return (T) this;
  }
}
