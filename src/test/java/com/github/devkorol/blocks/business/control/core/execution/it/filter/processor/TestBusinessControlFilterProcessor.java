package com.github.devkorol.blocks.business.control.core.execution.it.filter.processor;

import static com.github.devkorol.blocks.business.control.core.execution.it.settings.provider.TestBusinessControlSettingsProvider.BC_100_AC_P_INACTIVE_BY_FILTER;

import com.github.devkorol.blocks.business.control.core.execution.filter.processor.BusinessControlFilterProcessor;
import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionRequest;
import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;
import org.springframework.stereotype.Component;

@Component
class TestBusinessControlFilterProcessor implements BusinessControlFilterProcessor {

  @Override
  public <T> boolean execute(BusinessControlExecutionRequest<T> request, BusinessControlSettingsModel settings) {
    return !settings.getAdditionalProperties()
        .containsKey(BC_100_AC_P_INACTIVE_BY_FILTER);
  }
}