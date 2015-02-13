package com.hometeam.service;

import com.hometeam.bean.MessageUserBean;
import com.hometeam.constant.R;
import com.hometeam.core.Core;
import org.apache.log4j.Logger;

public class MessageService {
    private static final Logger LOG = Logger.getLogger(MessageService.class);

    public void sendMessage(int sender, int receiver, String msg) {
        Core.getInstance().getMessageUserBean(sender).updateDate();
        MessageUserBean messageUserBean = Core.getInstance().getMessageUserBean(receiver);

        if (!messageUserBean.getFriend().contains(sender)) {
            return;
        }

        if (msg.length() > R.MESSAGE.MAX_LENGTH_MSG) {
            msg = msg.substring(0, R.MESSAGE.MAX_LENGTH_MSG);
        }

        messageUserBean.getMessage().add(msg);
    }

    public String getMessage(int receiver) {
        Core.getInstance().getMessageUserBean(receiver).updateDate();
        MessageUserBean messageUserBean = Core.getInstance().getMessageUserBean(receiver);
        return messageUserBean.getMessage().poll();
    }
}
