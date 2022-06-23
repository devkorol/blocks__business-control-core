package com.github.devkorol.blocks.business.control.core.execution.locator.method;

import com.github.devkorol.blocks.business.control.core.BusinessControl;
import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodNotAccessibleException;
import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodNotFoundException;
import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodOverloadingException;
import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodReturnTypeMismatchException;
import com.github.devkorol.blocks.business.control.core.execution.model.ReportEntity;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BusinessControlLocateInvocationMethod {

  /**
   * Read all bean declared methods to find one unique that match provided name in {@link BusinessControl} attribute
   * invocationMethod.
   */
  public Method locate(String invocationMethodName, Class<?> beanClass) {
    Method[] methods = getDeclaredMethods(invocationMethodName, beanClass);

    List<Method> namedMethods = Arrays.stream(methods)
        .filter(m -> m.getName().equalsIgnoreCase(invocationMethodName))
        .collect(Collectors.toList());
    validateMethodsByName(invocationMethodName, beanClass, namedMethods);

    Method method = namedMethods.get(0);
    validateMethod(invocationMethodName, beanClass, method);

    return method;
  }

  private Method[] getDeclaredMethods(String invocationMethodName, Class<?> beanClass) {
    try {
      return beanClass.getDeclaredMethods();
    } catch (Exception e) {
      throw new BusinessControlInvokeMethodNotFoundException(invocationMethodName, beanClass, e);
    }
  }

  private void validateMethodsByName(String invocationMethodName, Class<?> beanClass, List<Method> namedMethods) {
    if (namedMethods.isEmpty()) {
      throw new BusinessControlInvokeMethodNotFoundException(invocationMethodName, beanClass);
    }
    if (namedMethods.size() > 1) {
      throw new BusinessControlInvokeMethodOverloadingException(invocationMethodName, beanClass);
    }
  }

  private void validateMethod(String invocationMethodName, Class<?> beanClass, Method method) {
    if (!Modifier.isPublic(method.getModifiers())) {
      throw new BusinessControlInvokeMethodNotAccessibleException(invocationMethodName, beanClass);
    }
    if (method.getReturnType() != ReportEntity.class) {
      throw new BusinessControlInvokeMethodReturnTypeMismatchException(invocationMethodName, beanClass);
    }
  }
}
