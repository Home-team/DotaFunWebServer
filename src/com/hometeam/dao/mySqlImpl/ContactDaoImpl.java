package com.hometeam.dao.mySqlImpl;

import com.hometeam.dao.ContactDao;
import com.hometeam.entity.Contact;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {
    private static final Logger LOG = Logger.getLogger(ContactDaoImpl.class);

    private Connection connection;

    protected Contact getByResultSet(ResultSet resultSet) throws SQLException {
        Contact contact = new Contact();
        contact.setReceiverId(resultSet.getInt("receiver_id"));
        contact.setSenderId(resultSet.getInt("sender_id"));
        return contact;
    }

    public ContactDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Contact> findBySenderId(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Contact> contacts = new ArrayList<>();
        Contact contact = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `contact` WHERE contact.sender_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contact = getByResultSet(resultSet);
                contacts.add(contact);
            }
        } finally {
            resultSet.close();
            preparedStatement.close();
        }

        return contacts;
    }

    @Override
    public List<Contact> findByReceiver(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Contact> contacts = new ArrayList<>();
        Contact contact = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `contact` WHERE contact.receiver_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contact = getByResultSet(resultSet);
                contacts.add(contact);
            }
        } finally {
            resultSet.close();
            preparedStatement.close();
        }

        return contacts;
    }

    @Override
    public void create(Contact contact) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO `contact` (`sender_id`, `receiver_id`) VALUES (?, ?)");
            preparedStatement.setInt(1, contact.getSenderId());
            preparedStatement.setInt(2, contact.getReceiverId());
            preparedStatement.executeUpdate();
        } finally {
            preparedStatement.close();
        }
    }

    @Override
    public void delete(Contact contact) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM `contact` WHERE (`sender_id`=?) AND (`receiver_id`=?)");
            preparedStatement.setInt(1, contact.getSenderId());
            preparedStatement.setInt(2, contact.getReceiverId());
            preparedStatement.executeUpdate();
        } finally {
            preparedStatement.close();
        }
    }
}
