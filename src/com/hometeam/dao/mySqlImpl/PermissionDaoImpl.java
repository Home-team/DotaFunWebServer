package com.hometeam.dao.mySqlImpl;

import com.hometeam.dao.PermissionDao;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class PermissionDaoImpl implements PermissionDao{
    private static final Logger LOG = Logger.getLogger(PermissionDaoImpl.class);

    private Connection connection;

    public PermissionDaoImpl(Connection connection) {
        this.connection = connection;
    }
}
