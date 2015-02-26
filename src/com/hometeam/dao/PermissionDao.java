package com.hometeam.dao;

import com.hometeam.entity.Permission;

import java.sql.SQLException;

public interface PermissionDao {
    Permission findById(int id) throws SQLException;

    Permission findByName(String name) throws SQLException;
}
