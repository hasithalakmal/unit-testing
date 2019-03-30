package com.smile.clz.api.core.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception to handle error scenarios in Permission API
 *
 * @author hasithagamage
 * @date 10/28/17
 */
public class ClassApiException extends RuntimeException {

  private final HttpStatus httpStatusCode;
  private final String errorCode;

  public ClassApiException(HttpStatus httpStatusCode, String errorCode, String message, Throwable t) {
    super(message, t);
    this.errorCode = errorCode;
    this.httpStatusCode = httpStatusCode;
  }

  public ClassApiException(HttpStatus httpStatusCode, String errorCode, String message) {
    this(httpStatusCode, errorCode, message, null);
  }

  public String getErrorCode() {
    return errorCode;
  }

  public HttpStatus getHttpStatusCode() {
    return httpStatusCode;
  }
}
