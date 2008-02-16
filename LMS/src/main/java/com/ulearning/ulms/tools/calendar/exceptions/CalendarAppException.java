/**
 * CalendarAppException.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class CalendarAppException extends ULMSAppException
{
        public CalendarAppException()
        {
        }

        public CalendarAppException(String msg)
        {
                super(msg);
        }

        public CalendarAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public CalendarAppException(Throwable nested)
        {
                super(nested);
        }
}
