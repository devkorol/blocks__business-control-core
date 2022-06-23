package com.github.devkorol.blocks.business.control.core.execution.settings.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@Accessors(chain = true)
@ConfigurationProperties(prefix = "app.blocks.business.control.settings.default")
public class DefaultBusinessControlSettingsProperties {

  private boolean active;
  private boolean critical;
}
