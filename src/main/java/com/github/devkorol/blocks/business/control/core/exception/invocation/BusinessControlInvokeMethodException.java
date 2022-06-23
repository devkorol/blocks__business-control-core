package com.github.devkorol.blocks.business.control.core.exception.invocation;


import com.github.devkorol.blocks.business.control.core.exception.BusinessControlException;

public class BusinessControlInvokeMethodException extends BusinessControlException {

  public BusinessControlInvokeMethodException(String message) {
    super(message);
  }

  public BusinessControlInvokeMethodException(String message, Throwable cause) {
    super(message, cause);
  }

  public BusinessControlInvokeMethodException(Exception e) {
    super(e);
  }
}
