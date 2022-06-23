package com.github.devkorol.blocks.business.control.core;

import com.github.devkorol.blocks.business.control.core.execution.locator.metamodel.ArgMetamodel;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.AliasFor;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface BusinessControl {

  /**
   * unique business control code
   */
  @AliasFor("code")
  String value() default "";

  @AliasFor("value")
  String code() default "";

  /**
   * unique code of flow where business control is executing
   */
  String flowCode() default "";

  /**
   * List with all metamodels classes for this business control. They help's to map input object to individual params.
   */
  Class<? extends ArgMetamodel>[] argMetamodels();

  /**
   * Bean level public method to execute business control. Signature must match metamodel params and types.
   */
  String invocationMethod();
}
