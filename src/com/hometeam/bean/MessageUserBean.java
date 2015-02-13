package com.hometeam.bean;

import org.apache.log4j.Logger;

import java.util.*;

public class MessageUserBean {
    private static final Logger LOG = Logger.getLogger(MessageUserBean.class);

    private int userId;
    private Queue<String> message;
    private Set<Integer> friend;
    private Date date;

    public MessageUserBean(int userId) {
        this.userId = userId;
        this.message = new LinkedList<>();
        this.friend = new HashSet<>();
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void updateDate() {
        this.date.setTime(System.currentTimeMillis());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Queue<String> getMessage() {
        return message;
    }

    public void setMessage(Queue<String> message) {
        this.message = message;
    }

    public Set<Integer> getFriend() {
        return friend;
    }

    public void setFriend(Set<Integer> friend) {
        this.friend = friend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageUserBean messageUserBean = (MessageUserBean) o;

        return userId == messageUserBean.userId;
    }

    @Override
    public int hashCode() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userId=" + userId +
                ", message=" + message +
                ", friend=" + friend +
                '}';
    }
}
