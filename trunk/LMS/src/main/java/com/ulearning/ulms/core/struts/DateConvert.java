/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: Fengch
 * Date: 2007-11-12 19:00:45
 */


package com.ulearning.ulms.core.struts;


import org.apache.commons.beanutils.Converter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;

public class DateConvert implements Converter
{
        protected static Log logger = LogFactory.getLog(DateConvert.class);
        static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public Object convert(Class arg0, Object value)
        {
                try
                {
                        logger.info("value="+value);
                        return sdf.parse((String) value);
                }
                catch (Exception ex)
                {
                        logger.info("Error:",ex);
                        return null;
                }
        }
}


