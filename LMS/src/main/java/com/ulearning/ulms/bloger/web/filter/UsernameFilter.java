/*
 * Created on 2004-10-8
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web.filter;

import javax.servlet.*;
import javax.servlet.http.*;


/**
 * To redirect /~username to /!username.c
 */
public final class UsernameFilter implements Filter
{
        public void init(FilterConfig filterConfig) throws ServletException
        {
        }

        public void destroy()
        {
        }

        public void doFilter(ServletRequest request, ServletResponse response,
                             FilterChain chain) throws java.io.IOException, ServletException
        {
                HttpServletRequest req = (HttpServletRequest) request;
                String url = req.getRequestURL().toString();
                System.out.println("UsernameFilter URL = " + url);

                int n = url.lastIndexOf("~");
                ((HttpServletResponse) response).sendRedirect("/!" +
                        url.substring(n + 1) + ".c");
        }
}
