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
  BELT("벨트", "belt"),
  NECK("목걸이", "neck"),
  RING("반지", "ring")
  ;

  private String tradeCategoryName;
  private String type;

  // 타입명으로 enum 가져오기
  public static ItemEnum getItemEnum(String type) {
    for (ItemEnum itemEnum : ItemEnum.values()) {
      if (itemEnum.getType().equals(type)) return itemEnum;
    }
    return null;
  }

  // 카테고리명으로 type 가져오기
  public static String getType(String tradeCategoryName) {
    for (ItemEnum itemEnum : ItemEnum.values()) {
      if (itemEnum.getTradeCategoryName().equals(tradeCategoryName)) return itemEnum.getType();
    }
    return null;
  }
}
