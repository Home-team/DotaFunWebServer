package com.hometeam.service;

import com.hometeam.bean.ContactBean;
import com.hometeam.constant.R;
import com.hometeam.core.Messager;
import com.hometeam.dao.ContactDao;
import com.hometeam.dao.SettingDao;
import com.hometeam.dao.mySqlImpl.ContactDaoImpl;
import com.hometeam.dao.mySqlImpl.SettingDaoImpl;
import com.hometeam.entity.Contact;
import com.hometeam.exception.ContactAlreadyExist;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactService {
    private static final Logger LOG = Logger.getLogger(ContactService.class);
    ContactDao contactDao = new ContactDaoImpl();
    SettingDao settingDao = new SettingDaoImpl();
    Messager messager = Messager.getInstance();

    public String getJsonContactList(int userId) {
        List<ContactBean> contactList = getContactList(userId);
        JSONArray jsonArray = new JSONArray();
        for (ContactBean contactBean : contactList) {
            jsonArray.add(contactBean.toJsonObject());
        }
        return jsonArray.toJSONString();
    }

    public List<ContactBean> getContactList(int userId) {
        List<Contact> bySender = contactDao.findBySender(userId);
        List<ContactBean> contactBeans = new ArrayList<>();
        ContactBean contactBean = null;

        int slot = Integer.parseInt(settingDao.find(userId, R.SETTING.SLOT).getValue());

        for (Contact contact : bySender) {
            contactBean = new ContactBean();
            contactBean.setName(settingDao.find(contact.getReceiverId(), R.SETTING.NAME).getValue());

            if(slot-- > 0) {
                contactBean.setId(contact.getReceiverId());
            }
            contactBeans.add(contactBean);
        }

        return contactBeans;
    }

    public void addContact(int senderId, int receiverId) throws ContactAlreadyExist {
        Contact contact = new Contact();
        contact.setSenderId(senderId);
        contact.setReceiverId(receiverId);

        try {
            contactDao.create(contact);
            messager.addContact(senderId, receiverId);
        } catch (SQLException e) {
            throw  new ContactAlreadyExist();
        }
    }
}
