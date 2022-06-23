package com.github.devkorol.blocks.business.control.core.execution.model;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessControlExecutionRequest<T> {

  /**
   * unique business control code
   */
  @NonNull
  private String code;

  /**
   * unique code of flow where business control is executing
   */
  @Nullable
  private String flowCode;

  /**
   * date-time in what period will be looked up settings
   */
  @Nullable
  private OffsetDateTime onDate;

  /**
   * Input data for verify with business control
   */
  @NonNull
  private T model;
}
