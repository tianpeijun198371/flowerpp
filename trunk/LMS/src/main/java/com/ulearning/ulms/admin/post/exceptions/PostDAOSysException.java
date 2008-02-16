/**
 * PostDAOSysException.java.
 * User: shid Date: 2005-7-21 10:43:25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.post.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class PostDAOSysException extends ULMSSysException
{
        public PostDAOSysException(String str)
        {
                super(str);
        }

        public PostDAOSysException(Throwable nested)
        {
                super(nested);
        }

        /**
         * Default constructor. Takes no arguments
         */
        public PostDAOSysException()
        {
                super();
        }
}
