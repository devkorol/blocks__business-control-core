package com.github.devkorol.blocks.business.control.core.execution.mismatch.config;

public enum BusinessControlMismatchBuilderMode {
  /**
   * all passed arguments to business control is will be marked as invalid
   */
  ALL,

  /**
   * only specified arguments after business control evaluation will be marked as invalid
   */
  SPECIFIED,

  /**
   * all passed arguments will be marked as invalid if specified ones is not passed
   */
  MIXED,

  /**
   * argument marking will be disabled
   */
  NONE
}
