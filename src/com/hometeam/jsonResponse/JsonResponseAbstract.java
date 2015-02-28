package com.hometeam.jsonResponse;

import org.json.simple.JSONObject;

public class JsonResponseAbstract {
    String type;
    JSONObject jsonObject;

    public JsonResponseAbstract() {
    }

    public JsonResponseAbstract(String type, JSONObject jsonObject) {
        this.type = type;
        this.jsonObject = jsonObject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("type",type);
        object.put("content", jsonObject);
        return object.toJSONString();
    }
}
