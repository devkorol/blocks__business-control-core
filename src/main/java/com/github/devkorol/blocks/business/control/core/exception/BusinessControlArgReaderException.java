package com.github.devkorol.blocks.business.control.core.exception;


import lombok.Getter;

@Getter
public class BusinessControlArgReaderException extends BusinessControlException {

  /**
   * accessor path
   */
  private final String path;

  public BusinessControlArgReaderException(String path, Exception e) {
    super("Invalid SPeL expression [" + path + "]", e);
    this.path = path;
  }
}
