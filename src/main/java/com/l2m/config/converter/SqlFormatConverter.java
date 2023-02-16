package com.l2m.config.converter;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;

/**
 * 쿼리 포맷팅을 위한 Converter.
 *
 * [참고]
 * https://docs.jboss.org/hibernate/orm/4.2/javadocs/org/hibernate/engine/jdbc/internal/BasicFormatterImpl.html
 *
 */
public class SqlFormatConverter extends MessageConverter {

  private BasicFormatterImpl formatter = new BasicFormatterImpl();

  @Override
  public String convert(ILoggingEvent event) {
    super.convert(event);

    return formatter.format(event.getMessage());
  }
}
