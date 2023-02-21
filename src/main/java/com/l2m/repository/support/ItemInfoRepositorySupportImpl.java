package com.l2m.repository.support;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.l2m.domain.QItemInfo;
import com.l2m.domain.base.enums.ItemEnum;
import com.l2m.model.SearchDto;
import com.l2m.util.global.IsNullUtil;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ItemInfoRepositorySupportImpl implements ItemInfoRepositorySupport {
  private final JPAQueryFactory jpaQueryFactory;

  public ItemInfoRepositorySupportImpl(EntityManager entityManager) {
    this.jpaQueryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public List<SearchDto.itemInfo> itemInfoList(SearchDto.lowPriceSearchParam lowPriceSearchParam, ItemEnum itemEnum) {
    final String classId = lowPriceSearchParam.getClass_id(),         // 클래스
                gradeId = lowPriceSearchParam.getGrade_id();          // 등급
    
    final QItemInfo itemInfo = QItemInfo.itemInfo;

    // 클래스는 무기타입일때만 조건추가
    final BooleanExpression isClassId = IsNullUtil.check(classId) ? null : 
                                        !itemEnum.getType().equals("weapon") ? itemInfo.tradeCategoryName.eq(itemEnum.getTradeCategoryName()) :
                                                                                        itemInfo.tradeCategoryName.eq(classId),
                            isGradeId = IsNullUtil.check(gradeId) ? null : itemInfo.grade.eq(gradeId);

    return jpaQueryFactory.select(Projections.constructor(SearchDto.itemInfo.class, itemInfo.itemId
                                                                                        , itemInfo.itemName
                                                                                        , itemInfo.tradeCategoryName))
                          .from(itemInfo)
                          .where(
                            isClassId
                            , isGradeId
                          )
                          .groupBy(
                            itemInfo.itemId
                            , itemInfo.itemName
                            , itemInfo.tradeCategoryName
                          )
                          // .limit(5L)
                          .fetch();
  }
}
