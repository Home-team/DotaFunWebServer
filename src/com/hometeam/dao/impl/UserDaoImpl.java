package com.hometeam.dao.impl;

import com.hometeam.dao.UserDao;
import com.hometeam.entity.User;
import com.hometeam.util.PooledDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    private User getUserByResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

    @Override
    public User find(int id) {
        Connection connection = PooledDataSource.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `user` WHERE user.id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = getUserByResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }
        return user;
    }

    public User find(String login) {
        Connection connection = PooledDataSource.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `user` WHERE user.login = ?");
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = getUserByResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }
        return user;
    }

    @Override
    public void create(User user) {
        Connection connection = PooledDataSource.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO `user` (`login`, `password`) VALUES (?, ?)");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }
    }

    @Override
    public void update(User user) {
        Connection connection = PooledDataSource.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE `user` SET `login`=?, `password`=? WHERE (`id`=?)");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }
    }

    @Override
    public void delete(User user) {
        Connection connection = PooledDataSource.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `user` WHERE (`id`=?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }
    }

    @Override
    public List<User> getAll() {
        Connection connection = PooledDataSource.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user;
        List<User> userList = new ArrayList<User>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `user`");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = getUserByResultSet(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }
        return userList;
    }
}
