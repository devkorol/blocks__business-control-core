package com.github.devkorol.blocks.business.control.core.exception.metamodel;


import com.github.devkorol.blocks.business.control.core.exception.BusinessControlException;

public class BusinessControlArgMetamodelException extends BusinessControlException {

  public BusinessControlArgMetamodelException(String message) {
    super(message);
  }

  public BusinessControlArgMetamodelException(String message, Throwable cause) {
    super(message, cause);
  }

  public BusinessControlArgMetamodelException(Exception e) {
    super(e);
  }
}
