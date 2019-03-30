package com.smile.clz.api.core.service;

import com.smile.clz.api.beans.Class;
import com.smile.clz.api.core.exception.ClassApiException;
import java.util.List;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
public interface ClassManager {

  /**
   * The method to retrieve the class by class name
   *
   * @return Class instance.
   */
  Class getClassByClassName(String className) throws ClassApiException;

  /**
   * The method to retrieve the all classes
   *
   * @return List of Class instance.
   */
  List<Class> getAllClasses() throws ClassApiException;

  /**
   * The method to retrieve the all classes by grade name
   *
   * @return list of Class instance.
   */
  List<Class> getAllClassesByGradeName(String gradeName) throws ClassApiException;

  /**
   * The method to retrieve the all classes by zone name
   *
   * @return list of Class instance.
   */
  List<Class> getAllClassesByZoneName(String zoneName) throws ClassApiException;
}
