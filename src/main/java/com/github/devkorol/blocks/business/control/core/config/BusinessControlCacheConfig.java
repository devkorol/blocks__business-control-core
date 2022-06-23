package com.github.devkorol.blocks.business.control.core.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class BusinessControlCacheConfig {

  public static final String BUSINESS_CONTROL_CACHE = "BUSINESS_CONTROL_CACHE";
  public static final String BUSINESS_CONTROL_BEAN_LOOKUP_CACHE = "BUSINESS_CONTROL_BEAN_LOOKUP_CACHE";
  public static final String BUSINESS_CONTROL_SETTINGS_CACHE = "BUSINESS_CONTROL_SETTINGS_CACHE";
  public static final String BUSINESS_CONTROL_PARAM_CACHE = "BUSINESS_CONTROL_PARAM_CACHE";

  @Bean(BUSINESS_CONTROL_CACHE)
  public CacheManager businessControlBeanCache() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager(
        BUSINESS_CONTROL_SETTINGS_CACHE, BUSINESS_CONTROL_BEAN_LOOKUP_CACHE, BUSINESS_CONTROL_PARAM_CACHE);
    cacheManager.setCaffeine(Caffeine.newBuilder());
    return cacheManager;
  }
}
