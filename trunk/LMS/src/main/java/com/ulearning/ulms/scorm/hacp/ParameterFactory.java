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
 * Class Factory for the Parameter classes.
 *
 */
import java.lang.*;

import java.util.Properties;


public class ParameterFactory
{
        protected static Log logger = LogFactory.getLog(GetParam.class);

        public static IParameter getInstanceOf(String inStr)
        {
                // if the inStr include the '=', mean the input format is in pairs.
                //    or, it is in table format
                // NOTE: suppose: there are not '=' in value.
                if ((inStr == null) || (inStr.trim() == null) ||
                        inStr.trim().equals(""))
                {
                        return null;
                }

                if (inStr.indexOf('=') > 0)
                {
                        logger.info("PairParameter!");

                        return new PairParameter(inStr);
                }
                else
                {
                        logger.info("TableParameter!");

                        return new TableParameter(inStr);
                }
        }
}
