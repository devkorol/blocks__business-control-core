package com.github.devkorol.blocks.business.control.core.execution.settings.provider;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.devkorol.blocks.business.control.core.execution.settings.config.DefaultBusinessControlSettingsProperties;
import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultBusinessControlSettingsProviderTest {

  private BusinessControlSettingsProvider provider;
  private DefaultBusinessControlSettingsProperties properties;

  @BeforeEach
  void setUp() {
    properties = new DefaultBusinessControlSettingsProperties()
        .setActive(true)
        .setCritical(true);
    provider = new DefaultBusinessControlSettingsProvider(properties);
  }

  @Test
  void get() {
    BusinessControlSettingsModel settings = provider.get("1", null, null);

    assertTrue(settings.isActive(), "Should return value from properties");
    assertTrue(settings.isCritical(), "Should return value from properties");
  }
}