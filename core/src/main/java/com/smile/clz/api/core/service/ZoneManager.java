package com.smile.clz.api.core.service;

import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.beans.Zone;

import java.util.List;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
public interface ZoneManager {
    /**
     * The method to retrieve the zone by zone name
     *
     * @param zoneName
     * @return Zone instance.
     */
    Zone getZoneByZoneName(String zoneName) throws ClassApiException;

    /**
     * The method to retrieve the all zones
     *
     * @return List of Zone instance.
     */
    List<Zone> getAllZones() throws ClassApiException;
}
