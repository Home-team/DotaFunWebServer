package com.hometeam.bean;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

public class UserBean {
    private static final Logger LOG = Logger.getLogger(UserBean.class);

    private int id;
    private String name;
    private String login;

    public UserBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String toJson() {
        JSONObject object = new JSONObject();
        object.put("id",id);
        object.put("name",name);
        object.put("login",login);
        return object.toJSONString();
    }
}
