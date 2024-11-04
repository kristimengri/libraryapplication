package com.kent.gmail.com.runtime.data;

import com.kent.gmail.com.runtime.model.Person;
import com.kent.gmail.com.runtime.model.Person_;
import com.kent.gmail.com.runtime.request.PersonFilter;
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
public class PersonRepository {

  @PersistenceContext private EntityManager em;

  @Autowired private ApplicationEventPublisher applicationEventPublisher;

  @Autowired private BaseRepository baseRepository;

  /**
   * @param personFilter Object Used to List Person
   * @param securityContext
   * @return List of Person
   */
  public List<Person> listAllPersons(
      PersonFilter personFilter, UserSecurityContext securityContext) {

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Person> q = cb.createQuery(Person.class);
    Root<Person> r = q.from(Person.class);
    List<Predicate> preds = new ArrayList<>();
    addPersonPredicate(personFilter, cb, q, r, preds, securityContext);
    q.select(r).where(preds.toArray(new Predicate[0]));

    TypedQuery<Person> query = em.createQuery(q);

    if (personFilter.getPageSize() != null
        && personFilter.getCurrentPage() != null
        && personFilter.getPageSize() > 0
        && personFilter.getCurrentPage() > -1) {
      query
          .setFirstResult(personFilter.getPageSize() * personFilter.getCurrentPage())
          .setMaxResults(personFilter.getPageSize());
    }
    return query.getResultList();
  }

  public <T extends Person> void addPersonPredicate(
      PersonFilter personFilter,
      CriteriaBuilder cb,
      CommonAbstractCriteria q,
      From<?, T> r,
      List<Predicate> preds,
      UserSecurityContext securityContext) {

    baseRepository.addBasePredicate(personFilter, cb, q, r, preds, securityContext);

    if (personFilter.getEmailLike() != null && !personFilter.getEmailLike().isEmpty()) {
      preds.add(cb.like(r.get(Person_.email), personFilter.getEmailLike()));
    }

    if (personFilter.getBirthdateStart() != null) {
      preds.add(
          cb.greaterThanOrEqualTo(r.get(Person_.birthdate), personFilter.getBirthdateStart()));
    }
    if (personFilter.getBirthdateEnd() != null) {
      preds.add(cb.lessThanOrEqualTo(r.get(Person_.birthdate), personFilter.getBirthdateEnd()));
    }

    if (personFilter.getAddressLike() != null && !personFilter.getAddressLike().isEmpty()) {
      preds.add(cb.like(r.get(Person_.address), personFilter.getAddressLike()));
    }

    if (personFilter.getSocialSecurityNumber() != null
        && !personFilter.getSocialSecurityNumber().isEmpty()) {
      preds.add(r.get(Person_.socialSecurityNumber).in(personFilter.getSocialSecurityNumber()));
    }

    if (personFilter.getPhoneNumber() != null && !personFilter.getPhoneNumber().isEmpty()) {
      preds.add(r.get(Person_.phoneNumber).in(personFilter.getPhoneNumber()));
    }

    if (personFilter.getPhoneNumberLike() != null && !personFilter.getPhoneNumberLike().isEmpty()) {
      preds.add(cb.like(r.get(Person_.phoneNumber), personFilter.getPhoneNumberLike()));
    }

    if (personFilter.getAddress() != null && !personFilter.getAddress().isEmpty()) {
      preds.add(r.get(Person_.address).in(personFilter.getAddress()));
    }

    if (personFilter.getSocialSecurityNumberLike() != null
        && !personFilter.getSocialSecurityNumberLike().isEmpty()) {
      preds.add(
          cb.like(r.get(Person_.socialSecurityNumber), personFilter.getSocialSecurityNumberLike()));
    }

    if (personFilter.getEmail() != null && !personFilter.getEmail().isEmpty()) {
      preds.add(r.get(Person_.email).in(personFilter.getEmail()));
    }
  }

  /**
   * @param personFilter Object Used to List Person
   * @param securityContext
   * @return count of Person
   */
  public Long countAllPersons(PersonFilter personFilter, UserSecurityContext securityContext) {

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> q = cb.createQuery(Long.class);
    Root<Person> r = q.from(Person.class);
    List<Predicate> preds = new ArrayList<>();
    addPersonPredicate(personFilter, cb, q, r, preds, securityContext);
    q.select(cb.count(r)).where(preds.toArray(new Predicate[0]));
    TypedQuery<Long> query = em.createQuery(q);
    return query.getSingleResult();
  }

  public void remove(Object o) {
    em.remove(o);
  }

  public <T extends Person, I> List<T> listByIds(
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

  public <T extends Person, I> T getByIdOrNull(
      Class<T> c, SingularAttribute<? super T, I> idField, I id) {
    return getByIdOrNull(c, idField, id, null);
  }

  public <T extends Person, I> List<T> listByIds(
      Class<T> c, SingularAttribute<? super T, I> idField, Set<I> ids) {
    return listByIds(c, idField, ids, null);
  }

  public <T extends Person, I> T getByIdOrNull(
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
