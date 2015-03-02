package com.hometeam.filter;

import com.hometeam.constant.R;
import com.hometeam.jsonResponse.JsonError;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LoginedFilter", urlPatterns = {"/contactList", "/addContact", "/sendMessage", "/getMessage"})
public class LoginedFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getSession().getAttribute(R.SESSION.USER) == null) {
            resp.getWriter().write(new JsonError("NEED_LOGIN_PERMISSION").toString());
            return;
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
