/**
 * JspUtil.java.
 * User: fengch Date: 2005-6-2 18:58:33
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import com.ulearning.ulms.util.LMSConstants;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 封装了一些在JSP页面中经常用到的方法的实用类。
 */
public class JspUtil
{
        /**
         * 判断Session是否过期，若过期，则页面跳转到相应页面。
         *
         * @param request
         * @param response
         */
        public static boolean verifySession(HttpServletRequest request,
                                            HttpServletResponse response) throws IOException
        {
                HttpSession session = request.getSession();
                Object userKey = session.getAttribute(LMSConstants.USERID_KEY);

                if (userKey == null)
                {
                        response.sendRedirect(request.getContextPath() + "/error_page/" +
                                LMSConstants.NOSESSION_PAGE);

                        return false;
                }

                return true;
        }
}
