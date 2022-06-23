package com.github.devkorol.blocks.business.control.core.execution.mismatch.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@Accessors(chain = true)
@ConfigurationProperties(prefix = "app.blocks.business.control.mismatch.builder")
public class BusinessControlMismatchBuilderProperties {

  private BusinessControlMismatchBuilderMode mode;
}
