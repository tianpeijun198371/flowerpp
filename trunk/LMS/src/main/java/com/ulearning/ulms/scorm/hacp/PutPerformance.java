/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-28 11:46:34
 */
package com.ulearning.ulms.scorm.hacp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.log4j.*;

import java.io.*;

import javax.servlet.*;

/**
 * Class for the put the performance info to aicc server.
 *
 */
import javax.servlet.http.*;


public class PutPerformance implements IHacpCommand
{
        protected static Log logger = LogFactory.getLog(GetParam.class);

        //properties
        HttpServletRequest req = null;
        HttpServletResponse resp = null;

        public PutPerformance(HttpServletRequest request,
                              HttpServletResponse response)
        {
                req = request;
                resp = response;
        }

        public void execute() throws IOException, ServletException
        {
                String aicc_data = req.getParameter("aicc_data");

                if (aicc_data != null)
                {
                        logger.info("PutPerformance :" + aicc_data);
                }

                HacpResponse.sendResult(req, resp, 0, null);
        }
}
