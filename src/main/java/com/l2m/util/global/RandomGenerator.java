package com.l2m.util.global;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

public interface RandomGenerator {
  /**
   * randomString generator
   */
  static final RandomStringGenerator LETTER_GENERATOR = new RandomStringGenerator.Builder()
    .withinRange('0', 'z')
    .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
    .build();

  static final RandomStringGenerator NUMBER_GENERATOR = new RandomStringGenerator.Builder()
    .withinRange('0', '9')
    .filteredBy(CharacterPredicates.DIGITS)
    .build();

  /**
   * 숫자 난수 생성
   * len : 자릿수
   */
  static Integer randomIntegerMaker(int len) {
    return Integer.valueOf(
      RandomGenerator.randomStringMaker(len, RandomGenerator.NUMBER_GENERATOR)
    );
  }

  /**
   * 랜덤 문자열 생성
   * size : 자릿수
   */
  static String randomStringMaker(int size) {
    return RandomGenerator.LETTER_GENERATOR.generate(size);
  }

  /**
   * 랜덤 문자열 생성 custom
   * size : 자릿수
   * RandomStringGenerator : 적용할 generator
   */
  static String randomStringMaker(
    int size,
    RandomStringGenerator randomStringGenerator
  ) {
    return randomStringGenerator.generate(size);
  }
}
