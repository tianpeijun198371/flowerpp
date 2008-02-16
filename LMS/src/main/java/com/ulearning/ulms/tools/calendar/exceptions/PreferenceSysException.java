/**
 * PreferenceSysException.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class PreferenceSysException extends ULMSSysException
{
        public PreferenceSysException()
        {
        }

        public PreferenceSysException(String msg)
        {
                super(msg);
        }

        public PreferenceSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public PreferenceSysException(Throwable nested)
        {
                super(nested);
        }
}
