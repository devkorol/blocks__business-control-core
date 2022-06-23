package com.github.devkorol.blocks.business.control.core.execution.settings.provider;

import static com.github.devkorol.blocks.business.control.core.config.BusinessControlCacheConfig.BUSINESS_CONTROL_CACHE;
import static com.github.devkorol.blocks.business.control.core.config.BusinessControlCacheConfig.BUSINESS_CONTROL_SETTINGS_CACHE;

import com.github.devkorol.blocks.business.control.core.execution.settings.config.DefaultBusinessControlSettingsProperties;
import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;

/**
 * DefaultBusinessControlSettingsProvider - just common settings provider realization. Should be overrode with at least
 * more specific settings for each code.
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultBusinessControlSettingsProvider implements BusinessControlSettingsProvider {

  private final DefaultBusinessControlSettingsProperties properties;

  @Override
  @Cacheable(cacheNames = BUSINESS_CONTROL_SETTINGS_CACHE, cacheManager = BUSINESS_CONTROL_CACHE)
  public BusinessControlSettingsModel get(String code, @Nullable String flowCode, @Nullable OffsetDateTime onDate) {
    log.debug("Return default settings for business control {}", code);
    return BusinessControlSettingsModel.builder()
        .active(properties.isActive())
        .critical(properties.isCritical())
        .build();
  }
}
