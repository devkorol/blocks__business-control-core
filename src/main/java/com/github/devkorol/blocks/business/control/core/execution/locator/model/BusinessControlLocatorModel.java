package com.github.devkorol.blocks.business.control.core.execution.locator.model;

import com.github.devkorol.blocks.business.control.core.execution.locator.metamodel.ArgMetamodel;
import java.lang.reflect.Method;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Data
@Builder
@Setter(AccessLevel.PRIVATE)
public class BusinessControlLocatorModel {

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
   * spring bean witch uses to execute this business control
   */
  @NonNull
  private Object bean;

  /**
   * Bean level public method to execute business control. Signature must match metamodel params and types.
   */
  @NonNull
  private Method invocationMethod;

  /**
   * List of arguments regarding invocation method. it will be used in mismatch building.
   */
  @NonNull
  private List<String> invocationMethodArgNames;

  /**
   * List with all metamodels classes for this business control. They help's to map input object to individual params.
   */
  @NonNull
  private List<ArgMetamodel> argMetamodels;

}
