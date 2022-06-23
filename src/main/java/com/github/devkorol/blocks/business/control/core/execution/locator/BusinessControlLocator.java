package com.github.devkorol.blocks.business.control.core.execution.locator;

import static com.github.devkorol.blocks.business.control.core.config.BusinessControlCacheConfig.BUSINESS_CONTROL_BEAN_LOOKUP_CACHE;
import static com.github.devkorol.blocks.business.control.core.config.BusinessControlCacheConfig.BUSINESS_CONTROL_CACHE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.github.devkorol.blocks.business.control.core.exception.BusinessControlBeanNotFoundException;
import com.github.devkorol.blocks.business.control.core.execution.locator.model.BusinessControlLocatorModel;
import com.github.devkorol.blocks.business.control.core.execution.locator.starter.BusinessControlStarter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessControlLocator {

  private final BusinessControlStarter businessControlStarter;

  /**
   * Find business control bean by it code and flow code. Flow code is optional so will return bean with null flow code
   * if it can't find specific bean.
   *
   * @param code     unique business control code
   * @param flowCode unique code of flow where business control is executing
   * @return composite object regarding spring bean
   */
  @Cacheable(key = "#code+'-'+#flowCode", cacheNames = BUSINESS_CONTROL_BEAN_LOOKUP_CACHE, cacheManager = BUSINESS_CONTROL_CACHE)
  public BusinessControlLocatorModel locate(String code, @Nullable String flowCode) {
    log.debug("Locating bean by code <{}> and flow <{}>", code, flowCode);
    BusinessControlLocatorModel businessControlBean = null;
    BusinessControlLocatorModel businessControlBeanNullFlowCode = null;

    for (BusinessControlLocatorModel controlBean : businessControlStarter.getBusinessControlBeans()) {
      if (!code.equalsIgnoreCase(controlBean.getCode())) {
        continue;
      }

      if (isNull(flowCode) && isNull(controlBean.getFlowCode())) {
        log.debug("Found by code and flowCode <null> bean {}", controlBean.getBean().getClass().getName());
        businessControlBean = controlBean;
        break;
      }

      if (nonNull(flowCode)) {
        if (flowCode.equalsIgnoreCase(controlBean.getFlowCode())) {
          log.debug("Found by code and flowCode bean {}", controlBean.getBean().getClass().getName());
          businessControlBean = controlBean;
          break;
        } else if (isNull(controlBean.getFlowCode())) {
          //save for later
          businessControlBeanNullFlowCode = controlBean;
        }
      }
    }

    //bean with null flowCode can be applied to request
    if (isNull(businessControlBean)
        && nonNull(businessControlBeanNullFlowCode)) {
      log.debug("Not found bean with matching flowCode so provided with flowCode=null bean  {}",
          businessControlBeanNullFlowCode.getBean().getClass().getName());
      businessControlBean = businessControlBeanNullFlowCode;
    }

    if (isNull(businessControlBean)) {
      throw new BusinessControlBeanNotFoundException(code, flowCode);
    }

    return businessControlBean;
  }
}
