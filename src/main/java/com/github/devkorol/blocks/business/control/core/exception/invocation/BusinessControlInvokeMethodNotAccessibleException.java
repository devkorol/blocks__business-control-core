package com.github.devkorol.blocks.business.control.core.exception.invocation;


import lombok.Getter;

@Getter
public class BusinessControlInvokeMethodNotAccessibleException extends BusinessControlInvokeMethodException {

  /**
   * business control bean
   */
  private final String methodName;

  private final Class<?> bean;

  public BusinessControlInvokeMethodNotAccessibleException(String methodName, Class<?> bean) {
    super(buildMessage(methodName, bean));
    this.methodName = methodName;
    this.bean = bean;
  }

  private static String buildMessage(String methodName, Class<?> bean) {
    return String.format("Invoke method [%s] for business control bean [%s] should be public",
        methodName, bean.getName());
  }
}
