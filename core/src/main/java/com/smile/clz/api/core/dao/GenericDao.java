package com.smile.clz.api.core.dao;

import java.util.Map;

/**
 * This is the interface for generic dao related methods
 *
 * @author hasithagamage
 * @date 10/28/17
 */
public interface GenericDao {

  /**
   * Them method to get context types from DB
   *
   * @return a map of context types woth context ID
   */
  Map<Integer, String> getContextTypes();
}