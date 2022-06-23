package com.github.devkorol.blocks.business.control.core.execution.model;

import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;

@Data
@Builder
@Setter(AccessLevel.PRIVATE)
public class ReportEntity {

  private final boolean success;

  /**
   * Specify index of arguments that violates business control.
   */
  @NonNull
  @Singular
  private final List<String> mismatchArgNames;

  /**
   * Named params that will be used in business control message template processing.
   */
  @NonNull
  @Singular
  private final Map<String, Object> messageParams;


  public static ReportEntityBuilder ok() {
    return builder().success(true);
  }

  public static ReportEntityBuilder fail() {
    return builder().success(false);
  }
}
