package com.github.devkorol.blocks.business.control.core.execution.settings.provider;

import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;
import java.time.OffsetDateTime;
import org.springframework.lang.Nullable;

public interface BusinessControlSettingsProvider {

  /**
   * Found and retrieve settings for specific business control
   *
   * @param code     - unique business control code
   * @param flowCode - unique code of flow where business control is executing
   * @param onDate   - date-time in what period will be looked up settings
   * @return settings for business control
   */
  BusinessControlSettingsModel get(String code, @Nullable String flowCode, @Nullable OffsetDateTime onDate);
}
