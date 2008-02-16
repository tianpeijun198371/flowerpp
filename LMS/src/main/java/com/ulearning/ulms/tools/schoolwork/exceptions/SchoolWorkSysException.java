/**
 * SchoolWorkSysException.java.
 * User: yud  Date: 2005-4-17
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;

public class SchoolWorkSysException extends ULMSSysException
{
        public SchoolWorkSysException()
        {
        }

        public SchoolWorkSysException(String msg)
        {
                super(msg);
        }

        public SchoolWorkSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public SchoolWorkSysException(Throwable nested)
        {
                super(nested);
        }
}
