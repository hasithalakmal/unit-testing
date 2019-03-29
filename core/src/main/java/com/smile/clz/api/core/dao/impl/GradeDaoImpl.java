package com.smile.clz.api.core.dao.impl;

import com.smile.clz.api.core.dao.DaoConstants;
import com.smile.clz.api.core.dao.GradeDao;
import com.smile.clz.api.beans.Grade;
import com.smile.clz.api.beans.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * todo
 *
 * @author hasithagamage
 * @date 10/28/17
 **/
@Repository("gradeDao")
public class GradeDaoImpl extends GenericDaoImpl implements GradeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(GradeDaoImpl.class);

    @Autowired
    @Qualifier("classDataSource")
    private DataSource dataSource;

    private static final String GET_GRADE = "SELECT G.GRADE_ID, G.GRADE_NAME, Z.ZONE_ID, Z.ZONE_NAME, Z.ZONE_STATE, G.GRADE_STATE " +
            "FROM GRADE G LEFT JOIN ZONE Z ON G.GRADE_ZONE_ID=Z.ZONE_ID";

    private static final String BY_GRADE_NAME = " WHERE G.GRADE_NAME=:gradeName";

    private static final String BY_ZONE_NAME = " WHERE Z.ZONE_NAME=:zoneName";

    /**
     * @inheritDoc
     */
    @Override
    public Grade getGradeByGradeName(String gradeName) {
        LOGGER.info("Preparing to retrieve grade by grade name [{}]", gradeName);

        String query = GET_GRADE + BY_GRADE_NAME;

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
                getJdbcTemplate().getDataSource());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(DaoConstants.PARAM_GRADE_NAME, gradeName);

        Grade gradeObj = null;
        try {
            gradeObj = namedParameterJdbcTemplate.queryForObject(
                    query, parameters, (resultSet, i) -> populateGrade(resultSet)
            );
        } catch (EmptyResultDataAccessException drfe) {
            //handled gracefully since scenario handled in service layer
            LOGGER.warn("No grade with the given name {} {}", gradeName, drfe);
        }
        return gradeObj;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Grade> getAllGrades() {
        LOGGER.info("Preparing to retrieve all grades");

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
                getJdbcTemplate().getDataSource());

        final List<Grade> listOfGrades = new ArrayList<>();

        namedParameterJdbcTemplate.query(GET_GRADE,
                (RowMapper<Grade>) (rs, rowNum) -> {
                    Grade grades = populateGrade(rs);
                    listOfGrades.add(grades);
                    return null;
                });

        return listOfGrades;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Grade> getAllGradesByZoneName(String zoneName) {
        LOGGER.info("Preparing to retrieve all grades by zone name [{}]",zoneName);

        final List<Grade> listOfGrades = new ArrayList<>();

        String query = GET_GRADE + BY_ZONE_NAME;

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
                getJdbcTemplate().getDataSource());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(DaoConstants.PARAM_ZONE_NAME, zoneName);

        namedParameterJdbcTemplate.query(query,parameters,
                (RowMapper<Grade>) (rs, rowNum) -> {
                    Grade grades = populateGrade(rs);
                    listOfGrades.add(grades);
                    return null;
                });

        return listOfGrades;
    }

    private Grade populateGrade(ResultSet resultSet) throws SQLException {
        Grade gradeObject = new Grade();
        gradeObject.setGradeId(resultSet.getInt(DaoConstants.GRADE_ID));
        gradeObject.setGradeName(resultSet.getString(DaoConstants.GRADE_NAME));
        gradeObject.setGradeState(resultSet.getString(DaoConstants.GRADE_STATE));

        Zone zoneObject = new Zone();
        zoneObject.setZoneId(resultSet.getInt(DaoConstants.ZONE_ID));
        zoneObject.setZoneName(resultSet.getString(DaoConstants.ZONE_NAME));
        zoneObject.setZoneState(resultSet.getString(DaoConstants.ZONE_STATE));

        gradeObject.setZone(zoneObject);

        return gradeObject;
    }
}
