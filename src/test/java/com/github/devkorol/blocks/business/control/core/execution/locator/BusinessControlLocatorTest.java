package com.github.devkorol.blocks.business.control.core.execution.locator;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.devkorol.blocks.business.control.core.execution.locator.metamodel.ArgMetamodel;
import com.github.devkorol.blocks.business.control.core.execution.locator.model.BusinessControlLocatorModel;
import com.github.devkorol.blocks.business.control.core.execution.locator.starter.BusinessControlStarter;
import java.util.Collections;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.util.ReflectionUtils;

class BusinessControlLocatorTest {

  private BusinessControlLocator locator;
  private BusinessControlStarter businessControlStarter;

  @BeforeEach
  void setUp() {
    businessControlStarter = mock(BusinessControlStarter.class);
    locator = new BusinessControlLocator(businessControlStarter);
  }

  private static Stream<Arguments> locateTestArgs() {
    return Stream.of(
        //exact code and flow
        Arguments.of("code", "flow", "flow", asArray(get("code1", "flow1"), get("code", "flow"))),
        //same code but different flows
        Arguments.of("code", "flow", "flow", asArray(get("code", "flow1"), get("code", "flow"))),
        //different flow and null
        Arguments.of("code", "flow", null, asArray(get("code", "flow1"), get("code", null))),
        //exact flow and null
        Arguments.of("code", "flow", "flow", asArray(get("code", null), get("code", "flow"))),
        //null flow
        Arguments.of("code", null, null, asArray(get("code", "flow"), get("code", null)))
    );
  }

  @ParameterizedTest
  @MethodSource("locateTestArgs")
  void locate(String code, String flowCode, String expectedFlowCode, BusinessControlLocatorModel... beans) {
    when(businessControlStarter.getBusinessControlBeans()).thenReturn(asList(beans));
    BusinessControlLocatorModel locate = locator.locate(code, flowCode);

    Assertions.assertEquals(code, locate.getCode());
    Assertions.assertEquals(expectedFlowCode, locate.getFlowCode());
  }

  private static BusinessControlLocatorModel get(String code, String flow) {
    return BusinessControlLocatorModel.builder()
        .code(code)
        .flowCode(flow)
        .bean(new Object())
        .invocationMethod(ReflectionUtils.findMethod(Object.class, "toString"))
        .invocationMethodArgNames(Collections.emptyList())
        .argMetamodels(Collections.emptyList())
        .build();
  }

  private static BusinessControlLocatorModel[] asArray(BusinessControlLocatorModel... beans) {
    return beans;
  }

}