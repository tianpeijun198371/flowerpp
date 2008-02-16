/**
 * MultipartRequestUtil.java.
 * User: fengch Date: 2007-08-09 9:14:56
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import org.apache.struts.upload.MultipartRequestWrapper;

import javax.servlet.http.HttpServletRequest;


/**
 * ��װ��һЩ���ش�����ط�����ʵ���ࡣ
 */
public class MultipartRequestUtil
{
        /**
         * ���ز���ֵ֮��
         */
        public static String getParameter(HttpServletRequest request, String name)
        {
                if (name == null)
                {
                        return "";
                }

                String value = "";
                System.out.println(
                        "[MultipartRequestUtil]getParameter---###################value1=" +
                                request.getParameter(name));

                if (request instanceof MultipartRequestWrapper)
                {
                        request = (HttpServletRequest) ((MultipartRequestWrapper) request).getRequest();
                        value = request.getParameter(name);
                        System.out.println(
                                "[MultipartRequestUtil]getParameter---###################value2=" +
                                        value);
                }

                return value;
        }
}
