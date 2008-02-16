/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-28 11:46:34
 */
package com.ulearning.ulms.scorm.hacp;

/**
 * Class for send the server process result to client(browser).
 *
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HacpResponse
{
        protected static Log logger = LogFactory.getLog(HacpServlet.class);

        //properties
        static String[] error_text = {
                "Successful", "Invalid Command", "Invalid AU-password",
                "Invalid Session ID"
        };

        public HacpResponse()
        {
        }

        public static void sendResult(HttpServletRequest req,
                                      HttpServletResponse resp, int error, String aicc_data)
                throws IOException, ServletException
        {
                resp.setContentType("text/plain");

                PrintWriter out = resp.getWriter();
                String version = (String) req.getParameter("version");

                if ((error > 4) || (error < 0))
                {
                        error = 1;
                }

                StringBuffer result = new StringBuffer();

                result.append("error=" + error + "\n");
                result.append("error_text=" + error_text[error] + "\n");
                result.append("version=2.0" + "\n");

                if (aicc_data != null)
                {
                        result.append("aicc_data=" + aicc_data + "\n");
                }

                logger.info("result = " + result.toString());
                out.print(URLEncoder.encode(result.toString(), "UTF-8"));
        }
}
