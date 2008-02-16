/**
 * TeaWorkSysException.java.
 * User: yud  Date: 2005-4-15
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;

public class TeaWorkSysException extends ULMSSysException
{
        public TeaWorkSysException()
        {
        }

        public TeaWorkSysException(String msg)
        {
                super(msg);
        }

        public TeaWorkSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public TeaWorkSysException(Throwable nested)
        {
                super(nested);
        }
}
