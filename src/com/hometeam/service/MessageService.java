package com.hometeam.service;

import com.hometeam.bean.Message;
import com.hometeam.constant.R;
import com.hometeam.core.Messager;
import com.hometeam.exception.NoContactList;
import org.apache.log4j.Logger;

public class MessageService {
    private static final Logger LOG = Logger.getLogger(MessageService.class);
    private Messager messager = Messager.getInstance();

    public void sendMessage(int senderId, int receiverId, String message) throws NoContactList {
        if (message.length() > R.MESSAGE.MAX_LENGTH_MSG) {
            message = message.substring(0, R.MESSAGE.MAX_LENGTH_MSG);
        }

        messager.sendMessage(senderId, receiverId, message);
    }

    public Message getMessage(int receiverId) {
        return messager.getMessage(receiverId);
    }
}
