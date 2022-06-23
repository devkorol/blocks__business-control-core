package com.github.devkorol.blocks.business.control.core.execution.arg.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
@Setter(AccessLevel.PRIVATE)
public class ArgModel {

  /**
   * Field full path in a given model.
   */
  private String path;

  /**
   * Field name in a given model.
   */
  private String name;

  /**
   * Field value from a model by path.
   */
  private Object value;
}
