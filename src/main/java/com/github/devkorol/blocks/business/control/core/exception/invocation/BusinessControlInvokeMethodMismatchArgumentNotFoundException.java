package com.github.devkorol.blocks.business.control.core.exception.invocation;


import java.util.List;

public class BusinessControlInvokeMethodMismatchArgumentNotFoundException extends BusinessControlInvokeMethodException {

  public BusinessControlInvokeMethodMismatchArgumentNotFoundException(String argName, List<String> methodArgNames) {
    super(String.format(
        "Cant find provided mismatch argument name [%s] from known names [%s]",
        argName, methodArgNames));
  }
}
