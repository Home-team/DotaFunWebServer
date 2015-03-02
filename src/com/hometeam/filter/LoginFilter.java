package com.hometeam.filter;

import com.hometeam.constant.R;
import com.hometeam.jsonResponse.JsonError;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/login", "/registration"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if(login == null || password == null) {
            req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
            return;
        }

        if(login.length() > R.LOGIN.MAX_LENGTH_LOGIN) {
            resp.getWriter().write(new JsonError("BAD_MAX_LOGIN_LENGTH").toString());
            return;
        }

        if(login.length() < R.LOGIN.MIN_LENGTH_LOGIN) {
            resp.getWriter().write(new JsonError("BAD_MIN_LOGIN_LENGTH").toString());
            return;
        }

        if(password.length() > R.LOGIN.MAX_LENGTH_PASSWORD) {
            resp.getWriter().write(new JsonError("BAD_MAX_PASSWORD_LENGTH").toString());
            return;
        }

        if(password.length() < R.LOGIN.MIN_LENGTH_PASSWORD) {
            resp.getWriter().write(new JsonError("BAD_MIN_PASSWORD_LENGTH").toString());
            return;
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
