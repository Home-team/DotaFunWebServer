package com.hometeam.exception;

import org.apache.log4j.Logger;

public class UserAlreadyExist extends Exception {
    private static final Logger LOG = Logger.getLogger(UserAlreadyExist.class);
}
