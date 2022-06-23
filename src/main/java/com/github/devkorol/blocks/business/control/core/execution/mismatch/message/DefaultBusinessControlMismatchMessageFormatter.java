package com.github.devkorol.blocks.business.control.core.execution.mismatch.message;

import com.github.devkorol.blocks.business.control.core.exception.BusinessControlBeanMessageProcessingException;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DefaultBusinessControlMismatchMessageFormatter implements BusinessControlMismatchMessageFormatter {

  private static final String HANDLEBAR_MARKS = "{{";

  private final Handlebars handlebars = new Handlebars();

  /**
   * Here used handlebars engine for template processing.
   */
  public String format(String message, Map<String, Object> messageParams) {
    if (!message.contains(HANDLEBAR_MARKS)
        || messageParams.isEmpty()) {
      log.debug("Message doesn't contains templating or provided params is empty. Return as-is");
      return message;
    }

    return processTemplate(message, messageParams);
  }

  private String processTemplate(String message, Map<String, Object> messageParams) {
    try {
      Template template = handlebars.compileInline(message);
      return template.apply(messageParams);
    } catch (Exception e) {
      throw new BusinessControlBeanMessageProcessingException(message, messageParams, e);
    }
  }
}
