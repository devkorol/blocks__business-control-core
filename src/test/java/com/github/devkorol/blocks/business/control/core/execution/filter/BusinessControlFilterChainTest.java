package com.github.devkorol.blocks.business.control.core.execution.filter;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.devkorol.blocks.business.control.core.execution.filter.config.DefaultBusinessControlFilterChainProperties;
import com.github.devkorol.blocks.business.control.core.execution.filter.processor.BusinessControlFilterProcessor;
import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionRequest;
import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusinessControlFilterChainTest {

  private BusinessControlFilterChain chain;
  private DefaultBusinessControlFilterChainProperties properties;
  private BusinessControlFilterProcessor filter;


  @BeforeEach
  void setUp() {
    properties = new DefaultBusinessControlFilterChainProperties().setPass(true);
    filter = mock(BusinessControlFilterProcessor.class);

    chain = new BusinessControlFilterChain(properties, Collections.singletonList(filter));
  }

  @Test
  void isActiveForRequest_Positive() {
    when(filter.execute(any(), any())).thenReturn(true);

    boolean activeForRequest = chain.isActiveForRequest(
        getRequest(), BusinessControlSettingsModel.builder().build());

    assertTrue(activeForRequest, "Result should be populated from filter chain");
    verify(filter).execute(any(), any());
  }

  @Test
  void isActiveForRequest_Negative() {
    when(filter.execute(any(), any())).thenReturn(false);

    boolean activeForRequest = chain.isActiveForRequest(
        getRequest(), BusinessControlSettingsModel.builder().build());

    assertFalse(activeForRequest, "Result should be populated from filter chain");
    verify(filter).execute(any(), any());
  }

  @Test
  void isActiveForRequest_Default() {
    chain = new BusinessControlFilterChain(properties, Collections.emptyList());

    boolean activeForRequest = chain.isActiveForRequest(
        getRequest(), BusinessControlSettingsModel.builder().build());

    assertEquals(properties.isPass(), activeForRequest, "Result should be populated from default values");
  }

  @Test
  void isActiveForRequest_ExecuteChain() {
    BusinessControlFilterProcessor filter1 = createMock(true);
    BusinessControlFilterProcessor filter2 = createMock(false);
    BusinessControlFilterProcessor filter3 = createMock(true);

    chain = new BusinessControlFilterChain(properties, asList(filter1, filter2, filter3));

    boolean activeForRequest = chain.isActiveForRequest(
        getRequest(), BusinessControlSettingsModel.builder().build());

    assertFalse(activeForRequest, "Result should be populated from filter chain");
    verify(filter1).execute(any(), any());
    verify(filter2).execute(any(), any());
    verify(filter3, times(0)).execute(any(), any());
  }

  private BusinessControlFilterProcessor createMock(boolean pass) {
    BusinessControlFilterProcessor filter = mock(BusinessControlFilterProcessor.class);
    when(filter.execute(any(), any())).thenReturn(pass);
    return filter;
  }

  private BusinessControlExecutionRequest<Object> getRequest() {
    return BusinessControlExecutionRequest.builder()
        .code("1")
        .model(new Object()).build();
  }
}