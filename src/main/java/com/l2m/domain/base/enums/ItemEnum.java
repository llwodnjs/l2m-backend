package com.l2m.domain.base.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 아이템 정보 관련 Enum
 * 
 * by jaewon
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ItemEnum {

  // 무기 [s]
  WEAPON("무기", "weapon"),
  // 무기 [e]
  // 방어구 [s]
  HEAD("투구", "head"),
  TOP("상의", "top"),
  PANTS("하의", "pants"),
  GLOVES("장갑", "gloves"),
  SHOES("신발", "shoes"),
  CLOAK("망토", "cloak"),
  SIGIL("시길", "sigil"),
  // 방어구 [e]
  // 악세서리[s]
  NECK("목걸이", "neck"),
  RING("반지", "ring"),
  BELT("벨트", "belt");

  private String tradeCategoryName;
  private String type;
}
