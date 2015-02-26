package com.hometeam.dao.mySqlImpl;

import com.hometeam.dao.RightDao;
import com.hometeam.entity.Right;
import com.hometeam.util.PooledDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RightDaoImpl implements RightDao {
    private static final Logger LOG = Logger.getLogger(RightDaoImpl.class);

    private Connection connection;

    public RightDaoImpl() {
        connection = PooledDataSource.getConnection();
    }

    public RightDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private Right getRightByResultSet(ResultSet resultSet) throws SQLException {
        Right right = new Right();
        right.setUserId(resultSet.getInt("user_id"));
        right.setPermissionId(resultSet.getInt("permission_id"));

        return right;
    }

    @Override
    public void create(Right right) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO `right` (`user_id`, `permission_id`) VALUES (?, ?)");
            preparedStatement.setInt(1, right.getUserId());
            preparedStatement.setInt(2, right.getPermissionId());
            preparedStatement.executeUpdate();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public void delete(Right right) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `right` WHERE (`user_id`=?) AND (`permission_id`=?)");
            preparedStatement.setInt(1, right.getUserId());
            preparedStatement.setInt(2, right.getPermissionId());
            preparedStatement.executeUpdate();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public List<Right> findByUserId(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        List<Right> rights = new ArrayList<>();
        Right right = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `right` WHERE `right`.user_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                right = getRightByResultSet(resultSet);
                rights.add(right);
            }
        } finally {
            preparedStatement.close();
            connection.close();
        }

        return rights;
    }

    @Override
    public List<Right> findByPermissionId(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        List<Right> rights = new ArrayList<>();
        Right right = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `right` WHERE `right`.permission_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                right = getRightByResultSet(resultSet);
                rights.add(right);
            }
        } finally {
            preparedStatement.close();
            connection.close();
        }

        return rights;
    }
}
