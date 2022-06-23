package com.github.devkorol.blocks.business.control.core.execution.mismatch.message;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DefaultBusinessControlMismatchMessageFormatterTest {

  DefaultBusinessControlMismatchMessageFormatter formatter = new DefaultBusinessControlMismatchMessageFormatter();

  private static Stream<Arguments> messageTestArgs() {
    return Stream.of(
        //simple
        Arguments.of("no templating", "no templating", new HashMap<String, Object>()),
        Arguments.of("no params {{param}}", "no params {{param}}", new HashMap<String, Object>()),
        Arguments.of("name Igor", "name {{firstName}}", new HashMap<String, Object>() {{
          put("firstName", "Igor");
        }}),
        //unknown without exception
        Arguments.of("name ", "name {{invalid}}", new HashMap<String, Object>() {{
          put("firstName", "Igor");
        }}),
        //null as empty
        Arguments.of("name ", "name {{firstName}}", new HashMap<String, Object>() {{
          put("firstName", null);
        }})
    );
  }

  @ParameterizedTest
  @MethodSource("messageTestArgs")
  void format(String expected, String template, HashMap<String, Object> params) {
    String message = formatter.format(template, params);
    assertEquals(expected, message);
  }
}