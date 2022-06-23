package com.github.devkorol.blocks.business.control.core.execution.it.bc;

import static java.util.Arrays.asList;

import com.github.devkorol.blocks.business.control.core.BusinessControl;
import com.github.devkorol.blocks.business.control.core.execution.it.bc.BC_100_ACBean.BC_100_ACMetamodel;
import com.github.devkorol.blocks.business.control.core.execution.it.dto.SomeInputModel;
import com.github.devkorol.blocks.business.control.core.execution.it.settings.provider.TestBusinessControlSettingsProvider;
import com.github.devkorol.blocks.business.control.core.execution.locator.metamodel.ArgMetamodel;
import com.github.devkorol.blocks.business.control.core.execution.locator.model.ArgPathModel;
import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionModel;
import com.github.devkorol.blocks.business.control.core.execution.model.ReportEntity;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@BusinessControl(
    code = TestBusinessControlSettingsProvider.BC_100_AC,
    invocationMethod = "execute",
    argMetamodels = BC_100_ACMetamodel.class
)
public class BC_100_ACBean {

  public ReportEntity execute(UUID id, String name, Integer age, String[] someArray, String innerInn,
      BusinessControlExecutionModel model) {

    return ReportEntity.fail()
        .mismatchArgName("age")
        .mismatchArgName("innerInn")
        .messageParam("key1", name)
        .messageParam("key2", innerInn)
        .build();
  }


  @Component
  public static class BC_100_ACMetamodel implements ArgMetamodel {

    @Override
    public Class<?> supportedModel() {
      return SomeInputModel.class;
    }


    @Override
    public List<ArgPathModel> path() {
      return asList(
          ArgPathModel.builder()
              .name(SomeInputModel.Fields.id)
              .path(SomeInputModel.Fields.id)
              .build(),
          ArgPathModel.builder()
              .name(SomeInputModel.Fields.name)
              .path(SomeInputModel.Fields.name)
              .build(),
          ArgPathModel.builder()
              .name(SomeInputModel.Fields.age)
              .path(SomeInputModel.Fields.age)
              .build(),
          ArgPathModel.builder()
              .name(SomeInputModel.Fields.someArray)
              .path(SomeInputModel.Fields.someArray)
              .build(),
          ArgPathModel.builder()
              .name(SomeInputModel.SomeInnerClass.Fields.inn)
              .path(SomeInputModel.Fields.inner + "." + SomeInputModel.SomeInnerClass.Fields.inn)
              .build()
          );
    }
  }
}

