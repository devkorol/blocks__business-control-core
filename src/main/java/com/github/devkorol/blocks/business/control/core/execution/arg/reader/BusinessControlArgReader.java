package com.github.devkorol.blocks.business.control.core.execution.arg.reader;


import com.github.devkorol.blocks.business.control.core.exception.BusinessControlArgReaderException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessControlArgReader {

  private final ExpressionParser parser;
  private final StandardEvaluationContext context;

  /**
   * Get field value by path.
   */
  @SuppressWarnings("unchecked")
  public <T> T get(String path, Object o) {
    T result = null;
    try {
      log.trace("Parse expression {}", path);
      Expression exp = parser.parseExpression(path);
      result = (T) exp.getValue(context, o);
      log.trace("Found result {}", result);
    } catch (SpelParseException | SpelEvaluationException e) {
      throw new BusinessControlArgReaderException(path, e);
    }
    return result;
  }
}
