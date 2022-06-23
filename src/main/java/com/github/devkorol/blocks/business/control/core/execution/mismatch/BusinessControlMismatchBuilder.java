package com.github.devkorol.blocks.business.control.core.execution.mismatch;

import com.github.devkorol.blocks.business.control.core.execution.arg.model.ArgModel;
import com.github.devkorol.blocks.business.control.core.execution.mismatch.element.BusinessControlMismatchElementBuilder;
import com.github.devkorol.blocks.business.control.core.execution.mismatch.message.BusinessControlMismatchMessageFormatter;
import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionModel;
import com.github.devkorol.blocks.business.control.core.execution.model.Mismatch;
import com.github.devkorol.blocks.business.control.core.execution.model.ReportEntity;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessControlMismatchBuilder {

  private final BusinessControlMismatchMessageFormatter messageFormatter;
  private final BusinessControlMismatchElementBuilder elementBuilder;

  public <T> Optional<Mismatch> build(
      ReportEntity reportEntity,
      BusinessControlExecutionModel<T> requestModel,
      List<ArgModel> args,
      List<String> invocationMethodArgNames) {

    if (reportEntity.isSuccess()) {
      return Optional.empty();
    }

    return Optional.of(Mismatch.builder()
        .code(requestModel.getRequest().getCode())
        .critical(requestModel.getSettings().isCritical())
        .message(
            messageFormatter.format(requestModel.getSettings().getMessage(), reportEntity.getMessageParams()))
        .elements(
            elementBuilder.build(args, reportEntity.getMismatchArgNames(), invocationMethodArgNames))
        .build());
  }
}
