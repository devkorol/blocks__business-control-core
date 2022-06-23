package com.github.devkorol.blocks.business.control.core.execution.arg;

import com.github.devkorol.blocks.business.control.core.exception.metamodel.BusinessControlArgMetamodelPathEmptyException;
import com.github.devkorol.blocks.business.control.core.execution.arg.model.ArgModel;
import com.github.devkorol.blocks.business.control.core.execution.arg.reader.BusinessControlArgReader;
import com.github.devkorol.blocks.business.control.core.execution.locator.metamodel.ArgMetamodel;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessControlArgService {

  private final BusinessControlArgReader argReader;

  /**
   * Find metamodel paths in input model and retrieve values.
   */
  public <T> List<ArgModel> read(T model, ArgMetamodel metamodel) {
    if (metamodel.path() == null || metamodel.path().length == 0) {
      throw new BusinessControlArgMetamodelPathEmptyException(metamodel.getClass());
    }

    log.debug("Start reading args from input model by metamodel...");
    return Arrays.stream(metamodel.path())
        .map(p -> ArgModel.builder()
            .path(p)
            .value(argReader.get(p, model))
            .build())
        .collect(Collectors.toList());
  }
}
