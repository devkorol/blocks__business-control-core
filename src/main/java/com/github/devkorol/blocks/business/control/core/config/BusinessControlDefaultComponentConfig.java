package com.github.devkorol.blocks.business.control.core.config;

import com.github.devkorol.blocks.business.control.core.execution.mismatch.message.BusinessControlMismatchMessageFormatter;
import com.github.devkorol.blocks.business.control.core.execution.mismatch.message.DefaultBusinessControlMismatchMessageFormatter;
import com.github.devkorol.blocks.business.control.core.execution.param.provider.BusinessControlParamProvider;
import com.github.devkorol.blocks.business.control.core.execution.param.provider.DefaultBusinessControlParamProvider;
import com.github.devkorol.blocks.business.control.core.execution.settings.config.DefaultBusinessControlSettingsProperties;
import com.github.devkorol.blocks.business.control.core.execution.settings.provider.BusinessControlSettingsProvider;
import com.github.devkorol.blocks.business.control.core.execution.settings.provider.DefaultBusinessControlSettingsProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessControlDefaultComponentConfig {

  @Bean
  @ConditionalOnMissingBean(BusinessControlParamProvider.class)
  public BusinessControlParamProvider getDefaultBusinessControlParamProvider() {
    return new DefaultBusinessControlParamProvider();
  }

  @Bean
  @ConditionalOnMissingBean(BusinessControlSettingsProvider.class)
  public BusinessControlSettingsProvider getDefaultBusinessControlSettingsProvider(
      DefaultBusinessControlSettingsProperties properties
  ) {
    return new DefaultBusinessControlSettingsProvider(properties);
  }

  @Bean
  @ConditionalOnMissingBean(BusinessControlMismatchMessageFormatter.class)
  public BusinessControlMismatchMessageFormatter getDefaultBusinessControlMismatchMessageFormatter() {
    return new DefaultBusinessControlMismatchMessageFormatter();
  }
}
