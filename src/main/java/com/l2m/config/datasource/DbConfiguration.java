package com.l2m.config.datasource;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * DB Config
 */
@Configuration
@RequiredArgsConstructor
@PropertySource(value = { "classpath:application.yml" }) // application yml 읽어오기
@EnableJpaRepositories(
  basePackages = "com.l2m.repository",
  entityManagerFactoryRef = "entityManager", // entityManagerFactory bean 명
  transactionManagerRef = "transactionManager" // transactionManager bean 명
)
public class DbConfiguration {
  // hibernate 설정
  private HashMap<String, Object> hibernateConfig = new HashMap<>();

  @NonNull
  private final Environment env;

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean entityManager() {
    final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

    // datasource
    em.setDataSource(dataSource());

    // entity package 경로
    em.setPackagesToScan(
      new String[] {
        "com.l2m.domain",
      }
    );

    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

    hibernateConfig.put(
      "hibernate.hbm2ddl.auto",
      env.getProperty("spring.jpa.hibernate.ddl-auto")
    );
    hibernateConfig.put(
      "hibernate.dialect",
      env.getProperty("spring.jpa.database-platform")
    );
    em.setJpaPropertyMap(hibernateConfig);

    return em;
  }

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    // spring.second-datasource 안에 프로퍼티명이 정확할 경우 아래처럼 단축 사용 가능
    return DataSourceBuilder.create().build();
  }

  @Bean
  @Primary
  public PlatformTransactionManager transactionManager() {
    final JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
      entityManager().getObject()
    );
    return transactionManager;
  }
}
