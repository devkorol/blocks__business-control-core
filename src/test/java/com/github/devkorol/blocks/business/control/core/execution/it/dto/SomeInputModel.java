package com.github.devkorol.blocks.business.control.core.execution.it.dto;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
@Accessors(chain = true)
public class SomeInputModel {

  private UUID id;
  private String name;
  private Integer age;
  private String[] someArray;
  private Collection<String> someCollection;
  private SomeInnerClass inner;
  private List<SomeInnerClass> innerCollection;


  @Data
  @Builder
  @FieldNameConstants
  @Accessors(chain = true)
  public static class SomeInnerClass {

    private String product;
    private String inn;
  }
}
