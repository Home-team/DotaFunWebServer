package com.hometeam.dao.mySqlImpl;

import com.hometeam.dao.UserDao;
import com.hometeam.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    private Connection connection;

    private User getUserByResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `user` WHERE `user`.id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = getUserByResultSet(resultSet);
            }
        } finally {
            preparedStatement.close();
            resultSet.close();
        }

        return user;
    }

    @Override
    public User findByLogin(String login) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `user` WHERE `user`.login = ?");
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = getUserByResultSet(resultSet);
            }
        } finally {
            preparedStatement.close();
            resultSet.close();
        }

        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        List<User> users = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `user`");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = getUserByResultSet(resultSet);
                users.add(user);
            }
        } finally {
            preparedStatement.close();
            resultSet.close();
        }

        return users;
    }

    @Override
    public List<User> getAll(int offset, int limit) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        List<User> users = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `user` LIMIT ?, ?");
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = getUserByResultSet(resultSet);
                users.add(user);
            }
        } finally {
            preparedStatement.close();
            resultSet.close();
        }

        return users;
    }

    @Override
    public void create(User user) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO `user` (`login`, `password`) VALUES (?, ?)");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } finally {
            preparedStatement.close();
        }
    }

    @Override
    public void update(User user) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE `user` SET `login`=?, `password`=? WHERE (`id`=?)");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
        } finally {
            preparedStatement.close();
        }
    }

    @Override
    public void delete(User user) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `user` WHERE (`id`=?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } finally {
            preparedStatement.close();
        }
    }
}