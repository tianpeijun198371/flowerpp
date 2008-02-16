/**
 * StuWorkSysException.java.
 * User: yud  Date: 2005-4-17
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;

public class StuWorkSysException extends ULMSSysException
{
        public StuWorkSysException()
        {
        }

        public StuWorkSysException(String msg)
        {
                super(msg);
        }

        public StuWorkSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public StuWorkSysException(Throwable nested)
        {
                super(nested);
        }
}
