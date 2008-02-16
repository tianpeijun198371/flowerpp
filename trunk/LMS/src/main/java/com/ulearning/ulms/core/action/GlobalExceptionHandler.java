/**
 * GlobalExceptionHandler.java.
 * User: fengch  Date: 2004-5-25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.action;

import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.Globals;
import org.apache.struts.action.*;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.util.ModuleException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GlobalExceptionHandler extends ExceptionHandler
{
        public ActionForward execute(Exception ex, ExceptionConfig ae,
                                     ActionMapping mapping, ActionForm formInstance,
                                     HttpServletRequest request, HttpServletResponse response)
                throws ServletException
        {
                LogUtil.debug("core", "[GlobalExceptionHandler]-----------start.");

                ActionForward forward = null;
                ActionError error = null;
                String property = null;

                // Build the forward from the exception mapping if it exists
                // or from the form input
                if (ae.getPath() != null)
                {
                        forward = new ActionForward(ae.getPath());
                }
                else
                {
                        forward = mapping.getInputForward();
                }

                // Figure out the error
                if (ex instanceof ModuleException)
                {
                        error = ((ModuleException) ex).getError();
                        property = ((ModuleException) ex).getProperty();
                }
                else
                {
                        error = new ActionError(ae.getKey(), ex.getMessage());
                        property = error.getKey();
                }

                // Store the exception
                request.setAttribute(Globals.EXCEPTION_KEY, ex);
                storeException(request, property, error, forward, ae.getScope());
                //ex.printStackTrace();
                LogUtil.debug("core", "[GlobalExceptionHandler]-----------end.");

                return forward;
        }
}
