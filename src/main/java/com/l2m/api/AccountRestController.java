package com.l2m.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.l2m.model.MemberDto;
import com.l2m.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 로그인용 api 정의
 */
@Tag(name = "로그인 api")
@CrossOrigin("*")
@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountRestController {
  
  @NonNull
  private MemberService memberService;
  
  @Operation(summary = "회원가입")
  @PostMapping(value = "/join")
  public MemberDto.join join(@RequestBody final MemberDto.joinParam joinParam) {
    return memberService.join(joinParam);
  }

  @Operation(summary = "로그인")
  @PostMapping(value = "/login")
  public MemberDto.login login(@RequestBody final MemberDto.loginParam loginParam) {
    return memberService.login(loginParam);
  }

  @Operation(summary = "비밀번호 찾기")
  @GetMapping(value = "/findPw")
  public MemberDto.findPw findPw(@ModelAttribute final MemberDto.findPwParam findPwParam) {
    return memberService.findPw(findPwParam);
  }

}
