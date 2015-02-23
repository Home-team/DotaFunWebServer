package com.hometeam.dao.mySqlImpl;

import com.hometeam.dao.RightDao;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class RightDaoImpl implements RightDao{
    private static final Logger LOG = Logger.getLogger(RightDaoImpl.class);

    private Connection connection;

    public RightDaoImpl(Connection connection) {
        this.connection = connection;
    }
}
