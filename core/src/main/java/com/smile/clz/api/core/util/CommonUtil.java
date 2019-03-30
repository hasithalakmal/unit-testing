package com.smile.clz.api.core.util;

import com.smile.clz.api.core.ErrorCode;
import com.smile.clz.api.core.exception.ClassApiException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * @author hasithagamage
 * @date 10/28/17
 */
public final class CommonUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);
  private static final SecureRandom random = new SecureRandom();
  private static final int DEFAULT_NO_OF_BITS = 130;
  private static final int DEFAULT_RADIX = 32;

  private CommonUtil() {
    //Disallow instantiation
  }

  /**
   * Converted the source object to destination
   *
   * @return the destination object after converting the properties
   */
  public static <T> T copyUsingBeanUtils(T destination, T source) {
    try {
      BeanUtils.copyProperties(destination, source);
    } catch (ReflectiveOperationException roe) {
      String message = MessageFormat.format("Error in converting source {0} -> destination {1}", destination, source);
      LOGGER.error(message, roe);
      throw new ClassApiException(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_PARAMETERS, message);
    }
    return destination;
  }

  /**
   * Get user type id from list
   */
  public static Integer getUserTypeIdByName(String userTypeName, Map<Integer, String> userTypes) {
    for (Map.Entry entry : userTypes.entrySet()) {
      if (entry.getValue() != null && entry.getValue().equals(userTypeName)) {
        return (Integer) entry.getKey();
      }
    }
    return null;
  }

  /**
   * Get context Type ID from context type
   */
  public static Integer getContextTypeIdByContextType(String contextType, Map<Integer, String> contextTypes) {
    for (Map.Entry entry : contextTypes.entrySet()) {
      if (entry.getValue() != null && entry.getValue().equals(contextType)) {
        return (Integer) entry.getKey();
      }
    }
    return null;
  }

  /**
   * The method to generate a random alpha string
   */
  public static String generateRandomAlphaString() {
    return new BigInteger(DEFAULT_NO_OF_BITS, random).toString(DEFAULT_RADIX);
  }


}
