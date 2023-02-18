package com.l2m.repository.support;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.l2m.domain.Member;
import com.l2m.domain.QMember;
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
  
}
