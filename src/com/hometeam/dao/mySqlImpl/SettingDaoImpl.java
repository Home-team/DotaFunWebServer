package com.hometeam.dao.mySqlImpl;

import com.hometeam.dao.SettingDao;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class SettingDaoImpl implements SettingDao{
    private static final Logger LOG = Logger.getLogger(SettingDaoImpl.class);

    private Connection connection;

    public SettingDaoImpl(Connection connection) {
        this.connection = connection;
    }
}
