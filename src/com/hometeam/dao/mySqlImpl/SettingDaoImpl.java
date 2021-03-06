package com.hometeam.dao.mySqlImpl;

import com.hometeam.dao.SettingDao;
import com.hometeam.entity.Setting;
import com.hometeam.util.PooledDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettingDaoImpl implements SettingDao {
    private static final Logger LOG = Logger.getLogger(SettingDaoImpl.class);

    private Connection connection;

    public SettingDaoImpl() {
        connection = PooledDataSource.getConnection();
    }

    public SettingDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private Setting getSettingByResultSet(ResultSet resultSet) throws SQLException {
        Setting setting = new Setting();
        setting.setUserId(resultSet.getInt("user_id"));
        setting.setName(resultSet.getString("name"));
        setting.setValue(resultSet.getString("value"));

        return setting;
    }

    @Override
    public List<Setting> findByUserId(int id) throws SQLException {
        connection = PooledDataSource.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Setting> settings = new ArrayList<>();
        Setting setting = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `setting` WHERE setting.user_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setting = getSettingByResultSet(resultSet);
                settings.add(setting);
            }
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }

        return settings;
    }

    @Override
    public List<Setting> findByName(String name) throws SQLException {
        connection = PooledDataSource.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Setting> settings = new ArrayList<>();
        Setting setting = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `setting` WHERE setting.`name` = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setting = getSettingByResultSet(resultSet);
                settings.add(setting);
            }
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }

        return settings;
    }

    @Override
    public Setting find(int id, String name) {
        connection = PooledDataSource.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Setting setting = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM setting WHERE setting.user_id = ? AND setting.`name` = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                setting = getSettingByResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOG.equals(e.getMessage());
        }

        return setting;
    }

    @Override
    public void create(Setting setting) {
        connection = PooledDataSource.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            try {
                preparedStatement = connection.prepareStatement("INSERT INTO `setting` (`user_id`, `name`, `value`) VALUES (?, ?, ?)");
                preparedStatement.setInt(1, setting.getUserId());
                preparedStatement.setString(2, setting.getName());
                preparedStatement.setString(3, setting.getValue());
                preparedStatement.executeUpdate();
            } finally {
                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException e) {
            LOG.equals(e.getMessage());
        }
    }

    @Override
    public void update(Setting setting) throws SQLException {
        connection = PooledDataSource.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE `setting` SET `value`=? WHERE (`user_id`=?)");
            preparedStatement.setString(1, setting.getValue());
            preparedStatement.setInt(2, setting.getUserId());
            preparedStatement.executeUpdate();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public void delete(Setting setting) throws SQLException {
        connection = PooledDataSource.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `setting` WHERE (`user_id`=?) AND (`name`=?)");
            preparedStatement.setInt(1, setting.getUserId());
            preparedStatement.setString(2, setting.getName());
            preparedStatement.executeUpdate();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }
}
