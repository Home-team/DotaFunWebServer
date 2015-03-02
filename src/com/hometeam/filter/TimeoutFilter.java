package com.hometeam.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "TimeoutFilter", urlPatterns = "*")
public class TimeoutFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(TimeoutFilter.class);
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        long l = System.nanoTime();
        chain.doFilter(req, resp);
        LOG.info("TIME: " + (System.nanoTime() - l) / 1000000000.0 + " sec.");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
