package com.hometeam.jsonResponse;

import org.json.simple.JSONObject;

public class JsonError extends JsonResponseAbstract{
    public JsonError(String errorText) {
        JSONObject object = new JSONObject();
        object.put("text", errorText);
        this.setJsonObject(object);
        this.setType("error");
    }
}
