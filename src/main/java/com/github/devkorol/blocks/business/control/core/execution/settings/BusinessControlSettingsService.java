package com.github.devkorol.blocks.business.control.core.execution.settings;

import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionRequest;
import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;
import com.github.devkorol.blocks.business.control.core.execution.settings.provider.BusinessControlSettingsProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessControlSettingsService {

  private final BusinessControlSettingsProvider businessControlSettingsProvider;

  public BusinessControlSettingsModel get(BusinessControlExecutionRequest<?> request) {
    log.debug("Start finding business control settings for code [{}], flow [{}], date [{}]",
        request.getCode(), request.getFlowCode(), request.getOnDate());
    BusinessControlSettingsModel settings = businessControlSettingsProvider.get(
        request.getCode(), request.getFlowCode(), request.getOnDate());

    log.debug("Found business control settings [{}]", settings);
    return settings;
  }
}
