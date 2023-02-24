package com.l2m.repository.support;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.l2m.domain.MySetting;
import com.l2m.domain.QMySetting;
import com.l2m.domain.QSettingItem;
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

  @Override
  public Optional<MySetting> findByKey(String mySettingKey) {
    final QMySetting mySetting = QMySetting.mySetting;
    final BooleanExpression isSettingKey = mySetting.businessKey.eq(mySettingKey);

    return Optional.ofNullable(
      jpaQueryFactory.select(mySetting)
                      .from(mySetting)
                      .where(isSettingKey)
                      .fetchFirst()
    );
  }

  @Override
  public List<MySettingDto.settingItemList> findSettingItemNameList(String mySettingKey) {
    final QSettingItem settingItem = QSettingItem.settingItem;
    final BooleanExpression isSettingKey = settingItem.settingBusinessKey.eq(mySettingKey);

    return jpaQueryFactory.select(Projections.constructor(MySettingDto.settingItemList.class, settingItem))
                          .from(settingItem)
                          .where(isSettingKey)
                          .fetch();
  }
}
