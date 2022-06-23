package com.github.devkorol.blocks.business.control.core.exception.invocation;


import static java.util.Objects.nonNull;

import java.util.StringJoiner;
import lombok.Getter;

@Getter
public class BusinessControlInvokeMethodIllegalArgumentException extends BusinessControlInvokeMethodException {

  /**
   * business control bean
   */
  private final String methodName;

  private final Class<?> bean;

  public BusinessControlInvokeMethodIllegalArgumentException(String methodName, Class<?> bean,
      Object[] methodArguments, Throwable e) {
    super(buildMessage(methodName, bean, methodArguments), e);
    this.methodName = methodName;
    this.bean = bean;
  }

  private static String buildMessage(String methodName, Class<?> bean, Object[] methodArguments) {
    return String.format("Cant apply arguments [%s] to invoking method [%s] for business control bean [%s]",
        toStringArguments(methodArguments), methodName, bean.getName());
  }

  private static String toStringArguments(Object[] methodArguments) {
    StringJoiner joiner = new StringJoiner(", ");
    for (Object methodArgument : methodArguments) {
      joiner.add(nonNull(methodArgument)
          ? methodArgument.getClass().getName()
          : "null");
    }
    return joiner.toString();
  }
}
