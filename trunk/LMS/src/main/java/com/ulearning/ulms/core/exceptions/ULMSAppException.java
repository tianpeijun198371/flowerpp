/**
 * ULMSAppException.java      2004-3-30 16:49
 * Author:cunhua.feng
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Huaxia Dadi Distance Learning Services Co.,Ltd.
 * You shall not disclose such Confidential Information.
 *
 * Modified by: name, date, comments for this modification
 * Modified by: name, date, comments for this modification
 *
 ***/
package com.ulearning.ulms.core.exceptions;


/**
 * Base ulms App Exception, provides nested exceptions, etc
 */
public class ULMSAppException extends ULMSException
{
        /**
         * Creates new <code>ULMSAppException</code> without detail message.
         */
        public ULMSAppException()
        {
                super();
        }

        /**
         * Constructs an <code>ULMSAppException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public ULMSAppException(String msg)
        {
                super(msg);
        }

        /**
         * Constructs an <code>ULMSAppException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public ULMSAppException(String msg, Throwable nested)
        {
                super(msg, nested);
        }

        /**
         * Constructs an <code>ULMSAppException</code> with the specified detail message,errorKey.
         *
         * @param msg the detail message.
         */
        public ULMSAppException(String msg, String errorKey)
        {
                super(msg, errorKey);
        }

        public ULMSAppException(String msg, String errorKey, Throwable nested)
        {
                super(msg, nested);
                super.setErrorKey(errorKey);
        }

        /**
         * Constructs an <code>ULMSAppException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public ULMSAppException(Throwable nested)
        {
                super(nested);
        }
}
