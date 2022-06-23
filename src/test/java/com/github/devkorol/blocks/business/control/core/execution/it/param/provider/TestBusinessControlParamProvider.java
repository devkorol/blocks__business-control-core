package com.github.devkorol.blocks.business.control.core.execution.it.param.provider;

import static java.util.Arrays.asList;

import com.github.devkorol.blocks.business.control.core.execution.param.model.BusinessControlParamMap;
import com.github.devkorol.blocks.business.control.core.execution.param.model.BusinessControlParamModel;
import com.github.devkorol.blocks.business.control.core.execution.param.provider.BusinessControlParamProvider;
import java.time.OffsetDateTime;
import org.springframework.stereotype.Component;

@Component
public class TestBusinessControlParamProvider implements BusinessControlParamProvider {

  public static final String PARAM_STRING = "string";
  public static final String PARAM_INT = "int";
  public static final String PARAM_ARRAY = "array";
  public static final String PARAM_LIST = "list";

  private final BusinessControlParamMap paramMap = new BusinessControlParamMap() {{
    put(new BusinessControlParamModel(PARAM_STRING, "lorem ipsum dolor sit amet"));
    put(new BusinessControlParamModel(PARAM_INT, 256));
    put(new BusinessControlParamModel(PARAM_ARRAY, new int[]{1, 2, 3, 4, 5}));
    put(new BusinessControlParamModel(PARAM_LIST, asList("hello", "world")));
  }};

  @Override
  public BusinessControlParamMap get(String code, String flowCode, OffsetDateTime onDate) {
    return paramMap;
  }
}
