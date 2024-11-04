package com.kent.gmail.com.runtime.service;

import com.kent.gmail.com.runtime.data.CVSObjectRepository;
import com.kent.gmail.com.runtime.model.CVSObject;
import com.kent.gmail.com.runtime.model.CVSObject_;
import com.kent.gmail.com.runtime.request.CVSObjectCreate;
import com.kent.gmail.com.runtime.request.CVSObjectFilter;
import com.kent.gmail.com.runtime.request.CVSObjectUpdate;
import com.kent.gmail.com.runtime.response.PaginationResponse;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import jakarta.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CVSObjectService {

  @Autowired private CVSObjectRepository repository;

  /**
   * @param cVSObjectCreate Object Used to Create CVSObject
   * @param securityContext
   * @return created CVSObject
   */
  public CVSObject createCVSObject(
      CVSObjectCreate cVSObjectCreate, UserSecurityContext securityContext) {

    CVSObject cVSObject = createCVSObjectNoMerge(cVSObjectCreate, securityContext);
    cVSObject = this.repository.merge(cVSObject);
    return cVSObject;
  }

  /**
   * @param cVSObjectCreate Object Used to Create CVSObject
   * @param securityContext
   * @return created CVSObject unmerged
   */
  public CVSObject createCVSObjectNoMerge(
      CVSObjectCreate cVSObjectCreate, UserSecurityContext securityContext) {

    CVSObject cVSObject = new CVSObject();
    cVSObject.setId(UUID.randomUUID().toString());

    updateCVSObjectNoMerge(cVSObject, cVSObjectCreate);

    return cVSObject;
  }

  /**
   * @param cVSObjectCreate Object Used to Create CVSObject
   * @param cVSObject
   * @return if cVSObject was updated
   */
  public boolean updateCVSObjectNoMerge(CVSObject cVSObject, CVSObjectCreate cVSObjectCreate) {

    boolean update = false;

    if (cVSObjectCreate.getBucketId() != null
        && (!cVSObjectCreate.getBucketId().equals(cVSObject.getBucketId()))) {
      cVSObject.setBucketId(cVSObjectCreate.getBucketId());
      update = true;
    }

    if (cVSObjectCreate.getObjectId() != null
        && (!cVSObjectCreate.getObjectId().equals(cVSObject.getObjectId()))) {
      cVSObject.setObjectId(cVSObjectCreate.getObjectId());
      update = true;
    }

    if (cVSObjectCreate.getUploadDate() != null
        && (!cVSObjectCreate.getUploadDate().equals(cVSObject.getUploadDate()))) {
      cVSObject.setUploadDate(cVSObjectCreate.getUploadDate());
      update = true;
    }

    return update;
  }

  /**
   * @param cVSObjectUpdate
   * @param securityContext
   * @return cVSObject
   */
  public CVSObject updateCVSObject(
      CVSObjectUpdate cVSObjectUpdate, UserSecurityContext securityContext) {

    CVSObject cVSObject = cVSObjectUpdate.getCVSObject();

    if (updateCVSObjectNoMerge(cVSObject, cVSObjectUpdate)) {
      cVSObject = this.repository.merge(cVSObject);
    }

    return cVSObject;
  }

  /**
   * @param cVSObjectFilter Object Used to List CVSObject
   * @param securityContext
   * @return PaginationResponse<CVSObject> containing paging information for CVSObject
   */
  public PaginationResponse<CVSObject> getAllCVSObjects(
      CVSObjectFilter cVSObjectFilter, UserSecurityContext securityContext) {

    List<CVSObject> list = listAllCVSObjects(cVSObjectFilter, securityContext);
    long count = this.repository.countAllCVSObjects(cVSObjectFilter, securityContext);

    return new PaginationResponse<>(list, cVSObjectFilter.getPageSize(), count);
  }

  /**
   * @param cVSObjectFilter Object Used to List CVSObject
   * @param securityContext
   * @return List of CVSObject
   */
  public List<CVSObject> listAllCVSObjects(
      CVSObjectFilter cVSObjectFilter, UserSecurityContext securityContext) {

    return this.repository.listAllCVSObjects(cVSObjectFilter, securityContext);
  }

  public CVSObject deleteCVSObject(String id, UserSecurityContext securityContext) {

    CVSObject cVSObject =
        this.repository.getByIdOrNull(CVSObject.class, CVSObject_.id, id, securityContext);

    if (cVSObject == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CVSObject not found");
    }

    this.repository.remove(cVSObject);

    return cVSObject;
  }

  public <T extends CVSObject, I> List<T> listByIds(
      Class<T> c,
      SingularAttribute<? super T, I> idField,
      Set<I> ids,
      UserSecurityContext securityContext) {
    return repository.listByIds(c, idField, ids, securityContext);
  }

  public <T extends CVSObject, I> T getByIdOrNull(
      Class<T> c,
      SingularAttribute<? super T, I> idField,
      I id,
      UserSecurityContext securityContext) {
    return repository.getByIdOrNull(c, idField, id, securityContext);
  }

  public <T extends CVSObject, I> T getByIdOrNull(
      Class<T> c, SingularAttribute<? super T, I> idField, I id) {
    return repository.getByIdOrNull(c, idField, id);
  }

  public <T extends CVSObject, I> List<T> listByIds(
      Class<T> c, SingularAttribute<? super T, I> idField, Set<I> ids) {
    return repository.listByIds(c, idField, ids);
  }

  public <T> T merge(T base) {
    return this.repository.merge(base);
  }

  public void massMerge(List<?> toMerge) {
    this.repository.massMerge(toMerge);
  }
}
