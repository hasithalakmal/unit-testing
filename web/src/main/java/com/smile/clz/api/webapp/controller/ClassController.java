package com.smile.clz.api.webapp.controller;

import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.ClassManager;
import com.smile.clz.api.beans.Class;
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
public class ClassController extends AbstractRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassController.class);

    @Autowired
    private ClassManager classManager;

    @RequestMapping(value = "ping", method = RequestMethod.GET)
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public String ping() {
        String currentTime = Long.toString(System.currentTimeMillis());
        LOGGER.info("Ping request {}", currentTime);
        return currentTime;
    }

    @RequestMapping(value = "classes/className/{className}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public Class getClassByClassName(@PathVariable String className) throws ClassApiException {
        LOGGER.info("Request received to get class by class name [{}]", className);
        Class classObj;
        classObj = classManager.getClassByClassName(className);

        LOGGER.info("Return response for get class by class name");
        return classObj;
    }

    @RequestMapping(value = "classes",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List<Class> getAllClasses() throws ClassApiException {
        LOGGER.info("Request received to get all classes");
        List<Class> listOfClasses = null;
        listOfClasses = classManager.getAllClasses();

        LOGGER.info("Return response for get all classes");
        return listOfClasses;
    }

    @RequestMapping(value = "classes/gradeName/{gradeName}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List<Class> getAllClassesByGradeName(@PathVariable String gradeName) throws ClassApiException {
        LOGGER.info("Request received to get all class by grade name [{}]", gradeName);
        List<Class> listOfClasses = null;
        listOfClasses = classManager.getAllClassesByGradeName(gradeName);

        LOGGER.info("Return response for get  all class by grade name");
        return listOfClasses;
    }

    @RequestMapping(value = "classes/zoneName/{zoneName}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody
    public List<Class> getAllClassesByZoneName(@PathVariable String zoneName) throws ClassApiException {
        LOGGER.info("Request received to get all class by zone name [{}]", zoneName);
        List<Class> listOfClasses = null;
        listOfClasses = classManager.getAllClassesByZoneName(zoneName);

        LOGGER.info("Return response for get  all class by zone name");
        return listOfClasses;
    }
}
