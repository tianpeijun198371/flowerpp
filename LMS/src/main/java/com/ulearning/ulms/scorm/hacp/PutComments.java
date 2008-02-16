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
 * Class for process the put comments command in aicc server side.
 *
 */
import java.lang.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class PutComments implements IHacpCommand
{
        protected static Log logger = LogFactory.getLog(GetParam.class);

        //properties
        HttpServletRequest req = null;
        HttpServletResponse resp = null;

        public PutComments(HttpServletRequest request, HttpServletResponse response)
        {
                req = request;
                resp = response;
        }

        public void execute() throws IOException, ServletException
        {
                HacpResponse.sendResult(req, resp, 0, null);
        }
}
