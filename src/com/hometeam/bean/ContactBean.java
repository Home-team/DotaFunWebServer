package com.hometeam.bean;

import org.json.simple.JSONObject;

public class ContactBean {
    private int id;
    private String name;

    public ContactBean() {

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

    public JSONObject toJsonObject() {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("name", name);
        return object;
    }

    public String toJson() {
        return toJsonObject().toJSONString();
    }

    @Override
    public String toString() {
        return "ContactBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
