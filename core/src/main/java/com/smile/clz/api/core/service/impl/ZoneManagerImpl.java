package com.smile.clz.api.core.service.impl;

import static java.text.MessageFormat.format;

import com.smile.clz.api.beans.Zone;
import com.smile.clz.api.core.ErrorCode;
import com.smile.clz.api.core.dao.ZoneDao;
import com.smile.clz.api.core.exception.ClassApiException;
import com.smile.clz.api.core.service.ZoneManager;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
@Service("zoneManager")
public class ZoneManagerImpl implements ZoneManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(ZoneManagerImpl.class);

  @Autowired
  private ZoneDao zoneDao;

  /**
   * @inheritDoc
   */
  @Override
  public Zone getZoneByZoneName(String zoneName) throws ClassApiException {
    LOGGER.info("Entered get zone by zone name [{}]", zoneName);

    validateZoneName(zoneName);
    LOGGER.info("Zone name is validated [{}]", zoneName);

    Zone zoneObj = zoneDao.getZoneByZoneName(zoneName);

    if (zoneObj == null) {
      String error = format("Zone does not exist for given zone name [{0}]", zoneName);
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.NOT_FOUND, ErrorCode.ZONE_NOT_FOUND, error);
    }

    LOGGER.info("Retrieved zone for zone name: [{}]", zoneName);
    return zoneObj;
  }

  /**
   * @inheritDoc
   */
  @Override
  public List<Zone> getAllZones() throws ClassApiException {
    LOGGER.info("Entered get all zones");

    List<Zone> listOfZones = zoneDao.getAllZones();

    if (CollectionUtils.isEmpty(listOfZones)) {
      String error = format("Zones does not exist [{0}]", listOfZones);
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.NOT_FOUND, ErrorCode.ZONE_NOT_FOUND, error);
    }

    LOGGER.info("Retrieved all zones: [{}]", listOfZones);
    return listOfZones;
  }

  /**
   * The method to validate the zone name
   */
  private void validateZoneName(String zoneName) throws ClassApiException {
    if (StringUtils.isBlank(zoneName)) {
      String error = format("Zone Name is not present: {0}", zoneName);
      LOGGER.error(error);
      throw new ClassApiException(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_PARAMETERS, error);
    }
  }
}
