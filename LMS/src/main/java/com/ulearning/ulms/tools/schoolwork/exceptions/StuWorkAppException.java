/**
 * StuWorkAppException.java.
 * User: yud  Date: 2005-3-7
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;

public class StuWorkAppException extends ULMSAppException
{
        public StuWorkAppException()
        {
        }

        public StuWorkAppException(Throwable nested)
        {
                super(nested);
        }

        public StuWorkAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public StuWorkAppException(String msg)
        {
                super(msg);
        }


}
