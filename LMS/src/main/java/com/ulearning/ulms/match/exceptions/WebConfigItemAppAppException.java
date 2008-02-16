/**
 * WebConfigItemAppAppException.java.
 * User: zhangy Date: 2005-6-3 9:18:33
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class WebConfigItemAppAppException extends ULMSAppException
{
        public WebConfigItemAppAppException()
        {
        }

        public WebConfigItemAppAppException(String msg)
        {
                super(msg);
        }

        public WebConfigItemAppAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        public WebConfigItemAppAppException(Throwable nested)
        {
                super(nested);
        }
}
