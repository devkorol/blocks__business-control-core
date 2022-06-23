package com.github.devkorol.blocks.business.control.core.execution.param.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BusinessControlParamMapTest {

  private static BusinessControlParamMap map;

  @BeforeAll
  static void setup() {
    map = new BusinessControlParamMap(10);

    map.put("string", new BusinessControlParamModel<>("string", "hello world"));
    map.put("long", new BusinessControlParamModel<>("long", Long.MAX_VALUE));
    map.put("date", new BusinessControlParamModel<>("date", LocalDate.now()));
    map.put("datetime", new BusinessControlParamModel<>("datetime", OffsetDateTime.now()));
    map.put("bigdecimal", new BusinessControlParamModel<>("bigdecimal", BigDecimal.valueOf(1)));
    map.put("array", new BusinessControlParamModel<>("array", new int[]{1, 2, 3, 4, 5}));
    map.put("list", new BusinessControlParamModel<>("array", Arrays.asList("Sam", "Tim", "Pom")));
  }


  @Test
  void map_String() {
    BusinessControlParamModel<String> string = map.getTyped("string");
    assertNotNull(string.getValue());
  }

  @Test
  void map_BigDecimal() {
    BigDecimal bigdecimal = map.getTypedValue("bigdecimal");
    assertNotNull(bigdecimal);
  }

  @Test
  void map_List() {
    List<String> list = map.getTypedValue("list");
    assertNotNull(list);
  }

  @Test
  void map_null() {
    Integer value = map.getTypedValue("invalid");
    assertNull(value);
  }
}