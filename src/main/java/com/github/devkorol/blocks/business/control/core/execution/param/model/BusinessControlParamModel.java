package com.github.devkorol.blocks.business.control.core.execution.param.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class BusinessControlParamModel<T> {

  private final String name;
  private final T value;

}


