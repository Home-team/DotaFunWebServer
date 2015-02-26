package com.hometeam.dao;

import com.hometeam.entity.Contact;

import java.sql.SQLException;
import java.util.List;

public interface ContactDao {
    List<Contact> findBySenderId(int id) throws SQLException;

    List<Contact> findByReceiver(int id) throws SQLException;

    void create(Contact contact) throws SQLException;

    void delete(Contact contact) throws SQLException;
}
