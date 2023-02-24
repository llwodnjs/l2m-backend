package com.l2m.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;;

@Configuration
public class WebMvnConfiguration implements WebMvcConfigurer {
  @Value("${filepath.root}")
  private String root;

  @Value("${filepath.contentDir}")
  private String contentDir;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/file/**")
            .addResourceLocations("file:" + root + contentDir)
            .setCachePeriod(20);
  }
}
