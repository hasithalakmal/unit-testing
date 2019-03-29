package com.smile.clz.api.core.dao;

import com.smile.clz.api.beans.Grade;

import java.util.List;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
public interface GradeDao {

    /**
     * The method to retrieve the the grade by grade name
     * The grade name is unique value for identifying grades
     *
     * @param gradeName
     * @return Grade instance.
     */
    Grade getGradeByGradeName(String gradeName);

    /**
     * The method to retrieve the all grades
     *
     * @return List of Grade instance.
     */
    List<Grade> getAllGrades();

    /**
     * The method to retrieve the all grades by zone name
     *
     * @param zoneName
     * @return List of Grade instance.
     */
    List<Grade> getAllGradesByZoneName(String zoneName);
}
