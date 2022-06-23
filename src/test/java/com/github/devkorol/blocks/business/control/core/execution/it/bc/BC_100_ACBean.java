package com.github.devkorol.blocks.business.control.core.execution.it.bc;

import com.github.devkorol.blocks.business.control.core.BusinessControl;
import com.github.devkorol.blocks.business.control.core.execution.it.bc.BC_100_ACBean.BC_100_ACMetamodel;
import com.github.devkorol.blocks.business.control.core.execution.it.dto.SomeInputModel;
import com.github.devkorol.blocks.business.control.core.execution.it.settings.provider.TestBusinessControlSettingsProvider;
import com.github.devkorol.blocks.business.control.core.execution.locator.metamodel.ArgMetamodel;
import com.github.devkorol.blocks.business.control.core.execution.model.BusinessControlExecutionModel;
import com.github.devkorol.blocks.business.control.core.execution.model.ReportEntity;
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
    public String[] path() {
      return of(
          SomeInputModel.Fields.id,
          SomeInputModel.Fields.name,
          SomeInputModel.Fields.age,
          SomeInputModel.Fields.someArray,
          join(SomeInputModel.Fields.inner, SomeInputModel.SomeInnerClass.Fields.inn)
      );
    }
  }
}

