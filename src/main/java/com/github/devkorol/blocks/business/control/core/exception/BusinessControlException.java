package com.github.devkorol.blocks.business.control.core.exception;

public class BusinessControlException extends RuntimeException {

  public BusinessControlException(String message) {
    super(message);
  }

  public BusinessControlException(String message, Throwable cause) {
    super(message, cause);
  }

  public BusinessControlException(Exception e) {
    super(e);
  }
}
