package com.github.devkorol.blocks.business.control.core.execution.locator.method;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BusinessControlParseInvocationMethod {

  public List<String> parseArgumentNames(Method invocationMethod) {
    List<String> arguments = Arrays.stream(invocationMethod.getParameters())
        .map(Parameter::getName)
        .collect(Collectors.toList());

    log.debug("for method [{}] found arguments [{}]", invocationMethod.getName(), arguments);
    return arguments;
  }
}
