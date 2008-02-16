/**
 * LmslogTypeDescSysException.java.
 * User: keyh  Date: 2004-8-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class LmslogTypeDescSysException extends ULMSSysException
{
        public LmslogTypeDescSysException()
        {
        }

        public LmslogTypeDescSysException(String msg)
        {
                super(msg);
        }

        public LmslogTypeDescSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public LmslogTypeDescSysException(Throwable nested)
        {
                super(nested);
        }
}
