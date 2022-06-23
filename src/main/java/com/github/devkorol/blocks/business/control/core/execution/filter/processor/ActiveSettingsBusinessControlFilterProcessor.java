package com.github.devkorol.blocks.business.control.core.execution.filter.processor;

import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionRequest;
import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;
import org.springframework.stereotype.Component;

@Component
public class ActiveSettingsBusinessControlFilterProcessor implements BusinessControlFilterProcessor {

  @Override
  public <T> boolean execute(BusinessControlExecutionRequest<T> request, BusinessControlSettingsModel settings) {
    return settings.isActive();
  }
}
