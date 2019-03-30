package com.smile.clz.api.core.service.impl;

import static org.testng.Assert.fail;

import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.BaseManager;
import com.smile.clz.api.core.service.TestNgExecutionFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class FewTestNgAdvancedFeatures1 extends BaseManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(FewTestNgAdvancedFeatures1.class);

  @Autowired
  private TestNgExecutionFlow testNgExecutionFlow;

  @Test (enabled = false)
  public void testMethod1() {
    try {
      LOGGER.info("Executing test case 1 in FewTestNgAdvancedFeatures1");
      testNgExecutionFlow.myGreeting();
    } catch (ClassApiException pae) {
      fail("Success scenario should not fail", pae);
    }
  }

  @Test (groups = { "functest", "checkintest" })
  public void testMethod2() {
    try {
      LOGGER.info("Executing test case 2 in FewTestNgAdvancedFeatures1");
      testNgExecutionFlow.myGreeting();
    } catch (ClassApiException pae) {
      fail("Success scenario should not fail", pae);
    }
  }

  @Test (groups = { "checkintest" })
  public void testMethod3() {
    try {
      LOGGER.info("Executing test case 3 in FewTestNgAdvancedFeatures1");
      testNgExecutionFlow.myGreeting();
    } catch (ClassApiException pae) {
      fail("Success scenario should not fail", pae);
    }
  }

  @Test (groups = { "functest" })
  public void testMethod4() {
    try {
      LOGGER.info("Executing test case 4 in FewTestNgAdvancedFeatures1");
      testNgExecutionFlow.myGreeting();
    } catch (ClassApiException pae) {
      fail("Success scenario should not fail", pae);
    }
  }

}
