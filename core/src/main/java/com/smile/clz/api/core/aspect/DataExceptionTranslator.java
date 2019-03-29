package com.smile.clz.api.core.aspect;

import com.smile.clz.api.core.ErrorCode;
import com.smile.clz.api.core.exception.ClassApiException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author hasithagamage
 * @date 10/28/17
 */

@Aspect
@Component
public class DataExceptionTranslator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataExceptionTranslator.class);

    private static final String DATA_ACCESS_EXCEPTION_HAS_OCCURRED_WHILE_ACCESSING_THE_DAO_LAYER =
            "Data Access Exception has occurred while accessing the DAO layer";

    @AfterThrowing(pointcut = "within(com.smile.class_api.api.core.service.impl.*)", throwing = "ex")
    public void translateException(DataAccessException ex) throws ClassApiException {
        LOGGER.error(DATA_ACCESS_EXCEPTION_HAS_OCCURRED_WHILE_ACCESSING_THE_DAO_LAYER, ex);
        throw new ClassApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.GENERIC_DAO_LEVEL_EXCEPTION,
                DATA_ACCESS_EXCEPTION_HAS_OCCURRED_WHILE_ACCESSING_THE_DAO_LAYER, ex);
    }
}
