package com.l2m.repository.support;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.l2m.domain.Member;
import com.l2m.domain.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MemberRepositorySupportImpl implements MemberRepositorySupport {
    
  private final JPAQueryFactory jpaQueryFactory;

  public MemberRepositorySupportImpl(EntityManager entityManager){
      this.jpaQueryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public Optional<Member> findByUsername(String username) {
    final QMember member = QMember.member;

    return Optional.ofNullable(
      jpaQueryFactory.select(member)
                            .from(member)
                            .where(member.username.eq(username))
                            .fetchFirst()
    );
  }

  @Override
  public Optional<Member> findByNameAndUsername(String name, String username) {
    final QMember member = QMember.member;
    final BooleanExpression isName = member.name.eq(name);
    final BooleanExpression isUsername = member.username.eq(username);
    

    return Optional.ofNullable(
      jpaQueryFactory.select(member)
                            .from(member)
                            .where(
                              isName
                              , isUsername
                            )
                            .fetchFirst()
    );
  }
  
  @Override
  public Optional<Member> findByBusinessKey(String businessKey) {
    final QMember member = QMember.member;
    final BooleanExpression isBusinessKey = member.businessKey.eq(businessKey);

    return Optional.ofNullable(
      jpaQueryFactory.select(member)
                      .from(member)
                      .where(isBusinessKey)
                      .fetchFirst()
    );
  }
}
