package com.github.devkorol.blocks.business.control.core.exception.invocation;


import lombok.Getter;

@Getter
public class BusinessControlInvokeMethodNotFoundException extends BusinessControlInvokeMethodException {

  /**
   * business control bean
   */
  private final String methodName;

  private final Class<?> bean;

  public BusinessControlInvokeMethodNotFoundException(String methodName, Class<?> bean, Exception e) {
    super(buildMessage(methodName, bean), e);
    this.methodName = methodName;
    this.bean = bean;
  }

  public BusinessControlInvokeMethodNotFoundException(String methodName, Class<?> bean) {
    super(buildMessage(methodName, bean));
    this.methodName = methodName;
    this.bean = bean;
  }

  private static String buildMessage(String methodName, Class<?> bean) {
    return String.format("Cant find invoke method [%s] for business control bean [%s]",
        methodName, bean.getName());
  }
}
