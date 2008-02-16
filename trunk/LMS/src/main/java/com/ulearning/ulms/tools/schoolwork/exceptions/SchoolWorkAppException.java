/**
 * SchoolWorkAppException.java.
 * User: yud  Date: 2005-4-12
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;

public class SchoolWorkAppException extends ULMSAppException
{
        public SchoolWorkAppException()
        {
        }

        public SchoolWorkAppException(Throwable nested)
        {
                super(nested);
        }

        public SchoolWorkAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public SchoolWorkAppException(String msg)
        {
                super(msg);
        }


}
