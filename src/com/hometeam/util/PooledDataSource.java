package com.hometeam.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class PooledDataSource {
    private static final Logger LOG = Logger.getLogger(PooledDataSource.class);
    private static ComboPooledDataSource pooledDataSource;

    private PooledDataSource() {
    }

    public static Connection getConnection() {
        if (pooledDataSource == null) {
            LOG.info("Load pooled data source");
            Properties properties = new Properties();
            InputStream inputStream = PooledDataSource.class.getClassLoader().getResourceAsStream("database.properties");
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                LOG.error(e.getMessage());
            }

            pooledDataSource = new ComboPooledDataSource();
            try {
                pooledDataSource.setDriverClass(properties.getProperty("db.driverClass"));
            } catch (PropertyVetoException e) {
                LOG.error(e.getMessage());
            }
            pooledDataSource.setJdbcUrl(properties.getProperty("db.url"));
            pooledDataSource.setUser(properties.getProperty("db.user"));
            pooledDataSource.setPassword(properties.getProperty("db.password"));
            pooledDataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("db.minPoolSize")));
            pooledDataSource.setAcquireIncrement(Integer.parseInt(properties.getProperty("db.acquireIncrement")));
            pooledDataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("db.maxPoolSize")));
        }
        Connection connection = null;
        try {
            connection = pooledDataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return connection;
    }
}
