/*
 * Created on 2004-10-8
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web.filter;

import javax.servlet.*;


public class EncodingFilter implements Filter
{
        protected String encoding = null;

        public void doFilter(ServletRequest request, ServletResponse response,
                             FilterChain chain) throws java.io.IOException, ServletException
        {
                if (encoding != null)
                {
                        request.setCharacterEncoding(encoding);
                }

                chain.doFilter(request, response);
        }

        public void init(FilterConfig filterConfig) throws ServletException
        {
                encoding = filterConfig.getInitParameter("encoding");
        }

        public void destroy()
        {
        }
}
