package com.github.devkorol.blocks.business.control.core.execution.arg.metamodel;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.devkorol.blocks.business.control.core.execution.locator.metamodel.ArgMetamodel;
import com.github.devkorol.blocks.business.control.core.execution.locator.model.ArgPathModel;
import com.github.devkorol.blocks.business.control.core.execution.locator.model.BusinessControlLocatorModel;
import com.github.devkorol.blocks.business.control.core.execution.metamodel.BusinessControlArgMetamodelService;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.util.ReflectionUtils;

class BusinessControlArgMetamodelServiceTest {

  private final BusinessControlArgMetamodelService service = new BusinessControlArgMetamodelService();

  private static Stream<Arguments> findMetamodelArgs() {
    return Stream.of(
        //simple
        Arguments.of("1", "valid", singletonList(get(String.class, "valid"))),
        //multi models
        Arguments.of("1", "valid", asList(get(Long.class, "invalid"), get(String.class, "valid"))),
        //super class models
        Arguments.of(1L, "valid", asList(get(String.class, "invalid"), get(Number.class, "valid"))),
        //direct class has more priority then super class
        Arguments.of(1L, "valid", asList(get(Number.class, "invalid"), get(Long.class, "valid")))
    );
  }

  @ParameterizedTest
  @MethodSource("findMetamodelArgs")
  void findMetamodel(Object model, String expected, List<ArgMetamodel> metamodels) {
    BusinessControlLocatorModel businessControlLocatorModel = BusinessControlLocatorModel.builder()
        .argMetamodels(metamodels)
        .code("1")
        .bean(new Object())
        .invocationMethod(ReflectionUtils.findMethod(Object.class, "toString"))
        .invocationMethodArgNames(singletonList("a"))
        .build();

    ArgMetamodel metamodel = service.findMetamodel(model, businessControlLocatorModel);
    assertEquals(expected, metamodel.path().get(0).getPath());
  }

  private static ArgMetamodel get(Class<?> supportedModel, String path) {
    return new ArgMetamodel() {
      @Override
      public Class<?> supportedModel() {
        return supportedModel;
      }

      @Override
      public List<ArgPathModel> path() {
        return singletonList(ArgPathModel.builder()
            .name(path)
            .path(path)
            .build());
      }
    };
  }
}