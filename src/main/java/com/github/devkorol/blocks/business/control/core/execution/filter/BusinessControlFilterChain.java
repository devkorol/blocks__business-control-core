package com.github.devkorol.blocks.business.control.core.execution.filter;

import static java.util.Objects.isNull;

import com.github.devkorol.blocks.business.control.core.execution.filter.config.DefaultBusinessControlFilterChainProperties;
import com.github.devkorol.blocks.business.control.core.execution.filter.processor.BusinessControlFilterProcessor;
import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionRequest;
import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessControlFilterChain {

  private final DefaultBusinessControlFilterChainProperties defaultProperties;
  private final List<BusinessControlFilterProcessor> filters;

  /**
   * Pass request and settings throught filter chain to define should business control be launched.
   *
   * @param request  request for execution business control
   * @param settings business control settings
   * @return false if one of filters return false
   */
  public <T> boolean isActiveForRequest(BusinessControlExecutionRequest<T> request,
      BusinessControlSettingsModel settings) {
    Boolean passed = null;

    for (BusinessControlFilterProcessor filter : filters) {
      passed = filter.execute(request, settings);

      if (Boolean.FALSE.equals(passed)) {
        log.debug("Request with code [{}] was filtered to false by filter [{}]", request.getCode(), filter.getClass());
        break;
      }
    }

    if (isNull(passed)) {
      log.warn("No filters was applied to request with code [{}]. Return default value", request.getCode());
      passed = defaultProperties.isPass();
    }

    return passed;
  }
}
