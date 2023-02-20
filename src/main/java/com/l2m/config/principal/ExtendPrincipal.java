package com.l2m.config.principal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * JAVA에서 사용할 Session 정의
 * 
 * by jaewon
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExtendPrincipal {

  // 유저 id
  private String username = "anonymous";

  // 유저 명
  private String name = "anonymous";

  // 유저 비즈니스 키
  private String memberKey = "MEM00000000";

  // 유저 패스워드 찾기 여부
  private Character isFindPw = 'N';
}
