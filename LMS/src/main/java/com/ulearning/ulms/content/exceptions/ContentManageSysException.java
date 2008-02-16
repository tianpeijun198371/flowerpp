/**
 * ContentManageSysException.java.
 * User: Fengch  Date: 2005-5-30 10:45:36
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.exceptions;

import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class ContentManageSysException extends ULMSSysException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public ContentManageSysException()
        {
                super();
        }

        /**
         * Constructs an <code>ContentManageSysException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public ContentManageSysException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>ContentManageSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public ContentManageSysException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>ContentManageSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public ContentManageSysException(Throwable nested)
        {
                super(nested);
        }
}
