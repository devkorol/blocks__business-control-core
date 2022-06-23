package com.github.devkorol.blocks.business.control.core.execution.param.model;

import static java.util.Objects.isNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessControlParamMap extends HashMap<String, BusinessControlParamModel<?>> {

  /**
   * Helper method to retrieve ready object with casting to necessary type.
   *
   * @param key unique argument name for business control
   * @param <T> type of argument
   */
  @SuppressWarnings("unchecked")
  public <T> BusinessControlParamModel<T> getTyped(String key) {
    return (BusinessControlParamModel<T>) get(key);
  }

  /**
   * Helper method to retrieve argument value with casting to necessary type.
   *
   * @param key unique argument name for business control
   * @param <T> type of argument
   */
  public <T> T getTypedValue(String key) {
    BusinessControlParamModel<T> model = getTyped(key);
    return isNull(model)
        ? null
        : model.getValue();
  }

  public BusinessControlParamMap(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor);
  }

  public BusinessControlParamMap(int initialCapacity) {
    super(initialCapacity);
  }

  public BusinessControlParamMap() {
    super();
  }

  public BusinessControlParamMap(Map<? extends String, ? extends BusinessControlParamModel<?>> m) {
    super(m);
  }

  public BusinessControlParamModel<?> put(BusinessControlParamModel<?> param) {
    return super.put(param.getName(), param);
  }

  public void putAll(List<BusinessControlParamModel<?>> params) {
    params.forEach(this::put);
  }

}
