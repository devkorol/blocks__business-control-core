package com.github.devkorol.blocks.business.control.core.execution.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import com.github.devkorol.blocks.business.control.core.SpringApplicationTest;
import com.github.devkorol.blocks.business.control.core.execution.BusinessControlExecutionService;
import com.github.devkorol.blocks.business.control.core.execution.arg.BusinessControlArgService;
import com.github.devkorol.blocks.business.control.core.execution.arg.model.ArgModel;
import com.github.devkorol.blocks.business.control.core.execution.filter.BusinessControlFilterChain;
import com.github.devkorol.blocks.business.control.core.execution.invocation.BusinessControlInvokeService;
import com.github.devkorol.blocks.business.control.core.execution.it.config.BusinessControlExecutionServiceITConfig;
import com.github.devkorol.blocks.business.control.core.execution.it.dto.SomeInputModel;
import com.github.devkorol.blocks.business.control.core.execution.it.dto.SomeInputModel.SomeInnerClass;
import com.github.devkorol.blocks.business.control.core.execution.it.settings.provider.TestBusinessControlSettingsProvider;
import com.github.devkorol.blocks.business.control.core.execution.it.utils.ResultCaptor;
import com.github.devkorol.blocks.business.control.core.execution.locator.BusinessControlLocator;
import com.github.devkorol.blocks.business.control.core.execution.locator.metamodel.ArgMetamodel;
import com.github.devkorol.blocks.business.control.core.execution.metamodel.BusinessControlArgMetamodelService;
import com.github.devkorol.blocks.business.control.core.execution.mismatch.BusinessControlMismatchBuilder;
import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionRequest;
import com.github.devkorol.blocks.business.control.core.execution.model.Mismatch;
import com.github.devkorol.blocks.business.control.core.execution.param.BusinessControlParamService;
import com.github.devkorol.blocks.business.control.core.execution.settings.BusinessControlSettingsService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringApplicationTest.class)
@ContextConfiguration(classes = BusinessControlExecutionServiceITConfig.class)
class BC_100_AC_IT {

  @Autowired
  private BusinessControlExecutionService executionService;

  @SpyBean
  private BusinessControlSettingsService settingsService;
  @SpyBean
  private BusinessControlFilterChain filterChain;
  @SpyBean
  private BusinessControlLocator businessControlLocator;
  @SpyBean
  private BusinessControlArgMetamodelService argMetamodelService;
  @SpyBean
  private BusinessControlParamService paramService;
  @SpyBean
  private BusinessControlArgService argService;
  @SpyBean
  private BusinessControlInvokeService invokeService;
  @SpyBean
  private BusinessControlMismatchBuilder mismatchBuilder;

  @Test
  void execute_ActiveCritical() {
    ResultCaptor<Boolean> filterResultCaptor = new ResultCaptor<>();
    doAnswer(filterResultCaptor).when(filterChain).isActiveForRequest(any(), any());

    BusinessControlExecutionRequest request = BusinessControlExecutionRequest.builder()
        .code(TestBusinessControlSettingsProvider.BC_100_AC)
        .model(SomeInputModel.builder()
            .id(UUID.randomUUID())
            .name("my name")
            .age(20)
            .inner(SomeInnerClass.builder()
                .inn("my inn")
                .build())
            .build())
        .build();

    Optional<Mismatch> mismatch = executionService.execute(request);

    assertTrue(filterResultCaptor.getResult());
    assertTrue(mismatch.isPresent());
    assertTrue(mismatch.get().isCritical());
    assertEquals(request.getCode(), mismatch.get().getCode());
    assertEquals(2, mismatch.get().getElements().size());

    verify(settingsService).get(eq(request));
    verify(filterChain).isActiveForRequest(eq(request), any());
    verify(businessControlLocator).locate(eq(request.getCode()), eq(request.getFlowCode()));
    verify(paramService).get(eq(request));
    verify(argMetamodelService).findMetamodel(eq(request.getModel()), any());
    verify(argService).read(eq(request.getModel()), any());
    verify(invokeService).invoke(any(), any(), any());
    verify(mismatchBuilder).build(any(), any(), any(), any());
  }
}