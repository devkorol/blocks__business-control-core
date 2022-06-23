package com.github.devkorol.blocks.business.control.core.execution.param.provider;

import com.github.devkorol.blocks.business.control.core.execution.param.model.BusinessControlParamMap;
import java.time.OffsetDateTime;
import org.springframework.lang.Nullable;

public interface BusinessControlParamProvider {

  /**
   * Found and retrieve param for specific business control
   *
   * @param code     - unique business control code
   * @param flowCode - unique code of flow where business control is executing
   * @param onDate   - date-time in what period will be looked up settings
   * @return param for business control
   */
  BusinessControlParamMap get(String code, @Nullable String flowCode, @Nullable OffsetDateTime onDate);
}
