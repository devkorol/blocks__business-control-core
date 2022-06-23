package com.github.devkorol.blocks.business.control.core.execution.it;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import com.github.devkorol.blocks.business.control.core.SpringApplicationTest;
import com.github.devkorol.blocks.business.control.core.execution.BusinessControlExecutionService;
import com.github.devkorol.blocks.business.control.core.execution.filter.BusinessControlFilterChain;
import com.github.devkorol.blocks.business.control.core.execution.it.config.BusinessControlExecutionServiceITConfig;
import com.github.devkorol.blocks.business.control.core.execution.it.settings.provider.TestBusinessControlSettingsProvider;
import com.github.devkorol.blocks.business.control.core.execution.it.utils.ResultCaptor;
import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionRequest;
import com.github.devkorol.blocks.business.control.core.execution.model.Mismatch;
import com.github.devkorol.blocks.business.control.core.execution.settings.BusinessControlSettingsService;
import java.util.Optional;
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
class BC_100_AC_P_IT {

  @Autowired
  private BusinessControlExecutionService executionService;

  @SpyBean
  private BusinessControlSettingsService settingsService;
  @SpyBean
  private BusinessControlFilterChain filterChain;

  @Test
  void execute_NonActiveByFilterCritical() {
    ResultCaptor<Boolean> resultCaptor = new ResultCaptor<>();
    doAnswer(resultCaptor).when(filterChain).isActiveForRequest(any(), any());

    BusinessControlExecutionRequest request = BusinessControlExecutionRequest.builder()
        .code(TestBusinessControlSettingsProvider.BC_100_AC_P)
        .model(new Object())
        .build();

    Optional<Mismatch> mismatch = executionService.execute(request);

    assertFalse(mismatch.isPresent());
    assertFalse(resultCaptor.getResult(), "inactive due to settings");
    verify(settingsService).get(eq(request));
    verify(filterChain).isActiveForRequest(eq(request), any());
  }
}