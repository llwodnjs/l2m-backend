package com.l2m.api;

import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

  @Operation(summary = "내 정보 확인")
  @GetMapping(value = "/confirmInfo")
  public MemberDto.confirmInfo confirmInfo(@ModelAttribute final MemberDto.confirmInfoParam confirmInfoParam) {
    return memberService.confirmInfo(confirmInfoParam);
  }

  
}
