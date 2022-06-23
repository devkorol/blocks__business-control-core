package com.github.devkorol.blocks.business.control.core.execution.invocation;

import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodException;
import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodIllegalArgumentException;
import com.github.devkorol.blocks.business.control.core.exception.invocation.BusinessControlInvokeMethodInvocationException;
import com.github.devkorol.blocks.business.control.core.execution.arg.model.ArgModel;
import com.github.devkorol.blocks.business.control.core.execution.locator.model.BusinessControlLocatorModel;
import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionModel;
import com.github.devkorol.blocks.business.control.core.execution.model.ReportEntity;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BusinessControlInvokeService {

  /**
   * Call business control bean method with collected arguments to evaluate request with it logic.
   */
  public <T> ReportEntity invoke(BusinessControlLocatorModel beanModel, BusinessControlExecutionModel<T> invokeModel,
      List<ArgModel> args) {
    ReportEntity result = null;
    Object[] methodArguments = mapArgs(invokeModel, args);

    try {
      result = (ReportEntity) beanModel.getInvocationMethod().invoke(
          beanModel.getBean(),
          methodArguments
      );
    } catch (IllegalAccessException e) {
      throw new BusinessControlInvokeMethodException(e);
    } catch (IllegalArgumentException e) {
      throw new BusinessControlInvokeMethodIllegalArgumentException(
          beanModel.getInvocationMethod().getName(), beanModel.getBean().getClass(), methodArguments, e);
    } catch (InvocationTargetException e) {
      throw new BusinessControlInvokeMethodInvocationException(
          beanModel.getInvocationMethod().getName(), beanModel.getBean().getClass(), methodArguments, e.getCause());
    }

    return result;
  }

  private <T> Object[] mapArgs(BusinessControlExecutionModel<T> invokeModel, List<ArgModel> args) {
    Object[] arguments = new Object[args.size() + 1];
    for (int i = 0; i < args.size(); i++) {
      arguments[i] = args.get(i).getValue();
    }

    //add model as last method argument
    arguments[args.size()] = invokeModel;
    return arguments;
  }
}
