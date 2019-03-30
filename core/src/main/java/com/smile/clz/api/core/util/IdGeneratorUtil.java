package com.smile.clz.api.core.util;

import java.util.Random;

/**
 * @author hasithagamage
 * @date 10/28/17
 */
public final class IdGeneratorUtil {

  private static final int VALUE_OF_20_BITS = 1048576;
  private static final Random SEED = new Random();

  private IdGeneratorUtil() {
    //cannot instantiate
  }

  /**
   * Generate unique id
   */
  public static int generateUniqueId() {
    Random randNumGen = new Random(SEED.nextInt());
    int mostSig20Bits = randNumGen.nextInt(VALUE_OF_20_BITS);
    int leastSig43Bits = (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    return (mostSig20Bits) + leastSig43Bits;
  }
}
