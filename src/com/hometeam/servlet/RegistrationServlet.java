package com.hometeam.servlet;

import com.hometeam.constant.R;
import com.hometeam.entity.User;
import com.hometeam.exception.UserAlreadyExist;
import com.hometeam.jsonResponse.JsonError;
import com.hometeam.jsonResponse.JsonMessage;
import com.hometeam.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(RegistrationServlet.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            userService.registration(login, password);
            User user = userService.authentication(login, password);
            req.getSession().setAttribute(R.SESSION.USER, user);
            resp.getWriter().write(new JsonMessage(req.getSession().getId()).toString());
        } catch (UserAlreadyExist userAlreadyExist) {
            resp.getWriter().write(new JsonError("USER_ALREADY_EXIST").toString());
        }
    }
}
