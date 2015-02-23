package com.hometeam.dao.factory.mySqlImpl;

import com.hometeam.dao.*;
import com.hometeam.dao.factory.DaoFactory;
import com.hometeam.util.PooledDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class DaoFactoryImpl implements DaoFactory{
    private static final Logger LOG = Logger.getLogger(DaoFactoryImpl.class);

    private Connection connection;

    public DaoFactoryImpl(Connection connection) {
        this.connection = PooledDataSource.getConnection();
    }

    @Override
    public ContactDao getContactDao() {
        return null;
    }

    @Override
    public PermissionDao getPermissionDao() {
        return null;
    }

    @Override
    public RightDao getRightDao() {
        return null;
    }

    @Override
    public SettingDao getSettingDao() {
        return null;
    }

    @Override
    public UserDao getUserDao() {
        return null;
    }
}
