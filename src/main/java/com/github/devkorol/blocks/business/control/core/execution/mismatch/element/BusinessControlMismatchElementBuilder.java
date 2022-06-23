package com.github.devkorol.blocks.business.control.core.execution.mismatch.element;

import static java.util.Objects.isNull;

import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodMismatchArgumentNotFoundException;
import com.github.devkorol.blocks.business.control.core.execution.arg.model.ArgModel;
import com.github.devkorol.blocks.business.control.core.execution.mismatch.config.BusinessControlMismatchBuilderMode;
import com.github.devkorol.blocks.business.control.core.execution.mismatch.config.BusinessControlMismatchBuilderProperties;
import com.github.devkorol.blocks.business.control.core.execution.model.MismatchElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessControlMismatchElementBuilder {

  private final BusinessControlMismatchBuilderProperties properties;

  public Collection<MismatchElement> build(
      List<ArgModel> args,
      List<String> mismatchArgNames,
      List<String> invocationMethodArgNames) {
    List<MismatchElement> mismatchElements;

    if (BusinessControlMismatchBuilderMode.SPECIFIED.equals(properties.getMode())) {
      log.debug("Only specified arguments will be added to elements");
      mismatchElements = markSpecifiedArgsAsInvalid(args, mismatchArgNames, invocationMethodArgNames);
    } else if (BusinessControlMismatchBuilderMode.ALL.equals(properties.getMode())) {
      log.debug("All arguments will be added to elements");
      mismatchElements = markAllArgsAsInvalid(args);
    } else if (BusinessControlMismatchBuilderMode.MIXED.equals(properties.getMode())) {
      log.debug("All arguments will be added to elements in there is no specified");
      mismatchElements = markArgsAsInvalid(args, mismatchArgNames, invocationMethodArgNames);
    } else {
      log.debug("Mismatch elements building is disabled, skip...");
      mismatchElements = Collections.emptyList();
    }

    return mismatchElements;
  }

  private List<MismatchElement> markArgsAsInvalid(List<ArgModel> args, List<String> mismatchArgNames,
      List<String> invocationMethodArgNames) {
    List<MismatchElement> mismatchElements;
    if (isNull(mismatchArgNames) || mismatchArgNames.isEmpty()) {
      mismatchElements = markAllArgsAsInvalid(args);
    } else {
      mismatchElements = markSpecifiedArgsAsInvalid(args, mismatchArgNames, invocationMethodArgNames);
    }
    return mismatchElements;
  }

  private List<MismatchElement> markSpecifiedArgsAsInvalid(List<ArgModel> args, List<String> mismatchArgNames,
      List<String> invocationMethodArgNames) {
    List<MismatchElement> mismatchElements = new ArrayList<>(mismatchArgNames.size());

    for (String mismatchArgName : mismatchArgNames) {
      int argIndex = invocationMethodArgNames.indexOf(mismatchArgName);
      if (argIndex == -1) {
        throw new BusinessControlInvokeMethodMismatchArgumentNotFoundException(mismatchArgName,
            invocationMethodArgNames);
      }

      ArgModel argModel = args.get(argIndex);

      mismatchElements.add(MismatchElement.builder()
          .name("TODO")
          .path(argModel.getPath())
          .value(argModel.getValue())
          .build());
    }
    return mismatchElements;
  }

  private List<MismatchElement> markAllArgsAsInvalid(List<ArgModel> args) {
    return args.stream()
        .map(arg -> MismatchElement.builder()
            .name("TODO")
            .path(arg.getPath())
            .value(arg.getValue())
            .build())
        .collect(Collectors.toList());
  }
}
