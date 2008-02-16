/**
 * LmslogTypeDescAppException.java.
 * User: keyh  Date: 2004-8-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class LmslogTypeDescAppException extends ULMSAppException
{
        public LmslogTypeDescAppException()
        {
        }

        public LmslogTypeDescAppException(String msg)
        {
                super(msg);
        }

        public LmslogTypeDescAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public LmslogTypeDescAppException(Throwable nested)
        {
                super(nested);
        }
}
