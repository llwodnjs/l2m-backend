package com.l2m.api;

import org.springframework.web.bind.annotation.CrossOrigin;
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
 * 회원 관련 API
 * 
 * by jaewon
 */
@CrossOrigin("*")
@Tag(name = "회원 관련 API")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberRestController {

  @NonNull
  private MemberService memberService;
  
  @Operation(summary = "회원가입")
  @PostMapping(value = "/join")
  public MemberDto.join join(@RequestBody final MemberDto.joinParam joinParam) {
    return memberService.join(joinParam);
  }
}
