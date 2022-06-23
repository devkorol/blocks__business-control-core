package com.github.devkorol.blocks.business.control.core.execution.param.provider;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.devkorol.blocks.business.control.core.execution.param.model.BusinessControlParamMap;
import org.junit.jupiter.api.Test;

class DefaultBusinessControlParamProviderTest {

  private DefaultBusinessControlParamProvider paramProvider = new DefaultBusinessControlParamProvider();

  @Test
  void get() {
    BusinessControlParamMap paramMap = paramProvider.get("", null, null);
    assertTrue(paramMap.isEmpty(), "Default param provider should return empty map");
  }
}