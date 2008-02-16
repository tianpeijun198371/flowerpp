/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-28 11:46:34
 */
package com.ulearning.ulms.scorm.hacp;

import com.ulearning.ulms.scorm.exceptions.ScormAppException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class HacpServlet extends HttpServlet
{
        protected static Log logger = LogFactory.getLog(HacpServlet.class);

        public void service(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException
        {
                if (!request.getMethod().equalsIgnoreCase("POST"))
                {
                        //非法的请求
                        throw new ScormAppException("AICC课件和服务器只能以Post方式进行HCAP通讯！");
                }

                String encoding = "GBK";
                request.setCharacterEncoding(encoding);
                response.setContentType("text/plain; charset=" + encoding);

                String command = StringUtils.trimToEmpty(request.getParameter("command"));
                String version = StringUtils.trimToEmpty(request.getParameter("version"));
                String session_id = StringUtils.trimToEmpty(request.getParameter(
                        "session_id"));
                String aicc_data = StringUtils.trimToEmpty(request.getParameter(
                        "aicc_data"));

                if (aicc_data == null)
                {
                        aicc_data = StringUtils.trimToEmpty(request.getParameter(
                                "AICC_Data"));
                }

                String au_password = StringUtils.trimToEmpty(request.getParameter(
                        "AU_password"));

                logger.info("command = " + command);
                logger.info("version = " + version);
                logger.info("session_id = " + session_id);
                logger.info("aicc_data = " + aicc_data);
                logger.info("au_password = " + au_password);

                HttpSession session = request.getSession(true);

                if (!session_id.equals(session.getId()))
                { //wrong session id
                        HacpResponse.sendResult(request, response, 3, null);

                        return;
                }

                IHacpCommand hacpCommand = HacpCommandFactory.getInstanceOf(request,
                        response);

                if (hacpCommand == null)
                { //wrong command
                        HacpResponse.sendResult(request, response, 1, null);
                }
                else
                {
                        hacpCommand.execute();
                }
        }
}
