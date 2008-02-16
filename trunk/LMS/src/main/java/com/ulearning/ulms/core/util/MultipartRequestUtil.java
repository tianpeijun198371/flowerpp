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
 * 封装了一些上载处理相关方法的实用类。
 */
public class MultipartRequestUtil
{
        /**
         * 返回参数之值。
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
