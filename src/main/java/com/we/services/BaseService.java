package com.we.services;

import com.we.common.Tools;
import groovy.sql.GroovyRowResult;
import groovy.sql.Sql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by aman.ullah on 3/10/2019.
 */

@Service
public class BaseService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DataSource dataSource;

    @Autowired
    private EntityManager entityManager;

    // returns list of map; better to use.
    protected List<GroovyRowResult> executeSelectSql(String query) {
        Sql sql = new Sql(dataSource);
        try {
            consolePrint(query, null);
            return sql.rows(query);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // returns plain list
    protected List executeSelectSql2(String sql) {
        List resultList = null;
        Query query = entityManager.createQuery(sql);
        consolePrint(sql, null);
        try {
            resultList = query.getResultList();
            return resultList;
        } catch (NoResultException ex) {
            System.out.print("Exception: " + ex.getMessage());
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private void consolePrint(String query, Object params) {
        System.out.print("EXECUTING SQL: " + query);
        if (params != null) System.out.print("PARAMS: " + params);
    }

    public void getData(Map map) {
        System.out.print(map.get("name"));
    }

    protected Map setError(Map resultMap, String message) {
        resultMap.put(Tools.IS_ERROR, Boolean.TRUE);
        if (message != null) {
            resultMap.put(Tools.MESSAGE, message);
        }
        return resultMap;
    }

    protected Map setSuccess(Map resultMap, String message) {
        resultMap.put(Tools.IS_ERROR, Boolean.FALSE);
        if (message != null) {
            resultMap.put(Tools.MESSAGE, message);
        }
        return resultMap;
    }

}
