/**
 * LmslogOperDescSysException.java.
 * User: keyh  Date: 2004-8-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class LmslogOperDescSysException extends ULMSSysException
{
        public LmslogOperDescSysException()
        {
        }

        public LmslogOperDescSysException(String msg)
        {
                super(msg);
        }

        public LmslogOperDescSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public LmslogOperDescSysException(Throwable nested)
        {
                super(nested);
        }
}
