package com.l2m.util.global;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * EntityListener 클래스에서는 의존성 주입이 안되기 때문에
 * 특정 class를 bean객체반환처리하는 BeansUtil Class.
 *
 * ApplicationContextAware를 implements받아 ApplicationContext객체를 주입시킴.
 * 주입받은 ApplicationContext객체로 bean객체를 반환하는 static method getBean을 구현.
 * 리스너에서 BeansUtil.getBean(BrandRepository.class)를 호출하면 해당 BrandRepository객체를 주입시킬 수 있음.
 */
@Component
public class BeansUtil implements ApplicationContextAware {

  //bean객체를 반환할 ApplicationContext객체
  @Getter
  private static ApplicationContext applicationContext;

  //ApplicationContext객체 주입.
  @Override
  public void setApplicationContext(ApplicationContext applicationContext)
    throws BeansException {
    BeansUtil.applicationContext = applicationContext;
  }

  //ApplicationContext의 getBean(Class<T>)로 의존성주입처리.
  public static <T> T getBean(Class<T> cls) {
    return applicationContext.getBean(cls);
  }
}
