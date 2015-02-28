package com.hometeam.service;

import com.hometeam.constant.R;
import com.hometeam.dao.SettingDao;
import com.hometeam.dao.UserDao;
import com.hometeam.dao.mySqlImpl.SettingDaoImpl;
import com.hometeam.dao.mySqlImpl.UserDaoImpl;
import com.hometeam.entity.Setting;
import com.hometeam.entity.User;
import org.apache.log4j.Logger;

public class UserService {
    private static final Logger LOG = Logger.getLogger(UserService.class);
    private UserDao userDao = new UserDaoImpl();
    private SettingDao settingDao = new SettingDaoImpl();

    public void registration(String login, String password) {
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        userDao.create(newUser);

        User target = authentication(login, password);

        Setting name = new Setting(target.getId(), R.SETTING.NAME, target.getLogin());
        Setting crystal = new Setting(target.getId(), R.SETTING.CRYSTAL, "0");
        Setting slot = new Setting(target.getId(), R.SETTING.SLOT, "1");

        settingDao.create(name);
        settingDao.create(crystal);
        settingDao.create(slot);
    }

    public User authentication(String login, String password) {
        User byLogin = userDao.findByLogin(login);

        if (byLogin == null) {
            return byLogin;
        }

        if (!byLogin.getPassword().equals(password)) {
            return null;
        } else {
            return byLogin;
        }
    }
}
