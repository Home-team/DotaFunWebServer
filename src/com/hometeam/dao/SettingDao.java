package com.hometeam.dao;

import com.hometeam.entity.Setting;

import java.sql.SQLException;
import java.util.List;

public interface SettingDao {
    List<Setting> findByUserId(int id) throws SQLException;

    List<Setting> findByName(String name) throws SQLException;

    Setting find(int id, String name);

    void create(Setting setting);

    void update(Setting setting) throws SQLException;

    void delete(Setting setting) throws SQLException;
}
