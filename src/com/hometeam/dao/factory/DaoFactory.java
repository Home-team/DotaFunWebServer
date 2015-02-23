package com.hometeam.dao.factory;

import com.hometeam.dao.*;

public interface DaoFactory {
    public ContactDao getContactDao();

    public PermissionDao getPermissionDao();

    public RightDao getRightDao();

    public SettingDao getSettingDao();

    public UserDao getUserDao();
}
