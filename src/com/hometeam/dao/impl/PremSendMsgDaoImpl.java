package com.hometeam.dao.impl;

import com.hometeam.dao.PremSendMsgDao;
import com.hometeam.entity.PremSendMsg;
import com.hometeam.util.PooledDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PremSendMsgDaoImpl implements PremSendMsgDao {
    private static final Logger LOG = Logger.getLogger(PremSendMsgDaoImpl.class);

    @Override
    public PremSendMsg getPremSendMsg(ResultSet resultSet) throws SQLException {
        PremSendMsg premSendMsg = new PremSendMsg();
        premSendMsg.setId(resultSet.getInt("id"));
        premSendMsg.setReceiver(resultSet.getInt("receiver"));
        premSendMsg.setSender(resultSet.getInt("sender"));
        return premSendMsg;
    }

    @Override
    public List<PremSendMsg> getByReceiver(int id) {
        Connection connection = PooledDataSource.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        PremSendMsg premSendMsg = null;
        List<PremSendMsg> premSendMsgList = new LinkedList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM PREM_SEND_MSG WHERE PREM_SEND_MSG.RECEIVER = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                premSendMsg = getPremSendMsg(resultSet);
                premSendMsgList.add(premSendMsg);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }

        return premSendMsgList;
    }

    @Override
    public void delete(PremSendMsg premSendMsg) {
        Connection connection = PooledDataSource.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM PREM_SEND_MSG WHERE PREM_SEND_MSG.RECEIVER = ? AND PREM_SEND_MSG.SENDER = ?");
            preparedStatement.setInt(1, premSendMsg.getReceiver());
            preparedStatement.setInt(2, premSendMsg.getSender());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }
    }

    @Override
    public void create(PremSendMsg premSendMsg) {
        Connection connection = PooledDataSource.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO PREM_SEND_MSG (SENDER, RECEIVER) VALUES (?, ?)");
            preparedStatement.setInt(1, premSendMsg.getSender());
            preparedStatement.setInt(2, premSendMsg.getReceiver());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
        }
    }
}
