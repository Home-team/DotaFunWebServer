package com.hometeam.core;

import com.hometeam.bean.Message;
import com.hometeam.dao.ContactDao;
import com.hometeam.dao.UserDao;
import com.hometeam.dao.mySqlImpl.ContactDaoImpl;
import com.hometeam.dao.mySqlImpl.UserDaoImpl;
import com.hometeam.entity.Contact;
import com.hometeam.entity.User;
import com.hometeam.exception.NoContactList;
import org.apache.log4j.Logger;

import java.util.*;

public class Messager {
    private static final Logger LOG = Logger.getLogger(Messager.class);

    private static Messager instance = null;
    private HashMap<Integer, MessageBean> messagent;

    public static Messager getInstance() {
        if (instance == null) {
            instance = new Messager();
        }
        return instance;
    }

    private Messager() {
        this.messagent = new HashMap<>();
        ContactDao contactDao = new ContactDaoImpl();
        UserDao userDao = new UserDaoImpl();
        MessageBean messageBean = null;

        for (User user : userDao.getAll()) {
            messageBean = new MessageBean();
            for (Contact contact : contactDao.findByReceiver(user.getId())) {
                messageBean.getSenders().add(contact.getSenderId());
            }
            messagent.put(user.getId(), messageBean);
        }
    }

    public void addUser(int userId) {
        messagent.put(userId,new MessageBean());
    }

    public void addContact(int sender, int receiver) {
        MessageBean messageBean = messagent.get(receiver);
        messageBean.getSenders().add(sender);
    }

    public void sendMessage(int sender, int receiver, String message) throws NoContactList {
        MessageBean messageBean = messagent.get(receiver);
        if (!messageBean.getSenders().contains(sender)) {
            throw new NoContactList();
        }
        messageBean.addMessage(sender, message);
    }

    public Message getMessage(int receiver) {
        Message poll = messagent.get(receiver).getMessages().poll();
        return poll;
    }

    class MessageBean {
        Queue<Message> messages = new LinkedList<>();
        Set<Integer> senders = new TreeSet<>();

        public Queue<Message> getMessages() {
            return messages;
        }

        public Set<Integer> getSenders() {
            return senders;
        }

        public void addMessage(int sender, String message) {
            Message newMessage = new Message();
            newMessage.setMessage(message);
            newMessage.setSender(sender);
            messages.add(newMessage);
        }
    }
}
