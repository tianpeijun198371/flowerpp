/**
 * DiscussDAOSysException.java.
 * User: huangsb  Date: 2004-6-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class DiscussDAOSysException extends ULMSSysException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public DiscussDAOSysException()
        {
                super();
        }

        /**
         * Constructs an <code>DiscussDAOSysException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public DiscussDAOSysException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>DiscussDAOSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public DiscussDAOSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>DiscussDAOSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public DiscussDAOSysException(Throwable nested)
        {
                super(nested);
        }
}
