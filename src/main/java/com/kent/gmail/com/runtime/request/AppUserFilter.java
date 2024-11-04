package com.kent.gmail.com.runtime.request;

import jakarta.validation.constraints.Min;
import java.util.Set;

/** Object Used to List AppUser */
public class AppUserFilter {

  @Min(
      value = 0,
      groups = {})
  private Integer currentPage;

  private Set<String> id;

  @Min(
      value = 1,
      groups = {})
  private Integer pageSize;

  private Set<String> roles;

  private String rolesLike;

  private Set<String> username;

  private String usernameLike;

  public AppUserFilter() {}

  /**
   * @return currentPage
   */
  public Integer getCurrentPage() {
    return this.currentPage;
  }

  /**
   * @param currentPage to set
   * @return AppUserFilter
   */
  public <T extends AppUserFilter> T setCurrentPage(Integer currentPage) {
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
   * @return AppUserFilter
   */
  public <T extends AppUserFilter> T setId(Set<String> id) {
    this.id = id;
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
   * @return AppUserFilter
   */
  public <T extends AppUserFilter> T setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return (T) this;
  }

  /**
   * @return roles
   */
  public Set<String> getRoles() {
    return this.roles;
  }

  /**
   * @param roles to set
   * @return AppUserFilter
   */
  public <T extends AppUserFilter> T setRoles(Set<String> roles) {
    this.roles = roles;
    return (T) this;
  }

  /**
   * @return rolesLike
   */
  public String getRolesLike() {
    return this.rolesLike;
  }

  /**
   * @param rolesLike to set
   * @return AppUserFilter
   */
  public <T extends AppUserFilter> T setRolesLike(String rolesLike) {
    this.rolesLike = rolesLike;
    return (T) this;
  }

  /**
   * @return username
   */
  public Set<String> getUsername() {
    return this.username;
  }

  /**
   * @param username to set
   * @return AppUserFilter
   */
  public <T extends AppUserFilter> T setUsername(Set<String> username) {
    this.username = username;
    return (T) this;
  }

  /**
   * @return usernameLike
   */
  public String getUsernameLike() {
    return this.usernameLike;
  }

  /**
   * @param usernameLike to set
   * @return AppUserFilter
   */
  public <T extends AppUserFilter> T setUsernameLike(String usernameLike) {
    this.usernameLike = usernameLike;
    return (T) this;
  }
}
