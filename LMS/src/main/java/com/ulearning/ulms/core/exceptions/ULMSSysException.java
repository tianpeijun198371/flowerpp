/**
 * ULMSSysException.java      2004-4-19 16:39
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
 * Base ulms Sys Exception, provides nested exceptions, etc
 */
public class ULMSSysException extends ULMSException
{
        /**
         * Creates new <code>ULMSSysException</code> without detail message.
         */
        public ULMSSysException()
        {
                super();
                super.setErrorKey("error.base.sys");
        }

        /**
         * Constructs an <code>ULMSSysException</code> with the specified detail message.
         *
         * @param msg the detail message.
         */
        public ULMSSysException(String msg)
        {
                super(msg);
                super.setErrorKey("error.base.sys");
        }

        /**
         * Constructs an <code>ULMSSysException</code> with the specified detail message and nested Exception.
         *
         * @param msg the detail message.
         */
        public ULMSSysException(String msg, Throwable nested)
        {
                super(msg, nested);
                super.setErrorKey("error.base.sys");
        }

        public ULMSSysException(String msg, String errorKey, Throwable nested)
        {
                super(msg, nested);
                super.setErrorKey(errorKey);
        }

        /**
         * Constructs an <code>ULMSSysException</code> with the specified detail message,errorKey.
         *
         * @param msg the detail message.
         */
        public ULMSSysException(String msg, String errorKey)
        {
                super(msg, errorKey);
        }

        /**
         * Constructs an <code>ULMSSysException</code> with the specified detail message and nested Exception.
         *
         * @param nested the detail message.
         */
        public ULMSSysException(Throwable nested)
        {
                super(nested);
                super.setErrorKey("error.base.sys");
        }
}
