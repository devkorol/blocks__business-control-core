package com.github.devkorol.blocks.business.control.core.execution.arg.reader;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.devkorol.blocks.business.control.core.config.BusinessControlArgReaderConfig;
import com.github.devkorol.blocks.business.control.core.exception.BusinessControlArgReaderException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BusinessControlArgReaderTest {

  private static BusinessControlArgReader service;

  @BeforeAll
  static void setUp() throws NoSuchMethodException {
    BusinessControlArgReaderConfig config = new BusinessControlArgReaderConfig();
    service = new BusinessControlArgReader(
        config.getExpressionParser(),
        config.getStandardEvaluationContext()
    );
  }

  @Test
  void testGet() {
    String my_name = "my_name";
    String name = service.get("name", new SomeClass().setName(my_name));
    assertEquals(my_name, name);
  }

  @Test
  void testGetInner() {
    String my_inn = "my_inn";
    Object inn = service.get("inner.inn", new SomeClass().setInner(new SomeInnerClass().setInn(my_inn)));
    assertEquals(my_inn, inn);
  }

  @Test
  void testTypes() {
    SomeClass dto = new SomeClass()
        .setSomeArray(new String[]{"1", "2"})
        .setSomeCollection(asList("1", "2"));
    String nullValue = service.get("name", dto);
    String[] someArray = service.get("someArray", dto);
    List<String> someCollection = service.get("someCollection", dto);

    assertNull(nullValue);
    assertArrayEquals(new String[]{"1", "2"}, someArray);
    assertTrue(someCollection.contains("1"));
  }

  @Test
  void testUnknownField() {
    assertThrows(BusinessControlArgReaderException.class,
        () -> service.get("unknown_field", new SomeClass()));
  }

  @Test
  void testInvalidPath() {
    assertThrows(BusinessControlArgReaderException.class,
        () -> service.get("invalid>!?[]", new SomeClass()));
  }

  @Test
  void testGetInnerListWithFilter() {
    String my_inn = "my_inn";
    ArrayList<SomeInnerClass> filtered = service.get(
        "innerCollection.?[#this.inn == 'my_inn2']",
        new SomeClass().setInnerCollection(asList(
            new SomeInnerClass().setInn(my_inn).setProduct("1"),
            new SomeInnerClass().setInn(my_inn + "2").setProduct("2"),
            new SomeInnerClass().setInn(my_inn + "3").setProduct("3")
        )));
    assertEquals(1, filtered.size(), "Should return only matched node");
    assertEquals("my_inn2", filtered.get(0).getInn());
    assertEquals("2", filtered.get(0).getProduct());
  }

  @Test
  void testGetInnerListGetArg() {
    String my_inn = "my_inn";
    ArrayList<String> inns = service.get(
        "innerCollection.![#this.inn]",
        new SomeClass().setInnerCollection(asList(
            new SomeInnerClass().setInn(my_inn),
            new SomeInnerClass().setInn(my_inn + "1")
        )));
    assertEquals(asList("my_inn", "my_inn1"), inns);
  }

  @Test
  void testGetInnerListFilterAndGetArg() {
    String my_inn = "my_inn";
    ArrayList<String> inns = service.get(
        "innerCollection.?[#this.inn == 'my_inn2'].![#this.inn]",
        new SomeClass().setInnerCollection(asList(
            new SomeInnerClass().setInn(my_inn),
            new SomeInnerClass().setInn(my_inn + "2")
        )));
    System.out.println(inns);
    assertEquals(1, inns.size(), "Should return only matched node");
    assertEquals("my_inn2", inns.get(0));
  }

  @Data
  @Accessors(chain = true)
  private static class SomeClass {

    private String name;
    private String product;
    private String[] someArray;
    private Collection<String> someCollection;
    private SomeInnerClass inner;
    private List<SomeInnerClass> innerCollection;
  }

  @Data
  @Accessors(chain = true)
  private static class SomeInnerClass {

    private String product;
    private String inn;
  }
}