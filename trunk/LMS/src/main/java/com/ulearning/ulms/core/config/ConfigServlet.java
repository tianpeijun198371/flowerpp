/**
 * ConfigServlet.java.
 * User: fengch  Date: 2004-5-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.config;

import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class ConfigServlet extends HttpServlet
{
        final static String INIT_PARAM_ulms_FILE = "ulms_CONFIG";
        final static String INIT_PARAM_CONFIG_FILE = "SYS_CONFIG";
        final static String INIT_PARAM_LOG_FILE = "LOG_CONFIG";

        public void init() throws ServletException
        {
                // Make sure to always call the super's init() first
                super.init();

                //  Load configuration file and init context
                ServletConfig config = getServletConfig();
                ServletContext context = getServletContext();
                // init logging
                initLog(config, context);

                try
                {
                        String configFile = config.getInitParameter(INIT_PARAM_CONFIG_FILE);
                        System.out.println(INIT_PARAM_CONFIG_FILE + " == " + configFile +
                                ".");

                        URL configURL = context.getResource(configFile);

                        //props = DomUtil.loadProperties(configURL.openStream());
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }

                // add application root path to system properties
                //String appRootPath = context.getRealPath("/").replace('\\', '/');   // excluding ending '/'
                //props.setProperty(BuilderConstants.PROP_APP_ROOT_PATH, appRootPath);

                //context.setAttribute(BuilderConstants.BUILDER_CONTEXT, bcontext);
        }

        public void service(javax.servlet.http.HttpServletRequest request,
                            javax.servlet.http.HttpServletResponse response)
                throws javax.servlet.ServletException, java.io.IOException
        {
                ServletConfig config = getServletConfig();
                ServletContext context = getServletContext();
                initLog(config, context);
        }

        public void destroy()
        {
                // todo
        }

        private void initLog(ServletConfig config, ServletContext context)
        {
        }
}
