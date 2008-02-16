/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: Fengch
 * Date: 2007-11-12 19:27:48
 */


package com.ulearning.ulms.core.struts;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import javax.servlet.ServletException;
import java.util.Date;

public class ConvertPlugIn implements PlugIn
{
        public void destroy()
        {
                ConvertUtils.deregister();
        }

        public void init(ActionServlet arg0, ModuleConfig arg1)
                throws ServletException
        {
                ConvertUtils.register(new DateConvert(), Date.class);
        }
}

