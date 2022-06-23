package com.github.devkorol.blocks.business.control.core.execution.metamodel;

import static java.util.Objects.isNull;

import com.github.devkorol.blocks.business.control.core.exception.metamodel.BusinessControlArgMetamodelForClassNotFoundException;
import com.github.devkorol.blocks.business.control.core.exception.metamodel.BusinessControlArgMetamodelSupportedModelEmptyException;
import com.github.devkorol.blocks.business.control.core.execution.locator.BusinessControlLocator;
import com.github.devkorol.blocks.business.control.core.execution.locator.metamodel.ArgMetamodel;
import com.github.devkorol.blocks.business.control.core.execution.locator.model.BusinessControlLocatorModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessControlArgMetamodelService {

  /**
   * Find appropriate metamodel for input data by it class. If cant find direct match then tries to apply it by model
   * superclass.
   *
   * @param model     business control input model to check
   * @param beanModel model from step {@link BusinessControlLocator}
   * @param <T>       any types of model is supported
   */
  public <T> ArgMetamodel findMetamodel(T model, BusinessControlLocatorModel beanModel) {
    log.debug("Start finding metamodel for input class {}", model.getClass().getName());
    ArgMetamodel metamodel = null;
    ArgMetamodel metamodelSuper = null;

    for (ArgMetamodel argMetamodel : beanModel.getArgMetamodels()) {
      if (isNull(argMetamodel.supportedModel())) {
        throw new BusinessControlArgMetamodelSupportedModelEmptyException(argMetamodel.getClass());
      }

      if (argMetamodel.supportedModel().equals(model.getClass())) {
        metamodel = argMetamodel;
      } else if (argMetamodel.supportedModel().isAssignableFrom(model.getClass())) {
        metamodelSuper = argMetamodel;
      }
    }
    if (isNull(metamodel)) {
      metamodel = metamodelSuper;
    }
    if (isNull(metamodel)) {
      throw new BusinessControlArgMetamodelForClassNotFoundException(beanModel.getBean().getClass(), model.getClass());
    }
    log.debug("Found metamodel {}", metamodel.getClass().getName());
    return metamodel;
  }
}
