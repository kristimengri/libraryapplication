package com.kent.gmail.com.runtime.request;

import jakarta.validation.constraints.Min;
import java.time.OffsetDateTime;
import java.util.Set;

/** Object Used to List Base */
public class BaseFilter {

  @Min(
      value = 0,
      groups = {})
  private Integer currentPage;

  private OffsetDateTime dateCreatedEnd;

  private OffsetDateTime dateCreatedStart;

  private OffsetDateTime dateUpdatedEnd;

  private OffsetDateTime dateUpdatedStart;

  private Set<String> description;

  private String descriptionLike;

  private Set<String> id;

  private Set<String> name;

  private String nameLike;

  @Min(
      value = 1,
      groups = {})
  private Integer pageSize;

  private Set<Boolean> softDelete;

  public BaseFilter() {}

  /**
   * @return currentPage
   */
  public Integer getCurrentPage() {
    return this.currentPage;
  }

  /**
   * @param currentPage to set
   * @return BaseFilter
   */
  public <T extends BaseFilter> T setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
    return (T) this;
  }

  /**
   * @return dateCreatedEnd
   */
  public OffsetDateTime getDateCreatedEnd() {
    return this.dateCreatedEnd;
  }

  /**
   * @param dateCreatedEnd to set
   * @return BaseFilter
   */
  public <T extends BaseFilter> T setDateCreatedEnd(OffsetDateTime dateCreatedEnd) {
    this.dateCreatedEnd = dateCreatedEnd;
    return (T) this;
  }

  /**
   * @return dateCreatedStart
   */
  public OffsetDateTime getDateCreatedStart() {
    return this.dateCreatedStart;
  }

  /**
   * @param dateCreatedStart to set
   * @return BaseFilter
   */
  public <T extends BaseFilter> T setDateCreatedStart(OffsetDateTime dateCreatedStart) {
    this.dateCreatedStart = dateCreatedStart;
    return (T) this;
  }

  /**
   * @return dateUpdatedEnd
   */
  public OffsetDateTime getDateUpdatedEnd() {
    return this.dateUpdatedEnd;
  }

  /**
   * @param dateUpdatedEnd to set
   * @return BaseFilter
   */
  public <T extends BaseFilter> T setDateUpdatedEnd(OffsetDateTime dateUpdatedEnd) {
    this.dateUpdatedEnd = dateUpdatedEnd;
    return (T) this;
  }

  /**
   * @return dateUpdatedStart
   */
  public OffsetDateTime getDateUpdatedStart() {
    return this.dateUpdatedStart;
  }

  /**
   * @param dateUpdatedStart to set
   * @return BaseFilter
   */
  public <T extends BaseFilter> T setDateUpdatedStart(OffsetDateTime dateUpdatedStart) {
    this.dateUpdatedStart = dateUpdatedStart;
    return (T) this;
  }

  /**
   * @return description
   */
  public Set<String> getDescription() {
    return this.description;
  }

  /**
   * @param description to set
   * @return BaseFilter
   */
  public <T extends BaseFilter> T setDescription(Set<String> description) {
    this.description = description;
    return (T) this;
  }

  /**
   * @return descriptionLike
   */
  public String getDescriptionLike() {
    return this.descriptionLike;
  }

  /**
   * @param descriptionLike to set
   * @return BaseFilter
   */
  public <T extends BaseFilter> T setDescriptionLike(String descriptionLike) {
    this.descriptionLike = descriptionLike;
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
   * @return BaseFilter
   */
  public <T extends BaseFilter> T setId(Set<String> id) {
    this.id = id;
    return (T) this;
  }

  /**
   * @return name
   */
  public Set<String> getName() {
    return this.name;
  }

  /**
   * @param name to set
   * @return BaseFilter
   */
  public <T extends BaseFilter> T setName(Set<String> name) {
    this.name = name;
    return (T) this;
  }

  /**
   * @return nameLike
   */
  public String getNameLike() {
    return this.nameLike;
  }

  /**
   * @param nameLike to set
   * @return BaseFilter
   */
  public <T extends BaseFilter> T setNameLike(String nameLike) {
    this.nameLike = nameLike;
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
   * @return BaseFilter
   */
  public <T extends BaseFilter> T setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return (T) this;
  }

  /**
   * @return softDelete
   */
  public Set<Boolean> getSoftDelete() {
    return this.softDelete;
  }

  /**
   * @param softDelete to set
   * @return BaseFilter
   */
  public <T extends BaseFilter> T setSoftDelete(Set<Boolean> softDelete) {
    this.softDelete = softDelete;
    return (T) this;
  }
}
