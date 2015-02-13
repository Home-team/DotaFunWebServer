package com.hometeam.service;

import com.hometeam.core.Core;
import com.hometeam.dao.impl.UserDaoImpl;
import com.hometeam.entity.User;
import org.apache.log4j.Logger;

public class UserService {
    private static final Logger LOG = Logger.getLogger(UserService.class);

    UserDaoImpl userDao = new UserDaoImpl();

    public void addUser(String login, String password) {
        User user = new User();
        Core core = Core.getInstance();

        user.setLogin(login);
        user.setPassword(password);

        userDao.create(user);
        User target = userDao.find(login);
        core.addUser(target.getId());
    }

    public String login(String login, String password) {
        User user = userDao.find(login);
        if(user.getPassword().equals(password)) {
            String session = getSession(user.getId());
            Core.getInstance().getMessageUserBean(Core.getInstance().getIdBySession(session)).updateDate();
            return session;
        } else {
            return null;
        }
    }

    private String getSession(int id) {
        Core core = Core.getInstance();
        String session = core.getSession();
        core.setSession(id, session);
        return session;
    }
}
