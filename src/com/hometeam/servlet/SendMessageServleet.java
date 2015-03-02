package com.hometeam.servlet;

import com.hometeam.constant.R;
import com.hometeam.entity.User;
import com.hometeam.exception.NoContactList;
import com.hometeam.jsonResponse.JsonError;
import com.hometeam.jsonResponse.JsonMessage;
import com.hometeam.service.MessageService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/sendMessage")
public class SendMessageServleet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SendMessageServleet.class);

    private MessageService messageService;

    @Override
    public void init() throws ServletException {
        messageService = new MessageService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String message = req.getParameter("msg");
        User user = (User) req.getSession().getAttribute(R.SESSION.USER);
        try {
            messageService.sendMessage(user.getId(), id, message);
            resp.getWriter().write(new JsonMessage("Messages sent!").toString());
        } catch (NoContactList noContactList) {
            resp.getWriter().write(new JsonError("NO_CONTACT_LIST").toString());
        }
    }
}
