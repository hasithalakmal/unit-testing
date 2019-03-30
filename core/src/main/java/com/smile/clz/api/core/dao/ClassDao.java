package com.smile.clz.api.core.dao;

import com.smile.clz.api.beans.Class;
import java.util.List;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
public interface ClassDao {

  /**
   * The method to retrieve the class by class name The class name is unique value for identifying classes
   *
   * @return Class instance.
   */
  Class getClassByClassName(String className);

  /**
   * The method to retrieve the all classes
   *
   * @return List of Class instance.
   */
  List<Class> getAllClasses();

  /**
   * The method to retrieve the all classes by grade name
   *
   * @return List of Class instance.
   */
  List<Class> getAllClassesByGradeName(String gradeName);

  /**
   * The method to retrieve the all classes by zone name
   *
   * @return List of Class instance.
   */
  List<Class> getAllClassesByZoneName(String zoneName);
}
