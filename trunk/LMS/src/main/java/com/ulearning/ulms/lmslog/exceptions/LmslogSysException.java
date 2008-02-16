/**
 * LmslogSysException.java.
 * User: keyh  Date: 2004-8-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class LmslogSysException extends ULMSSysException
{
        public LmslogSysException()
        {
        }

        public LmslogSysException(String msg)
        {
                super(msg);
        }

        public LmslogSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public LmslogSysException(Throwable nested)
        {
                super(nested);
        }
}
