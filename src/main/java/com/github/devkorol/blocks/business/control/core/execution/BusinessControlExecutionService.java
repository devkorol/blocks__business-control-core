package com.github.devkorol.blocks.business.control.core.execution;

import com.github.devkorol.blocks.business.control.core.execution.arg.BusinessControlArgService;
import com.github.devkorol.blocks.business.control.core.execution.arg.model.ArgModel;
import com.github.devkorol.blocks.business.control.core.execution.filter.BusinessControlFilterChain;
import com.github.devkorol.blocks.business.control.core.execution.invocation.BusinessControlInvokeService;
import com.github.devkorol.blocks.business.control.core.execution.locator.BusinessControlLocator;
import com.github.devkorol.blocks.business.control.core.execution.locator.metamodel.ArgMetamodel;
import com.github.devkorol.blocks.business.control.core.execution.locator.model.BusinessControlLocatorModel;
import com.github.devkorol.blocks.business.control.core.execution.metamodel.BusinessControlArgMetamodelService;
import com.github.devkorol.blocks.business.control.core.execution.mismatch.BusinessControlMismatchBuilder;
import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionModel;
import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionRequest;
import com.github.devkorol.blocks.business.control.core.execution.model.Mismatch;
import com.github.devkorol.blocks.business.control.core.execution.model.ReportEntity;
import com.github.devkorol.blocks.business.control.core.execution.param.BusinessControlParamService;
import com.github.devkorol.blocks.business.control.core.execution.param.model.BusinessControlParamMap;
import com.github.devkorol.blocks.business.control.core.execution.settings.BusinessControlSettingsService;
import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessControlExecutionService {

  private final BusinessControlSettingsService settingsService;
  private final BusinessControlFilterChain filterChain;
  private final BusinessControlLocator businessControlLocator;
  private final BusinessControlArgMetamodelService argMetamodelService;
  private final BusinessControlParamService paramService;
  private final BusinessControlArgService argService;
  private final BusinessControlInvokeService invokeService;
  private final BusinessControlMismatchBuilder mismatchBuilder;

  public <T> Optional<Mismatch> execute(BusinessControlExecutionRequest<T> request) {
    BusinessControlSettingsModel settings = settingsService.get(request);

    if (!filterChain.isActiveForRequest(request, settings)) {
      log.debug("Business control [{}] is disabled for request via settings or filters", request.getCode());
      return Optional.empty();
    }

    BusinessControlLocatorModel beanModel = businessControlLocator.locate(request.getCode(), request.getFlowCode());
    BusinessControlParamMap params = paramService.get(request);

    ArgMetamodel metamodel = argMetamodelService.findMetamodel(request.getModel(), beanModel);
    List<ArgModel> args = argService.read(request.getModel(), metamodel);

    BusinessControlExecutionModel<T> model = new BusinessControlExecutionModel<>(request, settings, params);
    ReportEntity reportEntity = invokeService.invoke(beanModel, model, args);

    return mismatchBuilder.build(reportEntity, model, args, beanModel.getInvocationMethodArgNames());
  }
}
