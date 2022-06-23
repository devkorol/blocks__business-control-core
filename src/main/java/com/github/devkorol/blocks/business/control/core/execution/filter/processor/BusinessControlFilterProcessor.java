package com.github.devkorol.blocks.business.control.core.execution.filter.processor;

import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionRequest;
import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;

public interface BusinessControlFilterProcessor {

  /**
   * Evaluate request and business control settings to define if business control should be executed.
   *
   * @param request  request for execution business control
   * @param settings business control settings
   * @return true if business control should be executed, false if not
   */
  <T> boolean execute(BusinessControlExecutionRequest<T> request, BusinessControlSettingsModel settings);
}
