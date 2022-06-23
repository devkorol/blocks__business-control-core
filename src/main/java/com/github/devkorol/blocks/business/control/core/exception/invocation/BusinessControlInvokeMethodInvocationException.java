package com.github.devkorol.blocks.business.control.core.exception.invocation;


import java.util.Arrays;
import lombok.Getter;

@Getter
public class BusinessControlInvokeMethodInvocationException extends BusinessControlInvokeMethodException {

  /**
   * business control bean
   */
  private final String methodName;

  private final Class<?> bean;

  public BusinessControlInvokeMethodInvocationException(String methodName, Class<?> bean,
      Object[] methodArguments, Throwable e) {
    super(buildMessage(methodName, bean, methodArguments), e);
    this.methodName = methodName;
    this.bean = bean;
  }

  private static String buildMessage(String methodName, Class<?> bean, Object[] methodArguments) {
    return String.format("Exception in business control bean [%s] method [%s] with arguments %s ",
        bean.getName(), methodName, Arrays.toString(methodArguments));
  }
}
