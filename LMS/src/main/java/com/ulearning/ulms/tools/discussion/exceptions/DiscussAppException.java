/**
 * DiscussAppException.java.
 * User: huangsb  Date: 2004-6-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;


public class DiscussAppException extends ULMSAppException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public DiscussAppException()
        {
                super();
        }

        /**
         * Constructs an <code>DiscussAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public DiscussAppException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>DiscussAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public DiscussAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>DiscussAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public DiscussAppException(Throwable nested)
        {
                super(nested);
        }
}
