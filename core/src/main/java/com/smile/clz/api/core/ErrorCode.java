package com.smile.clz.api.core;

/**
 * The class to hold error codes for Class API
 *
 * @author hasithagamage
 * @date 10/28/17
 */
public final class ErrorCode {

  public static final String INVALID_PARAMETERS = "10000";
  public static final String UNEXPECTED_ERROR = "10010";
  public static final String GENERIC_DAO_LEVEL_EXCEPTION = "10020";
  public static final String CLASS_NOT_FOUND = "10200";
  public static final String GRADE_NOT_FOUND = "10210";
  public static final String ZONE_NOT_FOUND = "10220";
  public static final String WEATHER_API_ERROR = "10300";

  private ErrorCode() {
    //Disallow instantiation
  }


}
