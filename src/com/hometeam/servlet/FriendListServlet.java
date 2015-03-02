package com.hometeam.servlet;

import com.hometeam.constant.R;
import com.hometeam.entity.User;
import com.hometeam.service.ContactService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/contactList")
public class FriendListServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(FriendListServlet.class);
    private ContactService contactService;

    @Override
    public void init() throws ServletException {
        contactService = new ContactService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(R.SESSION.USER);
        String jsonContactList = contactService.getJsonContactList(user.getId());
        resp.getWriter().write(jsonContactList);
    }
}
