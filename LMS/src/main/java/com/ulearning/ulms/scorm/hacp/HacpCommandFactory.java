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

/**
 * Class Factory for the hacp command. it can get the different hacp command
 *                        object according the request.
 */
import java.lang.*;

import java.util.Properties;

import javax.servlet.http.*;


public class HacpCommandFactory
{
        protected static Log logger = LogFactory.getLog(GetParam.class);

        public static IHacpCommand getInstanceOf(HttpServletRequest request,
                                                 HttpServletResponse response)
        {
                String command = (String) request.getParameter("command");

                if (command != null)
                {
                        command = command.trim();
                }

                if (command == null)
                { //wrong command

                        return null;
                }
                else
                {
                        command = command.toLowerCase();
                        logger.info("command:" + command);

                        if (command.equalsIgnoreCase("getparam"))
                        {
                                return new GetParam(request, response);
                        }
                        else if (command.equalsIgnoreCase("putparam"))
                        {
                                return new PutParam(request, response);
                        }
                        else if (command.equalsIgnoreCase("putcomments"))
                        {
                                return new PutComments(request, response);
                        }
                        else if (command.equalsIgnoreCase("putpath"))
                        {
                                return new PutPath(request, response);
                        }
                        else if (command.equalsIgnoreCase("putinteractions"))
                        {
                                return new PutInteractions(request, response);
                        }
                        else if (command.equalsIgnoreCase("putobjectives"))
                        {
                                return new PutObjectives(request, response);
                        }
                        else if (command.equalsIgnoreCase("putperformance"))
                        {
                                return new PutPerformance(request, response);
                        }
                        else if (command.equalsIgnoreCase("exitau"))
                        {
                                return new ExitAU(request, response);
                        }
                        else
                        {
                                return null;
                        }
                }
        }
}
