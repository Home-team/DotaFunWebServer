package com.hometeam.dao.mySqlImpl;

import com.hometeam.dao.PermissionDao;
import com.hometeam.entity.Permission;
import com.hometeam.util.PooledDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PermissionDaoImpl implements PermissionDao{
    private static final Logger LOG = Logger.getLogger(PermissionDaoImpl.class);

    private Connection connection;

    public PermissionDaoImpl() {
        connection = PooledDataSource.getConnection();
    }

    public PermissionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private Permission getByResultSet(ResultSet resultSet) throws SQLException {
        Permission permission = new Permission();
        permission.setId(resultSet.getInt("id"));
        permission.setName(resultSet.getString("name"));
        permission.setDescription(resultSet.getString("description"));
        return permission;
    }

    @Override
    public Permission findById(int id) throws SQLException {
        connection = PooledDataSource.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Permission permission = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `permission` WHERE permission.id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                permission = getByResultSet(resultSet);
            }
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }

        return permission;
    }

    @Override
    public Permission findByName(String name) throws SQLException {
        connection = PooledDataSource.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Permission permission = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `permission` WHERE permission.`name` = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                permission = getByResultSet(resultSet);
            }
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }

        return permission;
    }
}
