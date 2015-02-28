package com.hometeam;

import com.hometeam.jsonResponse.JsonError;
import org.apache.log4j.Logger;

public class Test {
    private static final Logger LOG = Logger.getLogger(Test.class);

    public static void main(String[] args) {
        JsonError jsonError = new JsonError("qweqwe");
        System.out.println(jsonError);
    }
}
