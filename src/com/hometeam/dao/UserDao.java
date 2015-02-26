package com.hometeam.dao;

import com.hometeam.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    User findById(int id) throws SQLException;

    User findByLogin(String login) throws SQLException;

    List<User> getAll() throws SQLException;

    List<User> getAll(int offset, int limit) throws SQLException;

    List<User> findBySender(User user) throws SQLException;

    List<User> findByReceiver(User user) throws SQLException;

    void create(User user) throws SQLException;

    void update(User user) throws SQLException;

    void delete(User user) throws SQLException;
}
