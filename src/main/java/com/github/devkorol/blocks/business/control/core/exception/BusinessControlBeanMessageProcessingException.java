package com.github.devkorol.blocks.business.control.core.exception;


import java.util.Map;
import lombok.Getter;

@Getter
public class BusinessControlBeanMessageProcessingException extends BusinessControlException {

  private final String message;
  private final Map<String, Object> messageParams;

  public BusinessControlBeanMessageProcessingException(String message, Map<String, Object> messageParams, Throwable e) {
    super(getMessage(message, messageParams), e);
    this.message = message;
    this.messageParams = messageParams;
  }

  private static String getMessage(String message, Map<String, Object> messageParams) {
    return "Cant process template message [" + message + "] with params [" + messageParams + "]";
  }
}
