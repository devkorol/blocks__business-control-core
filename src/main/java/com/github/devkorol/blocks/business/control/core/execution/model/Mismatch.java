package com.github.devkorol.blocks.business.control.core.execution.model;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;

@Data
@Builder
@Setter(AccessLevel.PRIVATE)
public class Mismatch {

  /**
   * unique business control code
   */
  @NonNull
  private String code;

  /**
   * true if error in this business control block flow execution
   */
  @NonNull
  private boolean critical;

  /**
   * describe error message of business control for report
   */
  @NonNull
  private String message;

  /**
   * collection of fields in model that causes business control fault
   */
  @Singular
  private List<MismatchElement> elements;
}
