package com.ulearning.ulms.util;

/**
 * Created by IntelliJ IDEA.
 * User: liaoxx
 * Date: 2007-2-28
 * Time: 17:48:17
 * To change this template use File | Settings | File Templates.
 */

import com.ulearning.ulms.util.HibernateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import java.io.IOException;

public class HibernateFilter implements Filter
{

        private static Log log = LogFactory.getLog(HibernateFilter.class);

        public void init(FilterConfig filterConfig) throws ServletException
        {
                log.info("Servlet filter init, now opening/closing a Session for each request.");
        }

        public void doFilter(ServletRequest request,
                             ServletResponse response,
                             FilterChain chain)
                throws IOException, ServletException
        {

                // There is actually no explicit "opening" of a Session, the
                // first call to HibernateUtil.beginTransaction() in control
                // logic (e.g. use case controller/event handler) will get
                // a fresh Session.
                try
                {
                        chain.doFilter(request, response);

                        // Commit any pending database transaction.
                        //HibernateUtil.commitTransaction();

                }
                finally
                {
                        try
                        {
                                // No matter what happens, close the Session.
                                HibernateUtil.closeSession();
                        }
                        catch (Exception ex)
                        {

                        }

                }
        }

        public void destroy()
        {
        }

}

