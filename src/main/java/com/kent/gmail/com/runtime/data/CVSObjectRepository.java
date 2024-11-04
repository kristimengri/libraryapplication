package com.kent.gmail.com.runtime.data;

import com.kent.gmail.com.runtime.model.CVSObject;
import com.kent.gmail.com.runtime.model.CVSObject_;
import com.kent.gmail.com.runtime.request.CVSObjectFilter;
import com.kent.gmail.com.runtime.security.UserSecurityContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CVSObjectRepository {

  @PersistenceContext private EntityManager em;

  @Autowired private ApplicationEventPublisher applicationEventPublisher;

  /**
   * @param cVSObjectFilter Object Used to List CVSObject
   * @param securityContext
   * @return List of CVSObject
   */
  public List<CVSObject> listAllCVSObjects(
      CVSObjectFilter cVSObjectFilter, UserSecurityContext securityContext) {

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<CVSObject> q = cb.createQuery(CVSObject.class);
    Root<CVSObject> r = q.from(CVSObject.class);
    List<Predicate> preds = new ArrayList<>();
    addCVSObjectPredicate(cVSObjectFilter, cb, q, r, preds, securityContext);
    q.select(r).where(preds.toArray(new Predicate[0]));

    TypedQuery<CVSObject> query = em.createQuery(q);

    if (cVSObjectFilter.getPageSize() != null
        && cVSObjectFilter.getCurrentPage() != null
        && cVSObjectFilter.getPageSize() > 0
        && cVSObjectFilter.getCurrentPage() > -1) {
      query
          .setFirstResult(cVSObjectFilter.getPageSize() * cVSObjectFilter.getCurrentPage())
          .setMaxResults(cVSObjectFilter.getPageSize());
    }
    return query.getResultList();
  }

  public <T extends CVSObject> void addCVSObjectPredicate(
      CVSObjectFilter cVSObjectFilter,
      CriteriaBuilder cb,
      CommonAbstractCriteria q,
      From<?, T> r,
      List<Predicate> preds,
      UserSecurityContext securityContext) {

    if (cVSObjectFilter.getObjectIdLike() != null && !cVSObjectFilter.getObjectIdLike().isEmpty()) {
      preds.add(cb.like(r.get(CVSObject_.objectId), cVSObjectFilter.getObjectIdLike()));
    }

    if (cVSObjectFilter.getBucketId() != null && !cVSObjectFilter.getBucketId().isEmpty()) {
      preds.add(r.get(CVSObject_.bucketId).in(cVSObjectFilter.getBucketId()));
    }

    if (cVSObjectFilter.getUploadDateStart() != null) {
      preds.add(
          cb.greaterThanOrEqualTo(
              r.get(CVSObject_.uploadDate), cVSObjectFilter.getUploadDateStart()));
    }
    if (cVSObjectFilter.getUploadDateEnd() != null) {
      preds.add(
          cb.lessThanOrEqualTo(r.get(CVSObject_.uploadDate), cVSObjectFilter.getUploadDateEnd()));
    }

    if (cVSObjectFilter.getObjectId() != null && !cVSObjectFilter.getObjectId().isEmpty()) {
      preds.add(r.get(CVSObject_.objectId).in(cVSObjectFilter.getObjectId()));
    }

    if (cVSObjectFilter.getBucketIdLike() != null && !cVSObjectFilter.getBucketIdLike().isEmpty()) {
      preds.add(cb.like(r.get(CVSObject_.bucketId), cVSObjectFilter.getBucketIdLike()));
    }

    if (cVSObjectFilter.getId() != null && !cVSObjectFilter.getId().isEmpty()) {
      preds.add(r.get(CVSObject_.id).in(cVSObjectFilter.getId()));
    }
  }

  /**
   * @param cVSObjectFilter Object Used to List CVSObject
   * @param securityContext
   * @return count of CVSObject
   */
  public Long countAllCVSObjects(
      CVSObjectFilter cVSObjectFilter, UserSecurityContext securityContext) {

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> q = cb.createQuery(Long.class);
    Root<CVSObject> r = q.from(CVSObject.class);
    List<Predicate> preds = new ArrayList<>();
    addCVSObjectPredicate(cVSObjectFilter, cb, q, r, preds, securityContext);
    q.select(cb.count(r)).where(preds.toArray(new Predicate[0]));
    TypedQuery<Long> query = em.createQuery(q);
    return query.getSingleResult();
  }

  public void remove(Object o) {
    em.remove(o);
  }

  public <T extends CVSObject, I> List<T> listByIds(
      Class<T> c,
      SingularAttribute<? super T, I> idField,
      Set<I> ids,
      UserSecurityContext securityContext) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<T> q = cb.createQuery(c);
    Root<T> r = q.from(c);
    List<Predicate> preds = new ArrayList<>();
    preds.add(r.get(idField).in(ids));

    q.select(r).where(preds.toArray(new Predicate[0]));
    return em.createQuery(q).getResultList();
  }

  public <T extends CVSObject, I> T getByIdOrNull(
      Class<T> c, SingularAttribute<? super T, I> idField, I id) {
    return getByIdOrNull(c, idField, id, null);
  }

  public <T extends CVSObject, I> List<T> listByIds(
      Class<T> c, SingularAttribute<? super T, I> idField, Set<I> ids) {
    return listByIds(c, idField, ids, null);
  }

  public <T extends CVSObject, I> T getByIdOrNull(
      Class<T> c,
      SingularAttribute<? super T, I> idField,
      I id,
      UserSecurityContext securityContext) {
    return listByIds(c, idField, Collections.singleton(id), securityContext).stream()
        .findFirst()
        .orElse(null);
  }

  @Transactional
  public <T> T merge(T base) {
    T updated = em.merge(base);
    applicationEventPublisher.publishEvent(updated);
    return updated;
  }

  @Transactional
  public void massMerge(List<?> toMerge) {
    for (Object o : toMerge) {
      Object updated = em.merge(o);
      applicationEventPublisher.publishEvent(updated);
    }
  }
}
