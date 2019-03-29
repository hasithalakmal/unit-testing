package com.smile.clz.api.core.dao.impl;

import com.smile.clz.api.core.dao.ClassDao;
import com.smile.clz.api.core.dao.DaoConstants;
import com.smile.clz.api.beans.Class;
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
@Repository("classDao")
public class ClassDaoImpl extends GenericDaoImpl implements ClassDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassDaoImpl.class);

    @Autowired
    @Qualifier("classDataSource")
    private DataSource dataSource;

    private static final String GET_CLASS = "SELECT C.CLASS_ID, C.CLASS_NAME, C.CLASS_PART, C.CLASS_STATE, G.GRADE_ID, G.GRADE_NAME, G.GRADE_STATE, Z.ZONE_ID, Z.ZONE_NAME, Z.ZONE_STATE FROM CLASS C LEFT JOIN GRADE G ON C.CLASS_GRADE_ID=G.GRADE_ID LEFT JOIN ZONE Z ON G.GRADE_ZONE_ID=Z.ZONE_ID";

    private static final String BY_CLASS_NAME = " WHERE C.CLASS_NAME=:className";

    private static final String BY_GRADE_NAME = " WHERE G.GRADE_NAME=:gradeName";

    private static final String BY_ZONE_NAME = " WHERE Z.ZONE_NAME=:zoneName";

    /**
     * @inheritDoc
     */
    @Override
    public Class getClassByClassName(String className) {
        LOGGER.info("Preparing to retrieve class by class name [{}]", className);

        String query = GET_CLASS + BY_CLASS_NAME;

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
                getJdbcTemplate().getDataSource());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(DaoConstants.PARAM_CLASS_NAME, className);

        Class classObj = null;
        try {
            classObj = namedParameterJdbcTemplate.queryForObject(
                    query, parameters, (resultSet, i) -> populateClass(resultSet)
            );
        } catch (EmptyResultDataAccessException drfe) {
            //handled gracefully since scenario handled in service layer
            LOGGER.warn("No class with the given name {} {}", className, drfe);
        }
        return classObj;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Class> getAllClasses() {
        LOGGER.info("Preparing to retrieve all classes");

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
                getJdbcTemplate().getDataSource());

        final List<Class> listOfClasses = new ArrayList<>();

        namedParameterJdbcTemplate.query(GET_CLASS,
                (RowMapper<Class>) (rs, rowNum) -> {
                    Class classes = populateClass(rs);
                    listOfClasses.add(classes);
                    return null;
                });

        return listOfClasses;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Class> getAllClassesByGradeName(String gradeName) {
        LOGGER.info("Preparing to retrieve all classes by grade name [{}]",gradeName);

        final List<Class> listOfClasses = new ArrayList<>();

        String query = GET_CLASS + BY_GRADE_NAME;

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
                getJdbcTemplate().getDataSource());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(DaoConstants.PARAM_GRADE_NAME, gradeName);

        namedParameterJdbcTemplate.query(query, parameters,
                (RowMapper<Grade>) (rs, rowNum) -> {
                    Class classObj = populateClass(rs);
                    listOfClasses.add(classObj);
                    return null;
                });

        return listOfClasses;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Class> getAllClassesByZoneName(String zoneName) {
        LOGGER.info("Preparing to retrieve all classes by zone name [{}]",zoneName);

        final List<Class> listOfClasses = new ArrayList<>();

        String query = GET_CLASS + BY_ZONE_NAME;

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
                getJdbcTemplate().getDataSource());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(DaoConstants.PARAM_ZONE_NAME, zoneName);

        namedParameterJdbcTemplate.query(query, parameters,
                (RowMapper<Grade>) (rs, rowNum) -> {
                    Class classObj = populateClass(rs);
                    listOfClasses.add(classObj);
                    return null;
                });

        return listOfClasses;
    }

    private Class populateClass(ResultSet resultSet) throws SQLException {

        Zone zoneObject = new Zone();
        zoneObject.setZoneId(resultSet.getInt(DaoConstants.ZONE_ID));
        zoneObject.setZoneName(resultSet.getString(DaoConstants.ZONE_NAME));
        zoneObject.setZoneState(resultSet.getString(DaoConstants.ZONE_STATE));

        Grade gradeObject = new Grade();
        gradeObject.setGradeId(resultSet.getInt(DaoConstants.GRADE_ID));
        gradeObject.setGradeName(resultSet.getString(DaoConstants.GRADE_NAME));
        gradeObject.setGradeState(resultSet.getString(DaoConstants.GRADE_STATE));
        gradeObject.setZone(zoneObject);

        Class classObject = new Class();
        classObject.setClassId(resultSet.getInt(DaoConstants.CLASS_ID));
        classObject.setClassName(resultSet.getString(DaoConstants.CLASS_NAME));
        classObject.setClassPart(resultSet.getString(DaoConstants.CLASS_PART));
        classObject.setClassState(resultSet.getString(DaoConstants.CLASS_STATE));
        classObject.setGrade(gradeObject);

        return classObject;
    }
}
