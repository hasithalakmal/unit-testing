package com.smile.clz.api.webapp.controller;

import com.smile.clz.api.core.ErrorCode;
import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.exception.Error;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Base Controller
 *
 * @author hasithagamage
 * @date 10/28/17
 */
public class AbstractRestController {

  protected static final String UNKNOWN_ERROR = "Unknown Error";
  protected static final String ERRORS = "errors.";
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRestController.class);
  @Autowired
  private MessageSource messages;

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Error handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {

    LOGGER.info("Handling error {}", e);
    if (e instanceof ClassApiException) {
      LOGGER.info("Handling class api exception");
      Locale locale = request.getLocale();
      String message = this.messages
          .getMessage(ERRORS + ((ClassApiException) e).getHttpStatusCode().value(), new Object[]{}, e.getMessage(),
              locale);
      return getError(((ClassApiException) e).getHttpStatusCode().value(), ((ClassApiException) e).getErrorCode(),
          message, ((ClassApiException) e).getHttpStatusCode().toString(), response);
    } else {
      LOGGER.info("Handling other exception");
      LOGGER.error("Generic exception handler: {}", e.getMessage(), e);
      Locale locale = request.getLocale();
      String message = this.messages
          .getMessage(ERRORS + ErrorCode.UNEXPECTED_ERROR, new Object[]{}, UNKNOWN_ERROR, locale);
      return getError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCode.UNEXPECTED_ERROR, message, e.getMessage(),
          response);
    }
  }

  private Error getError(int httpStatusCode, String errorCode, String description, String additionalInfo,
      HttpServletResponse response) {
    Error error = new Error();
    error.setCode(errorCode);
    error.setDescription(description);
    error.setAdditionalInfo(additionalInfo);
    response.setStatus(httpStatusCode);
    return error;
  }
}
