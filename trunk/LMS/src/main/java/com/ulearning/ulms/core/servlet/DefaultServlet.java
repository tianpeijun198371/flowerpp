/**
 * ULMSServlet.java.
 * User: Fengch  Date: 2005-4-1 15:16:15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;


/**
 * ulms default Servlet - all ulms servlets shall extend this class
 * provides generic functions.
 */
public class DefaultServlet extends HttpServlet
{
        /**
         * Forward to a URL.
         *
         * @param request
         * @param response
         * @param url
         * @throws ServletException
         * @throws IOException
         */
        protected void forward(HttpServletRequest request,
                               HttpServletResponse response, String url)
                throws ServletException, IOException
        {
                response.setContentType("text/html; charset=gb2312");

                RequestDispatcher rd = getServletContext()
                        .getRequestDispatcher(url);
                rd.forward(request, response);
        }

        /**
         * Include an URL.
         *
         * @param request
         * @param response
         * @param url
         * @throws ServletException
         * @throws IOException
         */
        protected void include(HttpServletRequest request,
                               HttpServletResponse response, String url)
                throws ServletException, IOException
        {
                RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                rd.include(request, response);
        }
}
