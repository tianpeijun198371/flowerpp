/**
 * PostAppException.java.
 * User: shid Date: 2005-7-21 10:41:36
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.post.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class PostAppException extends ULMSAppException
{
        public PostAppException(String str)
        {
                super(str);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public PostAppException()
        {
                super();
        }

        public PostAppException(Throwable nested)
        {
                super(nested);
        }
}
