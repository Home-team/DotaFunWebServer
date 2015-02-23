package com.hometeam.dao.mySqlImpl;

import com.hometeam.entity.User;
import com.hometeam.util.PooledDataSource;
import junit.framework.TestCase;

import java.sql.Connection;
import java.util.List;

public class UserDaoImplTest extends TestCase {
    UserDaoImpl userDao;
    Connection connection;

    public void setUp() throws Exception {
        connection = PooledDataSource.getConnection();
        userDao = new UserDaoImpl(connection);
    }

    @Override
    public void tearDown() throws Exception {
        connection.commit();
        connection.close();
    }

    public void testGetAll() throws Exception {
        List<User> all = userDao.getAll();
        System.out.println(1);
    }
}