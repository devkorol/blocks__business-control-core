package com.github.devkorol.blocks.business.control.core.execution.param;

import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionRequest;
import com.github.devkorol.blocks.business.control.core.execution.param.model.BusinessControlParamMap;
import com.github.devkorol.blocks.business.control.core.execution.param.provider.BusinessControlParamProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessControlParamService {

  private final BusinessControlParamProvider paramProvider;

  public BusinessControlParamMap get(BusinessControlExecutionRequest<?> request) {
    log.debug("Start finding business control params for code [{}], flow [{}], date [{}]",
        request.getCode(), request.getFlowCode(), request.getOnDate());

    BusinessControlParamMap paramMap = paramProvider.get(
        request.getCode(), request.getFlowCode(), request.getOnDate());

    log.debug("Found business control paramMap [{}]", paramMap);
    return paramMap;
  }
}
