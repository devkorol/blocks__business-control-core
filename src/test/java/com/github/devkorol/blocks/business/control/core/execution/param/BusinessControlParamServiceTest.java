package com.github.devkorol.blocks.business.control.core.execution.param;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionRequest;
import com.github.devkorol.blocks.business.control.core.execution.param.provider.BusinessControlParamProvider;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusinessControlParamServiceTest {

  private BusinessControlParamService service;
  private BusinessControlParamProvider paramProvider;

  @BeforeEach
  void setUp() {
    paramProvider = mock(BusinessControlParamProvider.class);
    service = new BusinessControlParamService(paramProvider);
  }

  @Test
  void get() {
    BusinessControlExecutionRequest request = BusinessControlExecutionRequest.builder()
        .code("BC_1")
        .flowCode("FC_1")
        .onDate(OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC))
        .model(new Object())
        .build();

    service.get(request);

    verify(paramProvider).get(
        eq(request.getCode()),
        eq(request.getFlowCode()),
        eq(request.getOnDate()));
  }
}