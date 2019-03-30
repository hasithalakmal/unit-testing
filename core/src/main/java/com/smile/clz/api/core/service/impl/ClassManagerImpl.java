package com.smile.clz.api.core.service.impl;

import static java.text.MessageFormat.format;

import com.smile.clz.api.beans.Class;
import com.smile.clz.api.core.ErrorCode;
import com.smile.clz.api.core.dao.ClassDao;
import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.ClassManager;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
@Service("classManager")
public class ClassManagerImpl implements ClassManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClassManagerImpl.class);

  @Autowired
  private ClassDao classDao;

  /**
   * @inheritDoc
   */
  @Override
  public Class getClassByClassName(String className) throws ClassApiException {
    LOGGER.info("Entered get class by Class Name [{}]", className);

    validateClassName(className);
    LOGGER.info("Class name is validated [{}]", className);

    Class classObj = classDao.getClassByClassName(className);

    if (classObj == null) {
      String error = format("Class does not exist for given class name [{0}]", className);
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.NOT_FOUND, ErrorCode.CLASS_NOT_FOUND, error);
    }

    LOGGER.info("Retrieved class for class name: [{}]", className);
    return classObj;
  }

  /**
   * @inheritDoc
   */
  @Override
  public List<Class> getAllClasses() throws ClassApiException {
    LOGGER.info("Entered get all classes");

    List<Class> listOfClasses = classDao.getAllClasses();

    if (CollectionUtils.isEmpty(listOfClasses)) {
      String error = format("Classes does not exist [{0}]", listOfClasses);
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.NOT_FOUND, ErrorCode.CLASS_NOT_FOUND, error);
    }

    LOGGER.info("Retrieved all classes: [{}]", listOfClasses);
    return listOfClasses;
  }

  /**
   * @inheritDoc
   */
  @Override
  public List<Class> getAllClassesByGradeName(String gradeName) throws ClassApiException {
    LOGGER.info("Entered get all classes by grade name [{}]", gradeName);

    if (StringUtils.isBlank(gradeName)) {
      String error = format("Grade Name is not present: {0}", gradeName);
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_PARAMETERS, error);
    }

    List<Class> listOfClasses = classDao.getAllClassesByGradeName(gradeName);

    if (CollectionUtils.isEmpty(listOfClasses)) {
      String error = format("Classes does not exist [{0}]", listOfClasses);
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.NOT_FOUND, ErrorCode.CLASS_NOT_FOUND, error);
    }

    LOGGER.info("Retrieved all classes [{}] by grade name: [{}]", listOfClasses, gradeName);
    return listOfClasses;
  }

  /**
   * @inheritDoc
   */
  @Override
  public List<Class> getAllClassesByZoneName(String zoneName) throws ClassApiException {
    LOGGER.info("Entered get all classes by zone name [{}]", zoneName);

    if (StringUtils.isBlank(zoneName)) {
      String error = format("Zone Name is not present: {0}", zoneName);
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_PARAMETERS, error);
    }

    List<Class> listOfClasses = classDao.getAllClassesByZoneName(zoneName);

    if (CollectionUtils.isEmpty(listOfClasses)) {
      String error = format("Classes does not exist [{0}]", listOfClasses);
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.NOT_FOUND, ErrorCode.CLASS_NOT_FOUND, error);
    }

    LOGGER.info("Retrieved all classes [{}] by zone name: [{}]", listOfClasses, zoneName);
    return listOfClasses;
  }

  /**
   * The method to validate the class name
   */
  private void validateClassName(String className) throws ClassApiException {
    if (StringUtils.isBlank(className)) {
      String error = format("Class Name is not present: {0}", className);
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_PARAMETERS, error);
    }
  }
}
