package com.kent.gmail.com.runtime.request;

import jakarta.validation.constraints.Min;
import java.time.OffsetDateTime;
import java.util.Set;

/** Object Used to List CVSObject */
public class CVSObjectFilter {

  private Set<String> bucketId;

  private String bucketIdLike;

  @Min(
      value = 0,
      groups = {})
  private Integer currentPage;

  private Set<String> id;

  private Set<String> objectId;

  private String objectIdLike;

  @Min(
      value = 1,
      groups = {})
  private Integer pageSize;

  private OffsetDateTime uploadDateEnd;

  private OffsetDateTime uploadDateStart;

  public CVSObjectFilter() {}

  /**
   * @return bucketId
   */
  public Set<String> getBucketId() {
    return this.bucketId;
  }

  /**
   * @param bucketId to set
   * @return CVSObjectFilter
   */
  public <T extends CVSObjectFilter> T setBucketId(Set<String> bucketId) {
    this.bucketId = bucketId;
    return (T) this;
  }

  /**
   * @return bucketIdLike
   */
  public String getBucketIdLike() {
    return this.bucketIdLike;
  }

  /**
   * @param bucketIdLike to set
   * @return CVSObjectFilter
   */
  public <T extends CVSObjectFilter> T setBucketIdLike(String bucketIdLike) {
    this.bucketIdLike = bucketIdLike;
    return (T) this;
  }

  /**
   * @return currentPage
   */
  public Integer getCurrentPage() {
    return this.currentPage;
  }

  /**
   * @param currentPage to set
   * @return CVSObjectFilter
   */
  public <T extends CVSObjectFilter> T setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
    return (T) this;
  }

  /**
   * @return id
   */
  public Set<String> getId() {
    return this.id;
  }

  /**
   * @param id to set
   * @return CVSObjectFilter
   */
  public <T extends CVSObjectFilter> T setId(Set<String> id) {
    this.id = id;
    return (T) this;
  }

  /**
   * @return objectId
   */
  public Set<String> getObjectId() {
    return this.objectId;
  }

  /**
   * @param objectId to set
   * @return CVSObjectFilter
   */
  public <T extends CVSObjectFilter> T setObjectId(Set<String> objectId) {
    this.objectId = objectId;
    return (T) this;
  }

  /**
   * @return objectIdLike
   */
  public String getObjectIdLike() {
    return this.objectIdLike;
  }

  /**
   * @param objectIdLike to set
   * @return CVSObjectFilter
   */
  public <T extends CVSObjectFilter> T setObjectIdLike(String objectIdLike) {
    this.objectIdLike = objectIdLike;
    return (T) this;
  }

  /**
   * @return pageSize
   */
  public Integer getPageSize() {
    return this.pageSize;
  }

  /**
   * @param pageSize to set
   * @return CVSObjectFilter
   */
  public <T extends CVSObjectFilter> T setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return (T) this;
  }

  /**
   * @return uploadDateEnd
   */
  public OffsetDateTime getUploadDateEnd() {
    return this.uploadDateEnd;
  }

  /**
   * @param uploadDateEnd to set
   * @return CVSObjectFilter
   */
  public <T extends CVSObjectFilter> T setUploadDateEnd(OffsetDateTime uploadDateEnd) {
    this.uploadDateEnd = uploadDateEnd;
    return (T) this;
  }

  /**
   * @return uploadDateStart
   */
  public OffsetDateTime getUploadDateStart() {
    return this.uploadDateStart;
  }

  /**
   * @param uploadDateStart to set
   * @return CVSObjectFilter
   */
  public <T extends CVSObjectFilter> T setUploadDateStart(OffsetDateTime uploadDateStart) {
    this.uploadDateStart = uploadDateStart;
    return (T) this;
  }
}
