package com.smile.clz.api.core.runner;

import com.smile.clz.api.core.util.MathsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppleDivider {

  private static final Logger LOGGER = LoggerFactory.getLogger(AppleDivider.class);

  public static void main(String[] args) {
    int numberOfApples = 10;
    int numberOfStudents = 3;
    LOGGER.info("Let's calculate the number of apples for one student...");
    double applesPerStudent = MathsUtil.divideTwoNumbers(numberOfApples, numberOfStudents);
    LOGGER.info("One student should have [3] apples. Calculated number of apples - [{}]", applesPerStudent);
  }

}
