package com.hometeam.dao;

import com.hometeam.entity.User;

import java.util.List;

public interface UserDao {
    User find(int id);

    void create(User user);

    void update(User user);

    void delete(User user);

    List<User> getAll();
}
