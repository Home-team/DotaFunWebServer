package com.hometeam.servlet.user;

import com.hometeam.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserService userService = new UserService();
        String session = userService.login(login, password);

        if(session != null) {
            response.getWriter().write(session);
        } else {
            response.getWriter().write("BAD_LOGIN_OR_PASSWORD");
        }
    }
}
