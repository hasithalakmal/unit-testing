package com.smile.clz.api.core.dao;

import com.smile.clz.api.beans.Zone;
import java.util.List;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
public interface ZoneDao {

  /**
   * The method to retrieve the all zones by zone name The zone name is unique value for identifying zones
   *
   * @return Zone instance.
   */
  Zone getZoneByZoneName(String zoneName);

  /**
   * The method to retrieve the all zones
   *
   * @return List of Zone instance.
   */
  List<Zone> getAllZones();
}
