package com.hometeam.dao;

import com.hometeam.entity.Contact;

import java.sql.SQLException;
import java.util.List;

public interface ContactDao {
    List<Contact> findBySender(int id);

    List<Contact> findByReceiver(int id);

    void create(Contact contact) throws SQLException;

    void delete(Contact contact) throws SQLException;
}
