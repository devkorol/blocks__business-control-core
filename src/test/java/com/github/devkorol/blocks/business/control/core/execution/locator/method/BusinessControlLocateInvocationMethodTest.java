package com.github.devkorol.blocks.business.control.core.execution.locator.method;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodNotAccessibleException;
import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodNotFoundException;
import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodOverloadingException;
import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodReturnTypeMismatchException;
import com.github.devkorol.blocks.business.control.core.execution.model.ReportEntity;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusinessControlLocateInvocationMethodTest {

  private BusinessControlLocateInvocationMethod methodLocate;

  @BeforeEach
  void setUp() {
    methodLocate = new BusinessControlLocateInvocationMethod();
  }

  @Test
  void locate_NoSuchMethod() {
    assertThrows(
        BusinessControlInvokeMethodNotFoundException.class,
        () -> methodLocate.locate("invalid", TestClass.class));
  }

  @Test
  void locate_PrivateMethod() {
    assertThrows(
        BusinessControlInvokeMethodNotAccessibleException.class,
        () -> methodLocate.locate("privateMethod", TestClass.class));
  }

  @Test
  void locate_OverloadMethod() {
    assertThrows(
        BusinessControlInvokeMethodOverloadingException.class,
        () -> methodLocate.locate("overloadMethod", TestClass.class));
  }

  @Test
  void locate_MethodReturnMismatch() {
    assertThrows(
        BusinessControlInvokeMethodReturnTypeMismatchException.class,
        () -> methodLocate.locate("methodReturnMismatch", TestClass.class));
  }

  @Test
  void locate() {
    Method method = methodLocate.locate("execute", TestClass.class);
    assertNotNull(method);
  }


  public static class TestClass {

    private ReportEntity privateMethod(String s) {
      return null;
    }

    public ReportEntity overloadMethod(String s) {
      return null;
    }

    public ReportEntity overloadMethod(String s, Integer i) {
      return null;
    }

    public String methodReturnMismatch(String s, Integer i) {
      return null;
    }

    public ReportEntity execute(String s) {
      return null;
    }
  }
}