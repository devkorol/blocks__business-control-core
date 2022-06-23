package com.github.devkorol.blocks.business.control.core.execution.locator.starter;

import com.github.devkorol.blocks.business.control.core.BusinessControl;
import com.github.devkorol.blocks.business.control.core.exception.metamodel.BusinessControlArgMetamodelNotFoundException;
import com.github.devkorol.blocks.business.control.core.execution.locator.metamodel.ArgMetamodel;
import com.github.devkorol.blocks.business.control.core.execution.locator.method.BusinessControlLocateInvocationMethod;
import com.github.devkorol.blocks.business.control.core.execution.locator.method.BusinessControlParseInvocationMethod;
import com.github.devkorol.blocks.business.control.core.execution.locator.model.BusinessControlLocatorModel;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessControlStarter implements CommandLineRunner {

  private final ApplicationContext context;
  private final BusinessControlLocateInvocationMethod locateInvocationMethod;
  private final BusinessControlParseInvocationMethod parseInvocationMethod;

  @Getter
  private List<BusinessControlLocatorModel> businessControlBeans;

  /**
   * On application start build collection of all business control beans with meta information for further lookup.
   */
  @Override
  public void run(String... args) throws Exception {
    Map<String, Object> allBeansWithNames = context.getBeansWithAnnotation(BusinessControl.class);
    businessControlBeans = new ArrayList<>(allBeansWithNames.size());

    for (Entry<String, Object> bean : allBeansWithNames.entrySet()) {
      BusinessControl annotation = context.findAnnotationOnBean(bean.getKey(), BusinessControl.class);
      log.debug("bean [{}] annotated with code [{}] and flow code [{}]",
          bean.getKey(), annotation.code(), annotation.flowCode());

      Method executionMethod = locateInvocationMethod.locate(annotation.invocationMethod(), bean.getValue().getClass());
      businessControlBeans.add(BusinessControlLocatorModel.builder()
          .code(annotation.code())
          .flowCode(StringUtils.hasText(annotation.flowCode())
              ? annotation.flowCode()
              : null)
          .bean(bean.getValue())
          .invocationMethod(executionMethod)
          .invocationMethodArgNames(parseInvocationMethod.parseArgumentNames(executionMethod))
          .argMetamodels(findArgMetamodels(bean, annotation))
          .build());
    }
  }

  /**
   * Collect arg metamodel defined in annotation from context by it classes from application context.
   */
  private List<ArgMetamodel> findArgMetamodels(Entry<String, Object> bean, BusinessControl annotation) {
    try {
      return Arrays.stream(annotation.argMetamodels())
          .map(context::getBean)
          .collect(Collectors.toList());
    } catch (Exception e) {
      throw new BusinessControlArgMetamodelNotFoundException(bean.getClass(), e);
    }
  }
}
