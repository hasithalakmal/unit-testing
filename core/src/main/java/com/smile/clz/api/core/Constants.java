package com.smile.clz.api.core;

/**
 * The constants file for api
 *
 * @author hasithagamage
 * @date 10/28/17
 */
public final class Constants {

  public static final String APPLICATION_JSON = "application/json";
  public static final int DEFAULT_CONTEXT_TYPE_ID = 2;
  public static final String WHITE_SPACE = " ";
  //Hub Events
  public static final String USER_CREATED_EVENT_NAME = "user.created";
  public static final String USER_UPDATED_EVENT_NAME = "user.updated";
  public static final String USER_DELETED_EVENT_NAME = "user.deleted";
  public static final String HUB_SUBSCRIBE_MODE = "subscribe";
  public static final String TOKEN_URL_SUFFIX = "?token=";
  public static final String ACCOUNT_CONTEXT = "ACCOUNT";
  public static final String CORRELATION_ID_INTERNAL_KEY = "Correlation_Id";
  public static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
  public static final String ERROR = "error";
  private Constants() {

  }

}
