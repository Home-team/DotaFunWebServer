package com.hometeam.core;

import com.hometeam.bean.MessageUserBean;
import com.hometeam.dao.impl.PremSendMsgDaoImpl;
import com.hometeam.dao.impl.UserDaoImpl;
import com.hometeam.entity.PremSendMsg;
import com.hometeam.entity.User;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Core {
    private static final Logger LOG = Logger.getLogger(Core.class);

    private static Core instance = null;

    private Map<String, Integer> login;
    private Random random;
    private long idCounter = 0;
    private Map<Integer, MessageUserBean> messageUserBeanList;

    private Core() {
        random = new Random();
        messageUserBeanList = new HashMap<>();
        login = new HashMap<>();
    }

    public static Core getInstance() {
        if(instance == null) {
            instance = new Core();

            UserDaoImpl userDao = new UserDaoImpl();
            PremSendMsgDaoImpl premSendMsgDao = new PremSendMsgDaoImpl();
            MessageUserBean messageUserBean;

            List<User> all = userDao.getAll();
            for (User user : all) {
                messageUserBean = new MessageUserBean(user.getId());
                List<PremSendMsg> byReceiver = premSendMsgDao.getByReceiver(user.getId());
                for (PremSendMsg premSendMsg : byReceiver) {
                    messageUserBean.getFriend().add(premSendMsg.getSender());
                }
                instance.messageUserBeanList.put(user.getId(), messageUserBean);
            }
        }
        return instance;
    }

    public Integer getIdBySession(String session) {
        if(login.containsKey(session)) {
            return login.get(session);
        } else {
            return null;
        }
    }

    public synchronized void addUser(int id) {
        messageUserBeanList.put(id, new MessageUserBean(id));
    }

    private synchronized long getNextId() {
        return idCounter++;
    }

    private char getRandomLetter() {
        return (char) (65 + (int)(Math.random() * ((90 - 65) + 1)));
    }

    public synchronized String getSession() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getNextId());
        for(int i = 0; i<10; i++) {
            stringBuffer.append(getRandomLetter());
        }
        return stringBuffer.toString();
    }

    public synchronized void setSession(int id, String session) {
        if(login.containsKey(session)) {
            login.remove(session);
        }
        login.put(session, id);
    }

    public MessageUserBean getMessageUserBean(int id) {
        return instance.messageUserBeanList.get(id);
    }
}
