package com.smile.clz.api.core.service.impl;

import static org.testng.Assert.fail;

import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.BaseManager;
import com.smile.clz.api.core.service.TestNgExecutionFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNgExecutionFlowDemoTest extends BaseManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestNgExecutionFlowDemoTest.class);

  @Autowired
  private TestNgExecutionFlow testNgExecutionFlow;

  @Test
  public void testMethod1() {
    try {
      LOGGER.info("Executing test case 1");
      testNgExecutionFlow.myGreeting();
    } catch (ClassApiException pae) {
      fail("Success scenario should not fail", pae);
    }
  }

  @Test
  public void testMethod2() {
    try {
      LOGGER.info("Executing test case 2");
      testNgExecutionFlow.myGreeting();
    } catch (ClassApiException pae) {
      fail("Success scenario should not fail", pae);
    }
  }

  @BeforeMethod
  public void beforeMethod() {
    LOGGER.info("Executing BeforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
    LOGGER.info("Executing AfterMethod");
  }

  @BeforeClass
  public void beforeClass() {
    LOGGER.info("Executing BeforeClass");
  }

  @AfterClass
  public void afterClass() {
    LOGGER.info("Executing AfterClass");
  }

  @BeforeTest
  public void beforeTest() {
    LOGGER.info("Executing BeforeTest");
  }

  @AfterTest
  public void afterTest() {
    LOGGER.info("Executing AfterTest");
  }

  @BeforeSuite
  public void beforeSuite() {
    LOGGER.info("Executing BeforeSuite");
  }

  @AfterSuite
  public void afterSuite() {
    LOGGER.info("Executing AfterSuite");
  }

}
