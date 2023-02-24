package com.l2m.repository.support;

import javax.persistence.EntityManager;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.l2m.domain.QMySetting;
import com.l2m.model.MySettingDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
@SuppressWarnings("deprecation")
public class MySettingRepositorySupportImpl implements MySettingRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  public MySettingRepositorySupportImpl(EntityManager entityManager) {
    this.jpaQueryFactory = new JPAQueryFactory(entityManager);
  }
  
  @Override
  public QueryResults<MySettingDto.list> list(MySettingDto.listParam listParam, String memberKey) {
    final QMySetting mySetting = QMySetting.mySetting;
    final BooleanExpression isMemberKey = mySetting.memberKey.eq(memberKey);
    final PageRequest pageable = listParam.makePageable();

    return jpaQueryFactory.select(Projections.constructor(MySettingDto.list.class, mySetting))
                          .from(mySetting)
                          .where(isMemberKey)
                          .offset(pageable.getOffset())
                          .limit(pageable.getPageSize())
                          .fetchResults();
  }
}
