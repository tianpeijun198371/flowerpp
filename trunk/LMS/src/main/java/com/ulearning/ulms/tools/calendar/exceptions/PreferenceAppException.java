/**
 * PreferenceAppException.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class PreferenceAppException extends ULMSAppException
{
        public PreferenceAppException()
        {
        }

        public PreferenceAppException(String msg)
        {
                super(msg);
        }

        public PreferenceAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public PreferenceAppException(Throwable nested)
        {
                super(nested);
        }
}
