package com.github.devkorol.blocks.business.control.core.execution.filter.processor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionRequest;
import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActiveSettingsBusinessControlFilterProcessorTest {

  ActiveSettingsBusinessControlFilterProcessor filter;

  @BeforeEach
  void setUp() {
    filter = new ActiveSettingsBusinessControlFilterProcessor();
  }

  @Test
  void execute_Positive() {
    boolean passed = filter.execute(
        getRequest(),
        BusinessControlSettingsModel.builder().active(true).critical(true).build());

    assertTrue(passed, "Active settings should be passed");
  }

  @Test
  void execute_Negative() {
    boolean passed = filter.execute(
        getRequest(),
        BusinessControlSettingsModel.builder().active(false).critical(true).build());

    assertFalse(passed, "Active settings should be skipped");
  }

  private BusinessControlExecutionRequest<Object> getRequest() {
    return BusinessControlExecutionRequest.builder().code("1").model(new Object()).build();
  }
}