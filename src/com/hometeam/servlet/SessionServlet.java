package com.hometeam.servlet;

import com.hometeam.jsonResponse.JsonMessage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/session")
public class SessionServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SessionServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(new JsonMessage(req.getSession().getId()).toString());
    }
}
