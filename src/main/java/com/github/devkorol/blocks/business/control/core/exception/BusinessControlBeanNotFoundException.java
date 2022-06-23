package com.github.devkorol.blocks.business.control.core.exception;


import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class BusinessControlBeanNotFoundException extends BusinessControlException {

  /**
   * unique business control code
   */
  private final String code;

  /**
   * unique code of flow where business control is executing
   */
  @Nullable
  private final String flowCode;

  public BusinessControlBeanNotFoundException(String code, String flowCode) {
    super("Cant find business control bean for code [" + code + "] and flowCode [" + flowCode + "]");
    this.code = code;
    this.flowCode = flowCode;
  }
}
