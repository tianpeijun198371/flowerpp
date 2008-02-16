/**
 * LmslogOperDescAppException.java.
 * User: keyh  Date: 2004-8-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class LmslogOperDescAppException extends ULMSAppException
{
        public LmslogOperDescAppException()
        {
        }

        public LmslogOperDescAppException(String msg)
        {
                super(msg);
        }

        public LmslogOperDescAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public LmslogOperDescAppException(Throwable nested)
        {
                super(nested);
        }
}
