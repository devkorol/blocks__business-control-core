package com.github.devkorol.blocks.business.control.core.execution.settings.model;

import static lombok.AccessLevel.PRIVATE;

import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;

/**
 * BusinessControlSettingsModel - store basic settings for business control which will be used in execution.
 */
@Data
@Builder
@Setter(PRIVATE)
public class BusinessControlSettingsModel {

  /**
   * Set true if business control in enabled and will be executed
   */
  private boolean active;

  /**
   * Set true if error in this business control block flow execution
   */
  private boolean critical;

  /**
   * describe error message of business control for report
   */
  @NonNull
  @Builder.Default
  private String message = "Business control evaluation error";

  /**
   * Additional settings can be provided to business control
   */
  @Singular
  private Map<String, Object> additionalProperties;

}
