package com.l2m.repository.support;

import static com.l2m.domain.QBusinessKeyStore.businessKeyStore;

import java.util.function.Function;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.l2m.domain.BusinessKeyStore;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BusinessKeyStoreRepositorySupportImpl implements BusinessKeyStoreRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  public BusinessKeyStoreRepositorySupportImpl(EntityManager entityManager) {
    this.jpaQueryFactory = new JPAQueryFactory(entityManager);
  }

  // [검색조건]
  private final Function<String, BooleanExpression> isKey = key ->
    businessKeyStore.businessKey.eq(key);

  @Override
  public boolean isUnique(BusinessKeyStore entity) {
    return (
      jpaQueryFactory
        .select(businessKeyStore.id)
        .from(businessKeyStore)
        .where(isKey.apply(entity.getBusinessKey()))
        .fetchFirst() ==
      null
    );
  }
}
