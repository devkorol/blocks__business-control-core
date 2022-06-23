package com.github.devkorol.blocks.business.control.core.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Configuration
public class BusinessControlArgReaderConfig {

  @Bean
  public ExpressionParser getExpressionParser() {
    return new SpelExpressionParser();
  }

  @Bean
  public StandardEvaluationContext getStandardEvaluationContext() throws NoSuchMethodException {
    StandardEvaluationContext ec = new StandardEvaluationContext();
    return ec;
  }
}
