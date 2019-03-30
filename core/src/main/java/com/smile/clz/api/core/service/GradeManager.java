package com.smile.clz.api.core.service;

import com.smile.clz.api.beans.Grade;
import com.smile.clz.api.core.exception.ClassApiException;
import java.util.List;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
public interface GradeManager {

  /**
   * The method to retrieve the grade by grade name
   *
   * @return Grade instance.
   */
  Grade getGradeByGradeName(String gradeName) throws ClassApiException;

  /**
   * The method to retrieve the all grades
   *
   * @return List of Grade instance.
   */
  List<Grade> getAllGrades() throws ClassApiException;

  /**
   * The method to retrieve all grades by zone name
   *
   * @return List of Grade instance.
   */
  List<Grade> getAllGradesByZoneName(String zoneName) throws ClassApiException;
}
