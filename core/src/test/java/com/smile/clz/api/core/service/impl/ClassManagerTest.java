package com.smile.clz.api.core.service.impl;

import static org.testng.Assert.fail;

import com.smile.clz.api.beans.Class;
import com.smile.clz.api.core.ErrorCode;
import com.smile.clz.api.core.dao.ClassDao;
import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.BaseManager;
import com.smile.clz.api.core.service.ClassManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for user manager related functionality
 *
 * @author hasithagamage
 * @date 10/28/17
 */
public class ClassManagerTest extends BaseManager {

  @Autowired
  private ClassManager classManager;

  @Autowired
  private ClassDao classDao;

  @BeforeClass
  public void setup() {
  }

  @Test
  public void testClassByClassNameErrorScenarios() {

    //Error on empty class name
    try {
      classManager.getClassByClassName(null);
      fail("Error scenario should not pass");
    } catch (ClassApiException pae) {
      Assert.assertNotNull(pae);
      Assert.assertTrue(pae.getHttpStatusCode() == HttpStatus.BAD_REQUEST);
      Assert.assertTrue(pae.getErrorCode() != null
          && pae.getErrorCode().equals(ErrorCode.INVALID_PARAMETERS));
    }

    try {
      classManager.getClassByClassName("");
      fail("Error scenario should not pass");
    } catch (ClassApiException pae) {
      Assert.assertNotNull(pae);
      Assert.assertTrue(pae.getHttpStatusCode() == HttpStatus.BAD_REQUEST);
      Assert.assertTrue(pae.getErrorCode() != null
          && pae.getErrorCode().equals(ErrorCode.INVALID_PARAMETERS));
    }

    try {
      classManager.getClassByClassName("    ");
      fail("Error scenario should not pass");
    } catch (ClassApiException pae) {
      Assert.assertNotNull(pae);
      Assert.assertTrue(pae.getHttpStatusCode() == HttpStatus.BAD_REQUEST);
      Assert.assertTrue(pae.getErrorCode() != null
          && pae.getErrorCode().equals(ErrorCode.INVALID_PARAMETERS));
    }

    //Error on invalid class name
    try {
      classManager.getClassByClassName("xxxx");
      fail("Error scenario should not pass");
    } catch (ClassApiException pae) {
      Assert.assertNotNull(pae);
      Assert.assertTrue(pae.getHttpStatusCode() == HttpStatus.NOT_FOUND);
      Assert.assertTrue(pae.getErrorCode() != null
          && pae.getErrorCode().equals(ErrorCode.CLASS_NOT_FOUND));
    }
  }

  @Test
  public void testClassByClassNameSuccessScenario() {
    try {
      Class classObj = classManager.getClassByClassName("2A");
      Assert.assertNotNull(classObj);
      Assert.assertEquals(classObj.getClassId(), 1);
      Assert.assertEquals(classObj.getClassName(), "2A");
      Assert.assertEquals(classObj.getClassPart(), "A");
      Assert.assertEquals(classObj.getClassState(), "ACTIVE");
      Assert.assertEquals(classObj.getGrade().getGradeName(), "GRADE 2");
      Assert.assertEquals(classObj.getGrade().getZone().getZoneName(), "PRIMARY");
    } catch (ClassApiException pae) {
      fail("Success scenario should not fail", pae);
    }
  }

}
