package com.l2m.repository.manager;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 회원 관련 RepositoryManager
 * 
 * by jaewon
 */
@Repository
@RequiredArgsConstructor
public class MemberRepositoryManagerImpl implements MemberRepositoryManager {
  @NonNull
  private EntityManager entityManager;

}
