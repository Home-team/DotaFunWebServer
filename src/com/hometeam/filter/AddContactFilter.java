package com.hometeam.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "AddContactFilter", urlPatterns = "/addContact")
public class AddContactFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
        }

        if(id == null) {
            req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
        }

        if(String.valueOf(id).length() > 10) {
            req.getRequestDispatcher("WEB-INF/error.jsp").forward(req, resp);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
