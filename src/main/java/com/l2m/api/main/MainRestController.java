package com.l2m.api.main;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 메인 페이지 관련 API 정의
 * 
 * by jaewon
 */
@CrossOrigin("*")
@Tag(name = "메인 페이지 API")
@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainRestController {
  // @Operation(summary = "회원가입")
  // @PostMapping("/join")
  // public 
}
