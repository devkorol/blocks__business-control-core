package com.github.devkorol.blocks.business.control.core.execution.it.settings.provider;


import com.github.devkorol.blocks.business.control.core.execution.settings.model.BusinessControlSettingsModel;
import com.github.devkorol.blocks.business.control.core.execution.settings.provider.BusinessControlSettingsProvider;
import java.time.OffsetDateTime;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class TestBusinessControlSettingsProvider implements BusinessControlSettingsProvider {

  public static final String BC_100_AC = "BC_100_AC";
  public static final String BC_100_NC = "BC_100_NC";
  public static final String BC_100_AC_P = "BC_100_AC_P";
  public static final String BC_100_AC_P_INACTIVE_BY_FILTER = "BC_100_AC_P_DISABLED_BY_PROPERTY_IN_FILTER";

  private final HashMap<String, BusinessControlSettingsModel> settings = new HashMap<String, BusinessControlSettingsModel>() {{
    put(BC_100_NC, BusinessControlSettingsModel.builder()
        .active(false)
        .critical(true)
        .build());
    put(BC_100_AC_P, BusinessControlSettingsModel.builder()
        .active(true)
        .critical(true)
        .additionalProperties(new HashMap<String, Object>() {{
          put(BC_100_AC_P_INACTIVE_BY_FILTER, "some value");
        }})
        .build());
    put(BC_100_AC, BusinessControlSettingsModel.builder()
        .active(true)
        .critical(true)
        .build());
  }};

  @Override
  public BusinessControlSettingsModel get(String code, String flowCode, OffsetDateTime onDate) {
    return settings.getOrDefault(code, null);
  }
}
