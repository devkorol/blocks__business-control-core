package com.github.devkorol.blocks.business.control.core.exception.metamodel;


import lombok.Getter;

@Getter
public class BusinessControlArgMetamodelNotFoundException extends BusinessControlArgMetamodelException {

  /**
   * business control bean
   */
  private final Class<?> bean;

  public BusinessControlArgMetamodelNotFoundException(Class<?> bean, Exception e) {
    super("Cant find arg metamodel bean in context for business control bean [" + bean.getName() + "]", e);
    this.bean = bean;
  }
}
