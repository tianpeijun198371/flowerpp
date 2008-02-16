/*
 * Created on 2004-10-14
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import javax.servlet.http.*;


/**
 * Exception handler.
 *
 * @author Huaxia
 */
public class ExceptionHandler implements HandlerExceptionResolver
{
        public ModelAndView resolveException(HttpServletRequest request,
                                             HttpServletResponse response, Object handler, Exception e)
        {
                String url = request.getRequestURL().toString();
                String param = request.getQueryString();

                if ((param != null) && !param.equals(""))
                {
                        url = url + "?" + param;
                }

                String message = e.getMessage();
                Map map = new HashMap();
                map.put("url", url);
                map.put("message", message);

                return new ModelAndView("error", map);
        }
}
