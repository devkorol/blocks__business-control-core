package com.github.devkorol.blocks.business.control.core.exception.metamodel;


import lombok.Getter;

@Getter
public class BusinessControlArgMetamodelSupportedModelEmptyException extends BusinessControlArgMetamodelException {

  private final Class<?> metamodel;

  public BusinessControlArgMetamodelSupportedModelEmptyException(Class<?> metamodel) {
    super("Method supportedModel return null in metamodel [" + metamodel.getName() + "]");
    this.metamodel = metamodel;
  }
}
