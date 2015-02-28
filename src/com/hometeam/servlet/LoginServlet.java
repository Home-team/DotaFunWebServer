package com.hometeam.servlet;

import com.hometeam.constant.R;
import com.hometeam.entity.User;
import com.hometeam.jsonResponse.JsonError;
import com.hometeam.jsonResponse.JsonMessage;
import com.hometeam.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(LoginServlet.class);

    UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.authentication(req.getParameter("login"), req.getParameter("password"));
        if(user == null) {
            resp.getWriter().write(new JsonError("BAD_LOGIN_OR_PASSWORD").toString());
        } else {
            HttpSession session = req.getSession();
            session.setAttribute(R.SESSION.USER, user);
            resp.getWriter().write(new JsonMessage(session.getId()).toString());
        }
    }
}
