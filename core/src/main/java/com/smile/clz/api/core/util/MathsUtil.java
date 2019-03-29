package com.smile.clz.api.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathsUtil {
  private static final Logger LOGGER = LoggerFactory.getLogger(MathsUtil.class);


  /**
   * divide two numbers
   *
   * @return
   */
  public static double divideTwoNumbers(int a, int b) {
      LOGGER.info("Ready to divide [{}]/[{}]", a, b);
      return a/b;
  }

}
