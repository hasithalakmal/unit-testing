package com.smile.clz.api.core.service.impl;

import static org.testng.Assert.fail;

import com.smile.clz.api.core.ErrorCode;
import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.BaseManager;
import com.smile.clz.api.core.service.TddDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TddDemoTest extends BaseManager {

  @Autowired
  private TddDemo tddDemo;

  @BeforeClass
  public void setup() {
  }

  @Test
  public void testMyDivideMethodIntegerResult() {
    try {
      Double result = tddDemo.myDivide(10, 2);
      Assert.assertNotNull(result);
      Assert.assertEquals(result,5.00);
    } catch (ClassApiException pae) {
      fail("Success scenario should not fail", pae);
    }
  }

  @Test
  public void testMyDivideMethodFloatingPointResult() {
    try {
      Double result = tddDemo.myDivide(10, 4);
      Assert.assertNotNull(result);
      Assert.assertEquals(result,2.5);
    } catch (ClassApiException pae) {
      fail("Success scenario should not fail", pae);
    }
  }

  @Test
  public void testMyDivideMethodZeroResult() {
    try {
      Double result = tddDemo.myDivide(0, 4);
      Assert.assertNotNull(result);
      Assert.assertEquals(result,0.0);
    } catch (ClassApiException pae) {
      fail("Success scenario should not fail", pae);
    }
  }

  @Test
  public void test5() {
    Double result = tddDemo.myDivide(10.5, 2.4);
    Assert.assertNotNull(result);
    Assert.assertEquals(result, 4.375);
  }

  //TODO - fill this
  @Test
  public void test1() {
    try {
      Double result = tddDemo.myDivide(0, 0);
      fail("Error scenario should fail");
    } catch (Exception ex) {
      Assert.assertTrue(ex instanceof ClassApiException, "Should be a ClassApiException ");
      ClassApiException e = (ClassApiException) ex;
      Assert.assertEquals(e.getErrorCode(), ErrorCode.INVALID_PARAMETERS );
      Assert.assertEquals(e.getHttpStatusCode(), HttpStatus.BAD_REQUEST);
    }
  }

}
