package com.github.devkorol.blocks.business.control.core.execution.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Builder
@Setter(AccessLevel.PRIVATE)
public class MismatchElement {

  /**
   * name of field
   */
  @NonNull
  private String name;

  /**
   * Full path to field in provided object
   */
  @NonNull
  private String path;

  /**
   * field value
   */
  @NonNull
  private Object value;
}
