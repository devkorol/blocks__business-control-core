package com.github.devkorol.blocks.business.control.core.execution.locator.metamodel;

public interface ArgMetamodel {

  /**
   * @return supported by this object DTO
   */
  Class<?> supportedModel();

  /**
   * @return array of paths regarding supported DTO. All paths will be processes by SPeL to extract data from DTO.
   */
  String[] path();


  //TODO add DSL
  default String[] of(String... paths) {
    return paths;
  }

  default String join(String... parts) {
    return String.join("?.", parts);
  }
}
