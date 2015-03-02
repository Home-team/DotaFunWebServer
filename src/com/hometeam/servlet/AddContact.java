package com.hometeam.servlet;

import com.hometeam.constant.R;
import com.hometeam.entity.User;
import com.hometeam.exception.ContactAlreadyExist;
import com.hometeam.jsonResponse.JsonError;
import com.hometeam.jsonResponse.JsonMessage;
import com.hometeam.service.ContactService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/addContact")
public class AddContact extends HttpServlet{
    private static final Logger LOG = Logger.getLogger(AddContact.class);
    private ContactService contactService;

    @Override
    public void init() throws ServletException {
        contactService = new ContactService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute(R.SESSION.USER);
        try {
            contactService.addContact(id ,user.getId());
            resp.getWriter().write(new JsonMessage("Contact added!").toString());
        } catch (ContactAlreadyExist contactAlreadyExist) {
            resp.getWriter().write(new JsonError("CONTACT_ALREADY_EXIST").toString());
        }
    }
}
