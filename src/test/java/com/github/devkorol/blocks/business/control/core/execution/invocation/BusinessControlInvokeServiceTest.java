package com.github.devkorol.blocks.business.control.core.execution.invocation;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodException;
import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodIllegalArgumentException;
import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodInvocationException;
import com.github.devkorol.blocks.business.control.core.execution.arg.model.ArgModel;
import com.github.devkorol.blocks.business.control.core.execution.locator.model.BusinessControlLocatorModel;
import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionModel;
import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionRequest;
import com.github.devkorol.blocks.business.control.core.execution.model.ReportEntity;
import com.github.devkorol.blocks.business.control.core.execution.param.model.BusinessControlParamMap;
import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class BusinessControlInvokeServiceTest {

  private final BusinessControlInvokeService invokeService = new BusinessControlInvokeService();

  @Test
  void invoke() {
    ReportEntity execute = invokeService.invoke(getBean("execute"), getModel(), asList(getArg("1")));
    assertNotNull(execute);
  }

  @Test
  void invoke_Exception() {
    assertThrows(BusinessControlInvokeMethodInvocationException.class,
        () -> invokeService.invoke(getBean("executeNPE"), getModel(), asList(getArg("1"))));
  }

  @Test
  void invoke_InvalidPrivate() {
    assertThrows(BusinessControlInvokeMethodException.class,
        () -> invokeService.invoke(getBean("invalid_private"), getModel(), asList(getArg("1"))));
  }

  @Test
  void invoke_InvalidTypes() {
    assertThrows(BusinessControlInvokeMethodIllegalArgumentException.class,
        () -> invokeService.invoke(getBean("invalid_types"), getModel(), asList(getArg("1"))));
  }

  public static class TestClass {

    public ReportEntity execute(String s, BusinessControlExecutionModel model) {
      return ReportEntity.builder().build();
    }

    public ReportEntity executeNPE(String s, BusinessControlExecutionModel model) {
      throw new NullPointerException("ups i did it again");
    }

    private ReportEntity invalid_private(String s, BusinessControlExecutionModel model) {
      return null;
    }

    public ReportEntity invalid_types(String s) {
      return null;
    }

  }

  private BusinessControlLocatorModel getBean(String method) {
    return BusinessControlLocatorModel.builder()
        .code("code")
        .bean(new TestClass())
        .argMetamodels(emptyList())
        .invocationMethod(findMethod(method))
        .invocationMethodArgNames(singletonList("a"))
        .build();
  }

  private Method findMethod(String name) {
    return Arrays.stream(TestClass.class.getDeclaredMethods())
        .filter(m -> m.getName().equalsIgnoreCase(name))
        .findFirst()
        .get();
  }

  private BusinessControlExecutionModel getModel() {
    return new BusinessControlExecutionModel(
        BusinessControlExecutionRequest.builder()
            .code("1")
            .model(new Object())
            .build(),
        BusinessControlSettingsModel.builder().build(),
        new BusinessControlParamMap()
    );
  }

  private ArgModel getArg(Object value) {
    return ArgModel.builder()
        .path("1")
        .value(value)
        .build();
  }
}