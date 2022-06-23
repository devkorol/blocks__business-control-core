package com.github.devkorol.blocks.business.control.core.exception.metamodel;


import lombok.Getter;

@Getter
public class BusinessControlArgMetamodelForClassNotFoundException extends BusinessControlArgMetamodelException {

  /**
   * business control bean
   */
  private final Class<?> bean;

  /**
   * input model class
   */
  private final Class<?> model;

  public BusinessControlArgMetamodelForClassNotFoundException(Class<?> bean, Class<?> model) {
    super("Cant find arg metamodel for type [" + model.getName()
        + "] described in business control bean [" + bean.getName() + "]");
    this.bean = bean;
    this.model = model;
  }
}
