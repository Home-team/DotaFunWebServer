package com.hometeam.dao;

import com.hometeam.entity.PremSendMsg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PremSendMsgDao {
    PremSendMsg getPremSendMsg(ResultSet resultSet) throws SQLException;

    List<PremSendMsg> getByReceiver(int id);

    void delete(PremSendMsg premSendMsg);

    void create(PremSendMsg premSendMsg);
}
