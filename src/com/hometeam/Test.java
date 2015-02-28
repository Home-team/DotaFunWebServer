package com.hometeam;

import com.hometeam.service.UserService;
import org.apache.log4j.Logger;

public class Test {
    private static final Logger LOG = Logger.getLogger(Test.class);

    public static void main(String[] args) {
        UserService userService = new UserService();

        for (int i = 1; i<10; i++) {
            userService.registration("a"+i, "a"+i);
        }
    }
}
