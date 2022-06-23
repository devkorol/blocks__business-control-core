package com.github.devkorol.blocks.business.control.core.execution.it.utils;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ResultCaptor<T> implements Answer {

  private T result = null;

  public T getResult() {
    return result;
  }

  @Override
  public T answer(InvocationOnMock invocationOnMock) throws Throwable {
    result = (T) invocationOnMock.callRealMethod();
    return result;
  }
}

