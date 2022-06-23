package com.github.devkorol.blocks.business.control.core.exception.metamodel;


import lombok.Getter;

@Getter
public class BusinessControlArgMetamodelPathEmptyException extends BusinessControlArgMetamodelException {

  private final Class<?> metamodel;

  public BusinessControlArgMetamodelPathEmptyException(Class<?> metamodel) {
    super("Method path return empty array in metamodel [" + metamodel.getName() + "]");
    this.metamodel = metamodel;
  }
}
