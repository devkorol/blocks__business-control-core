package com.github.devkorol.blocks.business.control.core.execution.locator.method;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class BusinessControlParseInvocationMethodTest {

  private final BusinessControlParseInvocationMethod parse = new BusinessControlParseInvocationMethod();

  @Test
  void parseArgumentNames_EmptyArgs() {
    Method method = getMethod("execute");

    List<String> names = parse.parseArgumentNames(method);

    assertTrue(names.isEmpty());
  }

  @Test
  void parseArgumentNames_SingleArgs() {
    Method method = getMethod("single");

    List<String> names = parse.parseArgumentNames(method);

    assertEquals(asList("s1"), names);
  }

  @Test
  void parseArgumentNames_MultiArgs() {
    Method method = getMethod("multi");

    List<String> names = parse.parseArgumentNames(method);

    assertEquals(asList("s1", "integer", "array"), names);
  }

  private Method getMethod(String name) {
    return Arrays.stream(Stub.class.getDeclaredMethods())
        .filter(m -> m.getName().equals(name))
        .findFirst()
        .get();
  }

  public static class Stub {

    void execute() {
    }

    void single(String s1) {
    }

    void multi(String s1, Integer integer, String[] array) {
    }
  }
}