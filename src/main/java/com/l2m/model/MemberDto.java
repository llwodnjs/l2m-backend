package com.l2m.model;

import com.l2m.domain.Member;

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
  
  
  /**
   * 회원가입 파라미터
   */
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

  /**
   * 회원가입 반환객체
   */
  @Getter
  @Setter
  public static class join {
    @Schema(description = "회원 key")
    private String memberKey;

    public join(String memberKey) {
      this.memberKey = memberKey;
    }
  }

  @Getter
  @Setter
  public static class login {
    @Schema(description = "아이디")
    private String username;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "회원 키")
    private String memberKey;

    @Schema(description = "token")
    private String token;

    public login(Member member, String token) {
      this.username = member.getUsername();
      this.name = member.getName();
      this.memberKey = member.getBusinessKey();
      this.token = token;
    }
  }

  /**
   * 로그인 파라미터
   */
  @Getter
  @Setter
  public static class loginParam {
    @Schema(description = "아이디")
    private String username;

    @Schema(description = "패스워드")
    private String password;
  }

  /**
   * 비밀번호 찾기 반환객체
   */
  @Getter
  @Setter
  public static class findPw {
    @Schema(description = "변경된 비밀번호")
    private String changePw;

    public findPw(String changePw) {
      this.changePw = changePw;
    }
  }

  /**
   * 비밀번호 찾기 파라미터
   */
  @Getter
  @Setter
  public static class findPwParam {
    @Schema(description = "이름")
    private String name;
    
    @Schema(description = "아이디")
    private String username;
  }
}
