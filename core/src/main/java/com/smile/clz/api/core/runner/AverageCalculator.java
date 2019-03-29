package com.smile.clz.api.core.runner;

import com.smile.clz.api.core.util.MathsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AverageCalculator {

  private static final Logger LOGGER = LoggerFactory.getLogger(AverageCalculator.class);

  public static void main(String[] args) {
    int totalMarks = 350;
    int numberOfSubjects = 4;
    LOGGER.info("Let's calculate the average...");
    double applesPerStudent = MathsUtil.divideTwoNumbers(totalMarks, numberOfSubjects);
    LOGGER.info("Average should be [87.5]. The calculated value is [{}]", applesPerStudent);
  }
}
