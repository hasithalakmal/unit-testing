package com.smile.clz.api.core.service.impl;

import com.smile.clz.api.core.ErrorCode;
import com.smile.clz.api.core.dao.GradeDao;
import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.GradeManager;
import com.smile.clz.api.beans.Grade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.text.MessageFormat.format;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
@Service("gradeManager")
public class GradeManagerImpl implements GradeManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(GradeManagerImpl.class);

    @Autowired
    private GradeDao gradeDao;

    /**
     * @inheritDoc
     */
    @Override
    public Grade getGradeByGradeName(String gradeName)  throws ClassApiException {
        LOGGER.info("Entered get grade by grade name [{}]", gradeName);

        validateGradeName(gradeName);
        LOGGER.info("Grade name is validated [{}]", gradeName);

        Grade gradeObj = gradeDao.getGradeByGradeName(gradeName);

        if (gradeObj == null) {
            String error = format("Grade does not exist for given grade name [{0}]", gradeName);
            LOGGER.error(error);
            throw new ClassApiException(HttpStatus.NOT_FOUND, ErrorCode.GRADE_NOT_FOUND, error);
        }

        LOGGER.info("Retrieved grade for grade name: [{}]", gradeName);
        return gradeObj;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Grade> getAllGrades() throws ClassApiException {
        LOGGER.info("Entered get all grades");

        List<Grade> listOfGrades = gradeDao.getAllGrades();

        validateListOfGrades(listOfGrades);

        LOGGER.info("Retrieved all grades: [{}]", listOfGrades);
        return listOfGrades;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Grade> getAllGradesByZoneName(String zoneName) throws ClassApiException {
        LOGGER.info("Entered get all grades by zone name");

        if(StringUtils.isBlank(zoneName)){
            String error = format("Zone name is not present: {0}", zoneName);
            LOGGER.error(error);
            throw new ClassApiException(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_PARAMETERS, error);
        }

        List<Grade> listOfGrades = gradeDao.getAllGradesByZoneName(zoneName);

        validateListOfGrades(listOfGrades);

        LOGGER.info("Retrieved all grades [{}] for given zone: [{}]", listOfGrades, zoneName);
        return listOfGrades;
    }

    /**
     * The method to validate list of grades
     * @param listOfGrades
     */
    private void validateListOfGrades(List<Grade> listOfGrades) {
        if (CollectionUtils.isEmpty(listOfGrades)) {
            String error = format("Grades does not exist [{0}]", listOfGrades);
            LOGGER.error(error);
            throw new ClassApiException(HttpStatus.NOT_FOUND, ErrorCode.GRADE_NOT_FOUND, error);
        }
    }

    /**
     * The method to validate the grade name
     * @param gradeName
     */
    private void validateGradeName(String gradeName) throws ClassApiException {
        if (StringUtils.isBlank(gradeName)) {
            String error = format("Grade name is not present: {0}", gradeName);
            LOGGER.error(error);
            throw new ClassApiException(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_PARAMETERS, error);
        }
    }
}
