/**
 * TeaWorkAppException.java.
 * User: yud  Date: 2005-4-17
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.      
 */
package com.ulearning.ulms.tools.schoolwork.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;

public class TeaWorkAppException extends ULMSAppException
{
        public TeaWorkAppException()
        {
        }

        public TeaWorkAppException(Throwable nested)
        {
                super(nested);
        }

        public TeaWorkAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public TeaWorkAppException(String msg)
        {
                super(msg);
        }


}
