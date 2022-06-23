package com.github.devkorol.blocks.business.control.core.execution.param.provider;

import static com.github.devkorol.blocks.business.control.core.config.BusinessControlCacheConfig.BUSINESS_CONTROL_CACHE;
import static com.github.devkorol.blocks.business.control.core.config.BusinessControlCacheConfig.BUSINESS_CONTROL_PARAM_CACHE;

import com.github.devkorol.blocks.business.control.core.execution.param.model.BusinessControlParamMap;
import java.time.OffsetDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;

/**
 * DefaultBusinessControlSettingsProvider - just common param provider realization. Should be overrode with at least
 * more specific for each code.
 */
@Slf4j
public class DefaultBusinessControlParamProvider implements BusinessControlParamProvider {

  @Override
  @Cacheable(cacheNames = BUSINESS_CONTROL_PARAM_CACHE, cacheManager = BUSINESS_CONTROL_CACHE)
  public BusinessControlParamMap get(
      String code, @Nullable String flowCode, @Nullable OffsetDateTime onDate) {
    log.debug("Return default params for business control {}", code);
    return new BusinessControlParamMap();
  }
}
