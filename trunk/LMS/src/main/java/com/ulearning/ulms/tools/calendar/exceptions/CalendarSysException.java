/**
 * CalendarSysException.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class CalendarSysException extends ULMSSysException
{
        public CalendarSysException()
        {
        }

        public CalendarSysException(String msg)
        {
                super(msg);
        }

        public CalendarSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public CalendarSysException(Throwable nested)
        {
                super(nested);
        }
}
