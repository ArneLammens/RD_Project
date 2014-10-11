package com.realdolmen.filter;

import javax.faces.application.ResourceHandler;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class NoCacheFilter implements Filter {
    private FilterConfig config;

    @Override
        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
            HttpServletResponse response = (HttpServletResponse) res;
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.addHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT"); // Proxies.
            chain.doFilter(req, res);
        }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }
}