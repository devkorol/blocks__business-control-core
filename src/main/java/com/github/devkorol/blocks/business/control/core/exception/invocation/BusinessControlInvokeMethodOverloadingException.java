package com.github.devkorol.blocks.business.control.core.exception.invocation;


import lombok.Getter;

@Getter
public class BusinessControlInvokeMethodOverloadingException extends BusinessControlInvokeMethodException {

  /**
   * business control bean
   */
  private final String methodName;

  private final Class<?> bean;

  public BusinessControlInvokeMethodOverloadingException(String methodName, Class<?> bean) {
    super(buildMessage(methodName, bean));
    this.methodName = methodName;
    this.bean = bean;
  }

  private static String buildMessage(String methodName, Class<?> bean) {
    return String.format(
        "Overloading of invocation method [%s] for business control bean [%s] is not supported, name must be unique",
        methodName, bean.getName());
  }
}
