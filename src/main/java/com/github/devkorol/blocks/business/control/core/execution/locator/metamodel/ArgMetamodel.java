package com.github.devkorol.blocks.business.control.core.execution.locator.metamodel;

import com.github.devkorol.blocks.business.control.core.execution.locator.model.ArgPathModel;
import java.util.List;

public interface ArgMetamodel {

  /**
   * @return supported by this object DTO
   */
  Class<?> supportedModel();

  /**
   * @return collection of paths regarding supported model. All paths will be processes by SPeL to extract data.
   */
  List<ArgPathModel> path();
}
