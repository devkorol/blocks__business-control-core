package com.github.devkorol.blocks.business.control.core.exception.invocation;


import com.github.devkorol.blocks.business.control.core.execution.model.ReportEntity;
import lombok.Getter;

@Getter
public class BusinessControlInvokeMethodReturnTypeMismatchException extends BusinessControlInvokeMethodException {

  /**
   * business control bean
   */
  private final String methodName;

  private final Class<?> bean;

  public BusinessControlInvokeMethodReturnTypeMismatchException(String methodName, Class<?> bean) {
    super(buildMessage(methodName, bean));
    this.methodName = methodName;
    this.bean = bean;
  }

  private static String buildMessage(String methodName, Class<?> bean) {
    return String.format("Invoke method [%s] for business control bean [%s] should return [%s]",
        methodName, bean.getName(), ReportEntity.class.getName());
  }
}
