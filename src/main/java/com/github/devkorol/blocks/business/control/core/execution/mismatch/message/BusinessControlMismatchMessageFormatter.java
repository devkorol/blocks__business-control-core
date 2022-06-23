package com.github.devkorol.blocks.business.control.core.execution.mismatch.message;

import java.util.Map;

public interface BusinessControlMismatchMessageFormatter {

  /**
   * Enrich business control message with params provided through its evaluation.
   */
  String format(String message, Map<String, Object> messageParams);
}
