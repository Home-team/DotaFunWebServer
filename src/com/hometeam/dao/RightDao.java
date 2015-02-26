package com.hometeam.dao;

import com.hometeam.entity.Right;

import java.sql.SQLException;
import java.util.List;

public interface RightDao {
    void create(Right right) throws SQLException;

    void delete(Right right) throws SQLException;

    List<Right> findByUserId(int id) throws SQLException;

    List<Right> findByPermissionId(int id) throws SQLException;
}
