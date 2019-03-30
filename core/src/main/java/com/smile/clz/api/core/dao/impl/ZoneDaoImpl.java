package com.smile.clz.api.core.dao.impl;

import com.smile.clz.api.beans.Class;
import com.smile.clz.api.beans.Zone;
import com.smile.clz.api.core.dao.DaoConstants;
import com.smile.clz.api.core.dao.ZoneDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
@Repository("zoneDao")
public class ZoneDaoImpl extends GenericDaoImpl implements ZoneDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(ZoneDaoImpl.class);
  private static final String GET_ZONE = "SELECT ZONE_ID, ZONE_NAME, ZONE_STATE FROM ZONE";
  private static final String BY_ZONE_NAME = " WHERE ZONE_NAME=:zoneName";
  @Autowired
  @Qualifier("classDataSource")
  private DataSource dataSource;

  /**
   * @inheritDoc
   */
  @Override
  public Zone getZoneByZoneName(String zoneName) {
    LOGGER.info("Preparing to retrieve zone by zone name [{}]", zoneName);

    String query = GET_ZONE + BY_ZONE_NAME;

    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
        getJdbcTemplate().getDataSource());

    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue(DaoConstants.PARAM_ZONE_NAME, zoneName);

    Zone zoneObj = null;
    try {
      zoneObj = namedParameterJdbcTemplate.queryForObject(
          query, parameters, (resultSet, i) -> populateZone(resultSet)
      );
    } catch (EmptyResultDataAccessException drfe) {
      //handled gracefully since scenario handled in service layer
      LOGGER.warn("No zone with the given name {} {}", zoneName, drfe);
    }
    return zoneObj;
  }

  /**
   * @inheritDoc
   */
  @Override
  public List<Zone> getAllZones() {
    LOGGER.info("Preparing to retrieve all zones");

    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
        getJdbcTemplate().getDataSource());

    final List<Zone> listOfZones = new ArrayList<>();

    namedParameterJdbcTemplate.query(GET_ZONE,
        (RowMapper<Class>) (rs, rowNum) -> {
          Zone zones = populateZone(rs);
          listOfZones.add(zones);
          return null;
        });

    return listOfZones;
  }

  private Zone populateZone(ResultSet resultSet) throws SQLException {
    Zone zoneObject = new Zone();
    zoneObject.setZoneId(resultSet.getInt(DaoConstants.ZONE_ID));
    zoneObject.setZoneName(resultSet.getString(DaoConstants.ZONE_NAME));
    zoneObject.setZoneState(resultSet.getString(DaoConstants.ZONE_STATE));
    return zoneObject;
  }
}
