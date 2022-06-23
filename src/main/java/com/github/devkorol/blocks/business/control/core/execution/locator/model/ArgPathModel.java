package com.github.devkorol.blocks.business.control.core.execution.locator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ArgPathModel {

  /**
   * SPeL path in model for param.
   */
  @NonNull
  private final String path;

  /**
   * Name of param in model, will be used in mismatch building.
   */
  @NonNull
  private final String name;
}
