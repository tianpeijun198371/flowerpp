/**
 * LmslogAppException.java.
 * User: keyh  Date: 2004-8-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class LmslogAppException extends ULMSAppException
{
        public LmslogAppException()
        {
        }

        public LmslogAppException(String msg)
        {
                super(msg);
        }

        public LmslogAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public LmslogAppException(Throwable nested)
        {
                super(nested);
        }
}
