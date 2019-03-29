package com.smile.clz.api.core.dao.impl;

import com.smile.clz.api.core.dao.DaoConstants;
import com.smile.clz.api.core.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * The base dao class for generic functionality
 *
 * @author hasithagamage
 * @date 10/28/17
 */
@Repository("genericDao")
public class GenericDaoImpl extends JdbcDaoSupport implements GenericDao {

    @Autowired
    @Qualifier("classDataSource")
    private DataSource dataSource;

    private Map<Integer, String> contextTypes;

    private static final String GET_ALL_CONTEXT_TYPES = "SELECT ID, NAME FROM CONTEXT_TYPE";

    @PostConstruct
    private void init() {
        setDataSource(dataSource);
    }

    /**
     * @inheritDoc
     */
    public Map<Integer, String> getContextTypes() {

        if (CollectionUtils.isEmpty(contextTypes)) {
            contextTypes = retrieveTypes(GET_ALL_CONTEXT_TYPES);
        }
        return contextTypes;
    }

    /**
     * The method to retrieve any Type which consists of key-value pairs.
     *
     * @param query The query should fetch the key and value of all entries in the type table.
     * @return
     */
    Map<Integer,String> retrieveTypes(String query) {

        Map<Integer, String> typesMap;

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
                getJdbcTemplate().getDataSource());
        typesMap = new HashMap<>();
        namedParameterJdbcTemplate.query(query, (resultSet, i) -> {
            Integer typeId = resultSet.getInt(DaoConstants.ID);
            String typeName = resultSet.getString(DaoConstants.NAME);
            typesMap.put(typeId, typeName);
            return null;
        });

        return typesMap;
    }
}
