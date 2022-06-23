package com.github.devkorol.blocks.business.control.core.execution.model;

import com.github.devkorol.blocks.business.control.core.execution.param.model.BusinessControlParamMap;
import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class BusinessControlExecutionModel<T> {

  @NonNull
  private BusinessControlExecutionRequest<T> request;

  @NonNull
  private BusinessControlSettingsModel settings;

  @NonNull
  private BusinessControlParamMap params;
}
