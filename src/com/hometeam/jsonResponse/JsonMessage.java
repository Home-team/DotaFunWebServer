package com.hometeam.jsonResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

public class JsonMessage extends JsonResponseAbstract {
    private static final Logger LOG = Logger.getLogger(JsonMessage.class);

    public JsonMessage(String text) {
        JSONObject object = new JSONObject();
        object.put("text",text);
        this.setType("message");
        this.setJsonObject(object);
    }
}
