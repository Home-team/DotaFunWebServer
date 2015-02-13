package com.hometeam.service;

import com.hometeam.bean.MessageUserBean;
import com.hometeam.core.Core;
import com.hometeam.dao.impl.PremSendMsgDaoImpl;
import com.hometeam.entity.PremSendMsg;
import org.apache.log4j.Logger;

public class FriendService {
    private static final Logger LOG = Logger.getLogger(FriendService.class);

    PremSendMsgDaoImpl premSendMsgDao = new PremSendMsgDaoImpl();

    public void addFriend(int sender, int receiver) {
        MessageUserBean messageUserBean = Core.getInstance().getMessageUserBean(receiver);

        if (!messageUserBean.getFriend().contains(sender)) {
            PremSendMsg premSendMsg = new PremSendMsg();
            premSendMsg.setSender(sender);
            premSendMsg.setReceiver(receiver);

            premSendMsgDao.create(premSendMsg);
            messageUserBean.getFriend().add(sender);
        }
    }

    public void removeFriend(int sender, int receiver) {
        MessageUserBean messageUserBean = Core.getInstance().getMessageUserBean(receiver);

        if(messageUserBean.getFriend().contains(sender)) {
            PremSendMsg premSendMsg = new PremSendMsg();
            premSendMsg.setReceiver(receiver);
            premSendMsg.setSender(sender);

            premSendMsgDao.delete(premSendMsg);
            messageUserBean.getFriend().remove(sender);
        }
    }
}
