package com.hometeam.servlet;

import com.hometeam.bean.Message;
import com.hometeam.constant.R;
import com.hometeam.entity.User;
import com.hometeam.service.MessageService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/getMessage")
public class GetMessageServlet extends HttpServlet{
    private static final Logger LOG = Logger.getLogger(GetMessageServlet.class);
    private MessageService messageService;

    @Override
    public void init() throws ServletException {
        messageService = new MessageService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(R.SESSION.USER);
        Message message = messageService.getMessage(user.getId());
        resp.getWriter().write(message.toJson());
    }
}
