package com.hometeam.dao.mySqlImpl;

import com.hometeam.dao.ContactDao;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class ContactDaoImpl implements ContactDao {
    private static final Logger LOG = Logger.getLogger(ContactDaoImpl.class);

    private Connection connection;

    public ContactDaoImpl(Connection connection) {
        this.connection = connection;
    }
}
