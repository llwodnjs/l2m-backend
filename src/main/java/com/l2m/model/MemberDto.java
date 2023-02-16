package com.l2m.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 회원 DTO 정의
 * 
 * by jaewon
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {
  
  // 회원가입 파라미터
  @Getter
  @Setter
  public static class joinParam {
    @Schema(description = "이름")
    private String name;

    @Schema(description = "회원 ID")
    private String username;

    @Schema(description = "패스워드")
    private String password;

    @Schema(description = "패스워드 확인")
    private String rePassword;
  }

  // 회원가입 반환객체
  @Getter
  @Setter
  public static class join {
    @Schema(description = "회원 key")
    private String memberKey;

    public join(String memberKey) {
      this.memberKey = memberKey;
    }
  }
}
