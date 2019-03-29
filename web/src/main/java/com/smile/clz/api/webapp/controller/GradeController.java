package com.smile.clz.api.webapp.controller;

import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.GradeManager;
import com.smile.clz.api.beans.Grade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
@Controller
public class GradeController extends AbstractRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GradeController.class);

    @Autowired
    private GradeManager gradeManager;

    @RequestMapping(value = "grades/gradeName/{gradeName}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public Grade getGradeByGradeName(@PathVariable String gradeName) throws ClassApiException {
        LOGGER.info("Request received to get grade by grade name [{}]", gradeName);
        Grade gradeObj;
        gradeObj = gradeManager.getGradeByGradeName(gradeName);

        LOGGER.info("Return response for get grade by grade name");
        return gradeObj;
    }

    @RequestMapping(value = "grades",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List<Grade> getAllGrades() throws ClassApiException {
        LOGGER.info("Request received to get all grades");
        List<Grade> listOfGrades = null;
        listOfGrades = gradeManager.getAllGrades();

        LOGGER.info("Return response for get all grades");
        return listOfGrades;
    }

    @RequestMapping(value = "grades/zoneName/{zoneName}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List<Grade> getAllGradesByZoneName(@PathVariable String zoneName) throws ClassApiException {
        LOGGER.info("Request received to get all grade by zone name [{}]", zoneName);
        List<Grade> listOfGrades = null;
        listOfGrades = gradeManager.getAllGradesByZoneName(zoneName);

        LOGGER.info("Return response for get all grade by zone name");
        return listOfGrades;
    }
}
