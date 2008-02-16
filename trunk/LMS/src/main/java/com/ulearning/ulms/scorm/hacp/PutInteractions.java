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

/**
 * Class for the put interaction command to aicc server.
 *
 */
import java.lang.*;

import java.util.Properties;

import javax.servlet.*;
import javax.servlet.http.*;


public class PutInteractions implements IHacpCommand
{
        protected static Log logger = LogFactory.getLog(GetParam.class);

        //properties
        HttpServletRequest req = null;
        HttpServletResponse resp = null;

        public PutInteractions(HttpServletRequest request,
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
                        logger.info("put interactions:" + aicc_data);
                }

                IParameter p = ParameterFactory.getInstanceOf(aicc_data);
                //Interaction i = new Interaction();
                HacpResponse.sendResult(req, resp, 0, null);
        }
}
